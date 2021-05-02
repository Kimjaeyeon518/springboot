package com.shop.springboot.repository;

import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.ProductOrder;
import com.shop.springboot.entity.User;
import com.shop.springboot.entity.enums.ProductOrderStatus;
import com.shop.springboot.entity.enums.Role;
import com.shop.springboot.exception.NotExistProductException;
import com.shop.springboot.exception.NotExistUserException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Autowired
    private UserRepository userRepository;
    
    // 장바구니 추가
    @Test
    public void CART_CRUD_REPOSITORY_TEST() {
        //given
        User user = new User();
        user.setName("spring5");
        user.setIdentifier("springxx5");
        user.setEmail("12Z^VV#xx%^^4@12.com");
        user.setPassword("1234");
        user.setAddr("Addr");
        user.setDetailAddr("DetailAddr");
        user.setAuthorities(Role.USER.getKey());
        userRepository.save(user);

        Product product1 = new Product();
        product1.setName("spring5");
        product1.setPrice(10000);
        product1.setDescription("Description");
        product1.setProductImg("Path");
        product1.setDiscount(10);
        product1.setTotalCount(10000);

        Product product2 = new Product();
        product2.setName("spring5");
        product2.setPrice(10000);
        product2.setDescription("Description");
        product2.setProductImg("Path");
        product2.setDiscount(10);
        product2.setTotalCount(10000);

        productRepository.save(product1);
        productRepository.save(product2);

        ProductOrder productOrder1 = new ProductOrder();
        productOrder1.setProductOrderStatus(ProductOrderStatus.ARRIVE);
        productOrderRepository.save(productOrder1);

        Cart cart1 = new Cart();
        cart1.setProductCount(0);
        cart1.setProductOrder(productOrder1);
        cart1.setUser(user);
        cart1.setProduct(product1);

        Cart cart2 = new Cart();
        cart2.setProductCount(0);
        cart2.setProductOrder(productOrder1);
        cart2.setUser(user);
        cart2.setProduct(product2);
        List<Cart> result1 = cartRepository.findAllByUserIdOrderByCreatedTimeDesc(user.getId());

        //when
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        cartRepository.delete(cart2);

        //then
        List<Cart> result2 = cartRepository.findAllByUserIdOrderByCreatedTimeDesc(user.getId());
        assertThat(result1.size()).isEqualTo(result2.size()-1);

        Cart result = cartRepository.findById(cart1.getId()).get();
        assertThat(result.getId()).isEqualTo(cart1.getId());
    }


}