package com.shop.springboot.service;

import com.shop.springboot.entity.Product;
import com.shop.springboot.repository.ProductRepository;
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
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void PRODUCT_CRUD_SERVICE_TEST() {
        //given
        Product product1 = new Product();
        product1.setName("spring5");
        product1.setPrice(10000);
        product1.setDescription("Description");
        product1.setProductImg("Path");
        product1.setDiscount(10);
        product1.setAmount(10000);

        Product product2 = new Product();
        product2.setName("spring5");
        product2.setPrice(10000);
        product2.setDescription("Description");
        product2.setProductImg("Path");
        product2.setDiscount(10);
        product2.setAmount(10000);

        List<Product> result1 = productService.findProducts();

        //when
        productService.save(product1);
        productService.save(product2);
        productService.delete(product2);

        //then
        List<Product> result2 = productService.findProducts();
        assertThat(result1.size()).isEqualTo(result2.size() - 1);

        Product findProduct = productService.findOne(product1.getId()).get();
        assertEquals(product1.getName(), findProduct.getName());
    }
}