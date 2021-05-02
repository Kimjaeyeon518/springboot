package com.shop.springboot.service;

import com.shop.springboot.dto.CartDto.CartRequestDto;
import com.shop.springboot.dto.ProductOrderDto.ProductOrderResponseDto;
import com.shop.springboot.dto.productDto.ProductRequestDto;
import com.shop.springboot.dto.productDto.ProductResponseDto;
import com.shop.springboot.dto.userDto.UserRequestDto;
import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
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
    @Autowired
    private ProductService productService;

    @Test
    public void PRODUCT_ORDER_CRUD_SERVICE_TEST() {
        //given

        UserRequestDto userRequestDto1
                = new UserRequestDto("spring5","asdf77ng5"
                ,"ROLE_USER", "1234"
                , "~~~12@12454@1.com", "Addr"
                , "Addr");
        UserRequestDto userRequestDto2
                = new UserRequestDto("spring5","zxcv88ng5"
                ,"ROLE_USER", "1234"
                , "~~~12@12$$454@1.com", "Addr"
                , "Addr");
        Long userId1 = userService.userRegistration(userRequestDto1);
        Long userId2 = userService.userRegistration(userRequestDto2);

        ProductRequestDto productRequestDto1
                = new ProductRequestDto("productName1", "Description1", 100000, "Path",
                "OUTER", 1000, 10);

        ProductRequestDto productRequestDto2
                = new ProductRequestDto("productName2", "Description1", 100000, "Path",
                "OUTER", 1000, 10);


        //when
        Long productId1 = productService.save(productRequestDto1);
        Long productId2 = productService.save(productRequestDto2);

        CartRequestDto cartRequestDto1
                = new CartRequestDto(userId1, productId1);
        CartRequestDto cartRequestDto2
                = new CartRequestDto(userId2, productId2);

        //when
        Long cartId1 = cartService.addCart(cartRequestDto1);
        Long cartId2 = cartService.addCart(cartRequestDto2);

        User user1 = userService.findOne(userId1).get();
        User user2 = userService.findOne(userId2).get();

        ProductOrder productOrder1 = new ProductOrder();
        productOrder1.setProductOrderStatus(ProductOrderStatus.ARRIVE);
        productOrder1.setCarts(user1.getCarts());
        productOrder1.setUser(user1);

        ProductOrder productOrder2 = new ProductOrder();
        productOrder2.setProductOrderStatus(ProductOrderStatus.WAIT);
        productOrder2.setCarts(user2.getCarts());
        productOrder2.setUser(user2);

        List<ProductOrder> result1 = productOrderService.findProductOrdersList(user1.getId());

        //when
        Long orderId1 = productOrderService.save(productOrder1.getUser().getId());
        Long orderId2 = productOrderService.save(productOrder2.getUser().getId());
        productOrderService.delete(orderId2);

        //then
        List<ProductOrder> result2 = productOrderService.findProductOrdersList(user1.getId());
        assertThat(result1.size()).isEqualTo(result2.size()-1);

        ProductOrder result = productOrderService.findOneProductOrder(orderId1);
        assertThat(result.getId()).isEqualTo(orderId1);
    }
}