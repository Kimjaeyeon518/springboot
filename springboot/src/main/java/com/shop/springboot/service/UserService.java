package com.shop.springboot.service;

import com.shop.springboot.dto.userDto.UserRequestDto;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.User;
import com.shop.springboot.entity.enums.Role;
import com.shop.springboot.exception.DuplicatedException;
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

    public boolean duplicateCheck(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicatedException("이미 등록된 이메일 입니다!");
        }

        return true;
    }
    // 회원 등록
    public Long save(User user) {
        user.setRole(Role.USER);
        userRepository.save(user);
        return user.getId();
    }

    // 회원 리스트 조회
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    // 회원 조회
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }

    // 회원 삭제
    public void delete(User user) {
        userRepository.delete(user);
    }

    // 회원 비밀번호 변경
    public User updatePassword(Long userId, String password) {
        Optional<User> user = userRepository.findById(userId);
        user.get().setPassword(password);
        return userRepository.save(user.get());
    }

    //  회원 정보 수정
    public User updateProfile(Long userId, UserRequestDto userRequestDto) {
        Optional<User> user = userRepository.findById(userId);

        user.get().setName(userRequestDto.getName());
        user.get().setAddr(userRequestDto.getAddr());
        user.get().setDetailAddr(userRequestDto.getDetailAddr());

        return userRepository.save(user.get());
    }
}
