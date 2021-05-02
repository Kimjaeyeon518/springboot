package com.shop.springboot.service;

import com.shop.springboot.dto.MeDto.MeRequestDto;
import com.shop.springboot.dto.updatePasswordDto.UpdatePasswordRequestDto;
import com.shop.springboot.dto.userDto.UserRequestDto;
import com.shop.springboot.dto.userDto.UserResponseDto;
import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.User;
import com.shop.springboot.entity.enums.Role;
import com.shop.springboot.exception.DuplicatedException;
import com.shop.springboot.exception.NotExistUserException;
import com.shop.springboot.exception.UpdatePasswordException;
import com.shop.springboot.repository.ProductRepository;
import com.shop.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional      // Service 의  모든 메소드의 트랜잭션 처리
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 일반유저 회원가입
    public Long userRegistration(UserRequestDto userRequestDto) {
        if (userRepository.existsByIdentifier(userRequestDto.getIdentifier())) {
            throw new DuplicatedException("이미 등록된 아이디 입니다!");
        }

        duplicateCheck(userRequestDto.getEmail());

        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRequestDto.setAuthorities(Role.ADMIN.getKey());


        return userRepository.save(userRequestDto.toEntity()).getId();
    }

    public boolean duplicateCheck(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicatedException("이미 등록된 이메일 입니다!");
        }

        return true;
    }

    // 회원 리스트 조회
    public List<User> findUsers() {
        return userRepository.findAll().stream()
                .map(User::new)
                .collect(Collectors.toList());
    }

    // 회원 조회
    public Optional<User> findOne(Long userId) {
        return userRepository.findById(userId);
    }

    // 회원 삭제
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotExistUserException("존재하지 않는 유저입니다."));

        userRepository.delete(user);
    }

    // 회원 비밀번호 변경
    public void updatePassword(Long userId, UpdatePasswordRequestDto passwordRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotExistUserException("존재하지 않는 유저입니다."));
        String beforePassword = user .getPassword();

        if(!isPasswordEquals(beforePassword, passwordRequestDto.getOldPassword())) {
            throw new UpdatePasswordException("기존 비밀번호를 잘못 입력하였습니다.");
        }

        if (isPasswordEquals(beforePassword, passwordRequestDto.getNewPassword())) {
            throw new UpdatePasswordException("기존 비밀번호와 바꾸려는 새 비밀번호가 일치합니다.");
        }

        User updatedUser = user.updatePassword(passwordEncoder.encode(passwordRequestDto.getNewPassword()));

        userRepository.save(updatedUser);
    }

    private boolean isPasswordEquals(String password1, String password2) {
        return passwordEncoder.matches(password1, password2);
    }

    //  회원 정보 수정
    public UserResponseDto updateProfiles(Long userId, MeRequestDto meRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotExistUserException("존재하지 않는 유저입니다."));

        User updatedUser = user.updateProfiles(meRequestDto);

        user = userRepository.save(updatedUser);

        return user.toResponseDto(user);
    }
}
