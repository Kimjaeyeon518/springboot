package com.shop.springboot.repository;

import com.shop.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    // 상품 등록
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
        productRepository.save(product);

        //then
        Product result = productRepository.findById(product.getId()).get();
        assertThat(result.getId()).isEqualTo(product.getId());
    }

    // 상품 리스트 조회
    @Test
    public void findAll() {

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

        List<Product> result1 = productRepository.findAll();

        //when
        productRepository.save(product1);
        productRepository.save(product2);

        //then
        List<Product> result2 = productRepository.findAll();
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
        productRepository.save(product);

        //then
        Product result = productRepository.findById(product.getId()).get();
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
        List<Product> result1 = productRepository.findAll();


        //when
        productRepository.save(product);
        productRepository.delete(product);

        //then
        List<Product> result2 = productRepository.findAll();
        assertThat(result1.size()).isEqualTo(result2.size());
    }

}