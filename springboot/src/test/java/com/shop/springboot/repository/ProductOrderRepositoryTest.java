package com.shop.springboot.repository;

import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.ProductOrder;
import com.shop.springboot.entity.User;
import com.shop.springboot.entity.enums.ProductOrderStatus;
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
class ProductOrderRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Test
    public void PRODUCT_ORDER_CRUD_REPOSITORY_TEST() {
        //given
        User user1 = new User();
        user1.setName("spring5");
        user1.setIdentifier("spfllgxx5");
        user1.setEmail("12Z^#~#VV%^^4@12.com");
        user1.setPassword("1234");
        user1.setAddr("Addr");
        user1.setDetailAddr("DetailAddr");
        user1.setAuthorities(Role.USER.getKey());

        User user2 = new User();
        user2.setName("spring6");
        user2.setIdentifier("skkgxx5");
        user2.setEmail("12##Z%%ZVVV%$#5@12.com");
        user2.setPassword("1234");
        user2.setAddr("Addr");
        user2.setDetailAddr("DetailAddr");
        user2.setAuthorities(Role.ADMIN.getKey());

        userRepository.save(user1);
        userRepository.save(user2);

        ProductOrder productOrder1 = new ProductOrder();
        productOrder1.setProductOrderStatus(ProductOrderStatus.ARRIVE);
        productOrder1.setCarts(user1.getCarts());
        productOrder1.setUser(user1);

        ProductOrder productOrder2 = new ProductOrder();
        productOrder2.setProductOrderStatus(ProductOrderStatus.WAIT);
        productOrder2.setCarts(user2.getCarts());
        productOrder2.setUser(user2);

        List<ProductOrder> result1 = productOrderRepository.findAllByUserIdOrderByCreatedTimeDesc(user1.getId());

        //when
        productOrderRepository.save(productOrder1);
        productOrderRepository.save(productOrder2);
        productOrderRepository.delete(productOrder2);

        //then
        List<ProductOrder> result2 = productOrderRepository.findAllByUserIdOrderByCreatedTimeDesc(user1.getId());
        assertThat(result1.size()).isEqualTo(result2.size()-1);

        ProductOrder result = productOrderRepository.findById(productOrder1.getId()).get();
        assertThat(result.getId()).isEqualTo(productOrder1.getId());
    }
}