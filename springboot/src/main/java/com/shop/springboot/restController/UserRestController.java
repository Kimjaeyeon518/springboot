package com.shop.springboot.restController;

import com.shop.springboot.dto.userDto.UserRequestDto;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.User;
import com.shop.springboot.exception.ResourceNotFoundException;
import com.shop.springboot.service.ProductService;
import com.shop.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
@RequestMapping(value = "/users")
public class UserRestController {

    private final UserService userService;

    //  회원 중복 확인
    @GetMapping("/duplicateCheck")
    public ResponseEntity<?> duplicateCheck(@RequestParam String email) {

        return ResponseEntity.ok(userService.duplicateCheck(email));
    }

    //  회원 리스트 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findUsers();
    }

    //  회원 조회
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) {
        return userService.findOne(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + userId));
    }

    //  회원 등록
    @PostMapping
    public Long createUser(@RequestBody User user) {
        userService.save(user);
        return user.getId();
    }

    //  회원 수정
    @PutMapping("/{id}")
    public Long updateUser(@RequestBody UserRequestDto userRequestDto, @PathVariable ("id") long userId) {
        User existingUser = userService.findOne(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + userId));
        userService.updateProfile(existingUser.getId(), userRequestDto);

        return existingUser.getId();
    }

    //  회원 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
        User existingUser = userService.findOne(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + userId));
        userService.delete(existingUser);
        return ResponseEntity.ok().build();
    }

    //  회원 비밀번호 변경
    @PutMapping("/{id}/password")
    public Long updatePassword(@PathVariable ("id") long userId, String password) {
        User existingUser = userService.findOne(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + userId));
        userService.updatePassword(userId, password);
        return existingUser.getId();
    }
}
