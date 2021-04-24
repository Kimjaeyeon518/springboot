package com.shop.springboot.service;

import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.User;
import com.shop.springboot.repository.ProductRepository;
import com.shop.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional      // Service 의 모든 메소드의 트랜잭션 처리
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
@Service
public class UserService {

    private final UserRepository userRepository;

    // 상품 등록
    public Long save(User user) {
        userRepository.save(user);
        return user.getId();
    }

    // 상품 리스트 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // 상품 조회
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }

    // 상품 삭제
    public void delete(User user) {
        userRepository.delete(user);
    }
}
