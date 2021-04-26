package com.shop.springboot.service;

import com.shop.springboot.dto.userDto.UserRequestDto;
import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.User;
import com.shop.springboot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    // 상품 등록
    @Test
    public void USER_CRUD_SERVICE_TEST() {

        //given
        User user1 = new User();
        user1.setName("spring5");
        user1.setEmail("~~~1212454@1.com");
        user1.setPassword("1234");
        user1.setAddr("Addr");
        user1.setDetailAddr("DetailAddr");

        User user2 = new User();
        user2.setName("spring6");
        user2.setEmail("~~~~~12123445@2.com");
        user2.setPassword("1234");
        user2.setAddr("Addr");
        user2.setDetailAddr("DetailAddr");

        List<User> result1 = userService.findUsers();

        //when
        userService.save(user1);
        userService.save(user2);
        userService.delete(user2);

        //then
        List<User> result2 = userService.findUsers();
        assertThat(result1.size()).isEqualTo(result2.size()-1);

        User result = userService.findOne(user1.getId()).get();
        assertThat(result.getId()).isEqualTo(user1.getId());
    }
}