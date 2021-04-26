package com.shop.springboot.repository;

import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.ProductOrder;
import com.shop.springboot.entity.User;
import com.shop.springboot.entity.enums.ProductOrderStatus;
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
        User user1 = userRepository.findById(12l).get();
        User user2 = userRepository.findById(14l).get();

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