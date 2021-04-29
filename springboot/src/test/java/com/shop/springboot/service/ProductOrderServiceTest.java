package com.shop.springboot.service;

import com.shop.springboot.dto.ProductOrderDto.ProductOrderResponseDto;
import com.shop.springboot.dto.productDto.ProductResponseDto;
import com.shop.springboot.entity.ProductOrder;
import com.shop.springboot.entity.User;
import com.shop.springboot.entity.enums.ProductOrderStatus;
import com.shop.springboot.repository.CartRepository;
import com.shop.springboot.repository.ProductOrderRepository;
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
class ProductOrderServiceTest {


    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductOrderService productOrderService;

    @Test
    public void PRODUCT_ORDER_CRUD_SERVICE_TEST() {
        //given
        User user1 = userService.findOne(12l).get();
        User user2 = userService.findOne(58l).get();

        ProductOrder productOrder1 = new ProductOrder();
        productOrder1.setProductOrderStatus(ProductOrderStatus.ARRIVE);
        productOrder1.setCarts(user1.getCarts());
        productOrder1.setUser(user1);

        ProductOrder productOrder2 = new ProductOrder();
        productOrder2.setProductOrderStatus(ProductOrderStatus.WAIT);
        productOrder2.setCarts(user2.getCarts());
        productOrder2.setUser(user2);

        ProductOrderResponseDto productOrderResponseDto1 = productOrder1.toResponseDto();
        ProductOrderResponseDto productOrderResponseDto2 = productOrder2.toResponseDto();

        List<ProductOrder> result1 = productOrderService.findProductOrdersList(user1.getId());

        //when
        Long orderId1 = productOrderService.save(productOrder1);
        Long orderId2 = productOrderService.save(productOrder2);
        productOrderService.delete(productOrder2.getId());

        //then
        List<ProductOrder> result2 = productOrderService.findProductOrdersList(user1.getId());
        assertThat(result1.size()).isEqualTo(result2.size()-1);

        ProductOrderResponseDto result = productOrderService.findOne(orderId1);
        assertThat(result.getId()).isEqualTo(productOrder1.getId());
    }
}