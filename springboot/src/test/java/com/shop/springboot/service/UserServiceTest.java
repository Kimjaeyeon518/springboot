package com.shop.springboot.service;

import com.shop.springboot.dto.userDto.UserRequestDto;
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
    public void save() {
        //given
        User user = new User();
        user.setName("spring5");
        user.setEmail("12222333@2.com");
        user.setPassword("1234");
        user.setAddr("Addr");
        user.setDetailAddr("DetailAddr");

        //when
        userService.save(user);

        //then
        User result = userService.findOne(user.getId()).get();
        assertThat(result.getId()).isEqualTo(user.getId());
    }

    // 상품 리스트 조회
    @Test
    public void findAll() {

        //given
        User user1 = new User();
        user1.setName("spring5");
        user1.setEmail("1212454@1.com");
        user1.setPassword("1234");
        user1.setAddr("Addr");
        user1.setDetailAddr("DetailAddr");

        User user2 = new User();
        user2.setName("spring6");
        user2.setEmail("12123445@2.com");
        user2.setPassword("1234");
        user2.setAddr("Addr");
        user2.setDetailAddr("DetailAddr");

        List<User> result1 = userService.findUsers();

        //when
        userService.save(user1);
        userService.save(user2);

        //then
        List<User> result2 = userService.findUsers();
        assertThat(result1.size()).isEqualTo(result2.size()-2);
    }

    // 상품 조회
    @Test
    public void findOne() {
        //given
        User user = new User();
        user.setName("spring5");
        user.setEmail("1246456776@1.com");
        user.setPassword("1234");
        user.setAddr("Addr");
        user.setDetailAddr("DetailAddr");

        //when
        userService.save(user);

        //then
        User result = userService.findOne(user.getId()).get();
        assertThat(result.getId()).isEqualTo(user.getId());
    }

    // 상품 삭제
    @Test
    public void delete() {
        //given
        User user = new User();
        user.setName("spring5");
        user.setEmail("17367645842@2.com");
        user.setPassword("1234");
        user.setAddr("Addr");
        user.setDetailAddr("DetailAddr");
        List<User> result1 = userService.findUsers();


        //when
        userService.save(user);
        userService.delete(user);

        //then
        List<User> result2 = userService.findUsers();
        assertThat(result1.size()).isEqualTo(result2.size());
    }

    @Test
    public void updatePassword() {
        //given
        String newPassword = "NEW_PASSWORD!";
        //when
        User result = userService.updatePassword(12l, newPassword);

        //then

        assertThat(result.getPassword()).isEqualTo(newPassword);

    }

    @Test
    public void updateProfile() {
        //given
        String newName = "NEW_NAME!";
        String newAddr = "NEW_ADDR!";
        String newDetailAddr = "NEW_DETAILADDR!";

        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setName(newName);
        userRequestDto.setAddr(newAddr);
        userRequestDto.setDetailAddr(newDetailAddr);

        //when
        User result = userService.updateProfile(12l, userRequestDto);

        //then

        assertThat(result.getName()).isEqualTo(userRequestDto.getName());

    }
}