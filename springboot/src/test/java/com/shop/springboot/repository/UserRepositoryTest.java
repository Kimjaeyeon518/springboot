package com.shop.springboot.repository;

import com.shop.springboot.entity.ProductOrder;
import com.shop.springboot.entity.User;
import com.shop.springboot.entity.enums.Role;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Test
    public void USER_CRUD_REPOSITORY_TEST() {
        //given

        List<ProductOrder> productOrders = productOrderRepository.findAll();

        User user1 = new User();
        user1.setName("spring5");
        user1.setEmail("12Z^VV%^^4@12.com");
        user1.setPassword("1234");
        user1.setAddr("Addr");
        user1.setDetailAddr("DetailAddr");
        user1.setAuthorities(Role.USER.getKey());
        user1.setProductOrders(productOrders);

        User user2 = new User();
        user2.setName("spring6");
        user2.setEmail("12##ZZVVV%$#5@12.com");
        user2.setPassword("1234");
        user2.setAddr("Addr");
        user2.setDetailAddr("DetailAddr");
        user2.setAuthorities(Role.ADMIN.getKey());

        User user3 = new User();
        user3.setName("spring6");
        user3.setEmail("12##ZZZVVv@%$#5@12.com");
        user3.setPassword("1234");
        user3.setAddr("Addr");
        user3.setDetailAddr("DetailAddr");
        user3.setAuthorities(Role.ADMIN.getKey());
        List<User> result1 = userRepository.findAll();

        //when
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.delete(user2);

        //then
        List<User> result2 = userRepository.findAll();          // findAll() TEST
        assertThat(result1.size()).isEqualTo(result2.size()-2);

        User result = userRepository.findById(user1.getId()).get();     // findOne() TEST
        assertThat(result.getId()).isEqualTo(user1.getId());
    }

}