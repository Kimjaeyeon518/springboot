package com.shop.springboot.service;

import static org.junit.jupiter.api.Assertions.*;

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
        UserRequestDto userRequestDto1
                = new UserRequestDto("관리자","administer"
                ,"ROLE_ADMIN", "wodus123"
                , "jae518@naver.com", "경기도 고양시 덕양구"
                , "신원1로 70");

        UserRequestDto userRequestDto2
                = new UserRequestDto("spring5","sfasd3ng5"
                ,"ROLE_USER", "1234"
                , "~~~12HH124!!!54@1.com", "Addr"
                , "Addr");

        List<User> result1 = userService.findUsers();

        //when
        Long userId1 = userService.userRegistration(userRequestDto1);
        Long userId2 = userService.userRegistration(userRequestDto2);
        userService.delete(userId2);

        //then
        List<User> result2 = userService.findUsers();
        assertThat(result1.size()).isEqualTo(result2.size()-1);

        User result = userService.findOne(userId1).get();
        assertThat(result.getId()).isEqualTo(userId1);
    }
}