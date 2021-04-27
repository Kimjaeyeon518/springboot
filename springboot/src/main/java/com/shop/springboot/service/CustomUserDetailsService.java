package com.shop.springboot.service;

import com.shop.springboot.entity.SecurityUser;
import com.shop.springboot.entity.User;
import com.shop.springboot.exception.NotExistUserException;
import com.shop.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 사용자가 입력한 identifier을 통해 디비에 저장된 유저 객체를 가져와서 UserDetails 객체로 변환하여 돌려주는 메소드
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userRepository.findByIdentifier(identifier).orElseThrow(()
                -> new NotExistUserException("로그인에 실패하였습니다."));

        if(nonNull(RequestContextHolder.getRequestAttributes())) {
            // 컨텍스트 홀더
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            request.getSession().setAttribute("user", user.toResponseDto(user));
        }

        return new SecurityUser(user);
    }
}