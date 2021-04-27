package com.shop.springboot.config;

import com.shop.springboot.handler.CustomLoginFailureHandler;
import com.shop.springboot.handler.CustomLoginSuccessHandler;
import com.shop.springboot.service.CustomUserDetailsService;
import com.shop.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@EnableWebSecurity // Spring Security를 활성화한다는 의미의 어노테이션
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter는 Spring Security의 설정파일로서의 역할을 하기 위해 상속해야 하는 클래스

    private final CustomUserDetailsService customUserDetailsService;
    private final DataSource dataSource;

    @Override
    public void configure(WebSecurity web) { //  인증을 무시할 경로들을 설정
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // http 관련 인증 설정
        http
                .authorizeRequests() // 접근에 대한 인증 설정이 가능
//                .antMatchers("/users**", "/carts**", "/profiles**", "/productOrders**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN") // USER, ADMIN만 접근 가능
//                .antMatchers("/admin**").hasAuthority("ROLE_ADMIN") // ADMIN만 접근 가능
//                .antMatchers(HttpMethod.POST, "/products").hasAuthority("ROLE_ADMIN")
//                .antMatchers(HttpMethod.PUT, "/products").hasAuthority("ROLE_ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/products").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                    .accessDeniedPage("/accessDenied")
                .and()
                    .formLogin() // 로그인에 관한 설정
                    .loginPage("/login") // 로그인 페이지 링크
                    .loginProcessingUrl("/doLogin")   // 로그인 페이지 form action에 넣을 주소
                    .successHandler(successHandler())
                    .failureHandler(failureHandler())
                .and()
                    .logout() // 로그아웃에 관한 설정
                    .logoutUrl("/doLogout")
                    .logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 주소
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true) // 세션 날리기
                .and()
                    .headers().frameOptions().disable()
                .and()
                    .csrf()
                    .ignoringAntMatchers("/h2-console/**")
                    .ignoringAntMatchers("/swagger-ui.html**")
                .and()
                    .sessionManagement()
                    .maximumSessions(1)
                    .expiredUrl("/duplicated-login")
                    .sessionRegistry(sessionRegistry());

        http.rememberMe()
                .key("remember-me")
                .userDetailsService(customUserDetailsService)
                .tokenRepository(getJDBCRepository())
                .tokenValiditySeconds(60*60*24);
    }

    private PersistentTokenRepository getJDBCRepository() {

        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);

        return repo;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {   // 로그인할 때 필요한 정보를 가져오는 곳
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new CustomLoginSuccessHandler("/");
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() { return new CustomLoginFailureHandler(); }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }// Register HttpSessionEventPublisher

    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
}