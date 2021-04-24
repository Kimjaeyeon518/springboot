package com.shop.springboot.repository;

import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.User;
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
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // 상품 등록
    @Test
    public void save() {
        //given
        User user = new User();
        user.setName("spring5");
        user.setEmail("123@12.com");
        user.setPassword("1234");
        user.setAddr("Addr");
        user.setDetailAddr("DetailAddr");

        //when
        userRepository.save(user);

        //then
        User result = userRepository.findById(user.getId()).get();
        assertThat(result.getId()).isEqualTo(user.getId());
    }

    // 상품 리스트 조회
    @Test
    public void findAll() {

        //given
        User user1 = new User();
        user1.setName("spring5");
        user1.setEmail("124@12.com");
        user1.setPassword("1234");
        user1.setAddr("Addr");
        user1.setDetailAddr("DetailAddr");

        User user2 = new User();
        user2.setName("spring6");
        user2.setEmail("125@12.com");
        user2.setPassword("1234");
        user2.setAddr("Addr");
        user2.setDetailAddr("DetailAddr");

        List<User> result1 = userRepository.findAll();

        //when
        userRepository.save(user1);
        userRepository.save(user2);

        //then
        List<User> result2 = userRepository.findAll();
        assertThat(result1.size()).isEqualTo(result2.size()-2);
    }

    // 상품 조회
    @Test
    public void findOne() {
        //given
        User user = new User();
        user.setName("spring5");
        user.setEmail("126@12.com");
        user.setPassword("1234");
        user.setAddr("Addr");
        user.setDetailAddr("DetailAddr");

        //when
        userRepository.save(user);

        //then
        User result = userRepository.findById(user.getId()).get();
        assertThat(result.getId()).isEqualTo(user.getId());
    }

    // 상품 삭제
    @Test
    public void delete() {
        //given
        User user = new User();
        user.setName("spring5");
        user.setEmail("172@12.com");
        user.setPassword("1234");
        user.setAddr("Addr");
        user.setDetailAddr("DetailAddr");
        List<User> result1 = userRepository.findAll();


        //when
        userRepository.save(user);
        userRepository.delete(user);

        //then
        List<User> result2 = userRepository.findAll();
        assertThat(result1.size()).isEqualTo(result2.size());
    }
}