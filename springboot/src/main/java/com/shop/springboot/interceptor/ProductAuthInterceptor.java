//package com.shop.springboot.interceptor;
//
//import com.shop.springboot.entity.Product;
//import com.shop.springboot.repository.ProductRepository;
//import com.shop.springboot.util.Sessions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.HandlerMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
//@Component
//public class ProductAuthInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    ProductRepository productRepository;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
//        String httpMethod = request.getMethod();
//
//        if(httpMethod.equals("POST") || httpMethod.equals("DELETE") || httpMethod.equals("PUT")) {
//            String sessionItem = (String)request.getSession().getAttribute(Sessions.SESSION_ID);
//            Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//            Long id = Long.parseLong((String)pathVariables.get("id"));
//
//            Product product = productRepository.findById(id).get();
//            String productSeller = product.getCreatedBy();
//
//            if(!productSeller.equals(sessionItem)){
//                response.getOutputStream().println("NOT AUTHORIZE!!");
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//}