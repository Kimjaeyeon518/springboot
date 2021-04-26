package com.shop.springboot.service;

import com.shop.springboot.dto.ProductOrderDto.ProductOrderResponseDto;
import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.ProductOrder;
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
class CartServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductOrderService productOrderService;

    @Test
    public void CART_CRUD_SERVICE_TEST() {
        //given
        User user = userService.findOne(12l).get();
        Product product1 = productService.findOne(3l).get();
        Product product2 = productService.findOne(4l).get();
        ProductOrder productOrder = productOrderService.findOneProductOrder(1l);

        product1.setLimitCount(100);
        product1.setAmount(10);
        product1.setPrice(10000);

        product2.setLimitCount(100);
        product2.setAmount(10);
        product2.setPrice(20000);

        productService.save(product1);
        productService.save(product2);

        Cart cart1 = new Cart();
        cart1.setProductCount(1);
        cart1.setProductOrder(productOrder);
        cart1.setUser(user);
        cart1.setProduct(product1);

        Cart cart2 = new Cart();
        cart2.setProductCount(3);
        cart2.setProductOrder(productOrder);
        cart2.setUser(user);
        cart2.setProduct(product2);
        List<Cart> result1 = cartService.findCartsList(user.getId());

        //when
        cartService.save(cart1);
        cartService.save(cart2);
        cartService.delete(cart2.getId());

        //then
        List<Cart> result2 = cartService.findCartsList(user.getId());
        assertThat(result1.size()).isEqualTo(result2.size()-1);

        Cart result = cartService.findOneCart(cart1.getId());
        assertThat(result.getId()).isEqualTo(cart1.getId());
    }
}