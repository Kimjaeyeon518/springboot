package com.shop.springboot.service;

import com.shop.springboot.dto.CartDto.CartRequestDto;
import com.shop.springboot.dto.ProductOrderDto.ProductOrderResponseDto;
import com.shop.springboot.dto.productDto.ProductRequestDto;
import com.shop.springboot.dto.userDto.UserRequestDto;
import com.shop.springboot.entity.Cart;
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
class CartServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;

    @Test
    public void CART_CRUD_SERVICE_TEST() {
        // given
        UserRequestDto userRequestDto1
                = new UserRequestDto("spring5","qwergzczv5"
                ,"ROLE_USER", "1234"
                , "~~~12@12454@1.com", "Addr"
                , "Addr");

        Long uid = userService.userRegistration(userRequestDto1);

        ProductRequestDto productRequestDto1
                = new ProductRequestDto("productName1", "Description1", 100000, "Path",
                "OUTER", 1000, 10);

        ProductRequestDto productRequestDto2
                = new ProductRequestDto("productName2", "Description2", 200000, "Path",
                "OUTER", 2000, 20);

        Long pid1 = productService.save(productRequestDto1);
        Long pid2 = productService.save(productRequestDto2);

        CartRequestDto cartRequestDto1
                = new CartRequestDto(uid, pid1, 1);
        CartRequestDto cartRequestDto2
                = new CartRequestDto(uid, pid2, 2);

        //when
        Long cid1 = cartService.addCart(cartRequestDto1);
        Long cid2 = cartService.addCart(cartRequestDto2);

        //then
        List<Cart> result = cartService.findAllByUserId(uid);
        for(Cart cart : result) {
            System.out.println(cart.getProduct().getName());
        }
//        assertThat(result.getId()).isEqualTo(cartId1);
    }
}