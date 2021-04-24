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
    public void save() {
        //given
        Product product = new Product();
        product.setName("spring5");
        product.setPrice(10000);
        product.setDescription("Description");
        product.setProductImg("Path");
        product.setDiscount(10);
        product.setAmount(10000);

        //when
        Long saveId = productService.save(product);

        //then
        Product findProduct = productService.findOne(saveId).get();
        assertEquals(product.getName(), findProduct.getName());
    }

    // 상품 리스트 조회
    @Test
    public void findProducts() {

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

        //then
        List<Product> result2 = productService.findProducts();
        assertThat(result1.size()).isEqualTo(result2.size()-2);
    }

    // 상품 조회
    @Test
    public void findOne() {
        //given
        Product product = new Product();
        product.setName("spring5");
        product.setPrice(10000);
        product.setDescription("Description");
        product.setProductImg("Path");
        product.setDiscount(10);
        product.setAmount(10000);

        //when
        productService.save(product);

        //then
        Product result = productService.findOne(product.getId()).get();
        assertThat(result.getId()).isEqualTo(product.getId());
    }

    // 상품 삭제
    @Test
    public void delete() {
        //given
        Product product = new Product();
        product.setName("spring5");
        product.setPrice(10000);
        product.setDescription("Description");
        product.setProductImg("Path");
        product.setDiscount(10);
        product.setAmount(10000);
        List<Product> result1 = productService.findProducts();


        //when
        productService.save(product);
        productService.delete(product);

        //then
        List<Product> result2 = productService.findProducts();
        assertThat(result1.size()).isEqualTo(result2.size());
    }
}