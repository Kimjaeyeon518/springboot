package com.shop.springboot.restController;

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

    //  상품 리스트 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findUsers();
    }

    //  상품 조회
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId) {
        return userService.findOne(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + userId));
    }

    //  상품 등록
    @PostMapping
    public Long createUser(@RequestBody User user) {
        userService.save(user);
        return user.getId();
    }

    //  상품 수정
    @PutMapping("/{id}")
    public Long updateUser(@RequestBody User user, @PathVariable ("id") long userId) {
        User existingUser = userService.findOne(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + userId));

        existingUser.setName(user.getName());
        existingUser.setAddr(user.getAddr());
        existingUser.setDetailAddr(user.getDetailAddr());
        existingUser.setPassword(user.getPassword());

        userService.save(existingUser);

        return existingUser.getId();
    }

    //  상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
        User existingUser = userService.findOne(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + userId));
        userService.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}
