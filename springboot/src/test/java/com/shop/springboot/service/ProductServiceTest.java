package com.shop.springboot.service;

import com.shop.springboot.dto.productDto.ProductRequestDto;
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
        ProductRequestDto productRequestDto1 = new ProductRequestDto();
        productRequestDto1.setName("spring5");
        productRequestDto1.setPrice(10000);
        productRequestDto1.setDescription("Description");
        productRequestDto1.setProductImg("Path");
        productRequestDto1.setDiscount(10);
        productRequestDto1.setTotalCount(10000);

        ProductRequestDto productRequestDto2 = new ProductRequestDto();
        productRequestDto2.setName("spring5");
        productRequestDto2.setPrice(10000);
        productRequestDto2.setDescription("Description");
        productRequestDto2.setProductImg("Path");
        productRequestDto2.setDiscount(10);
        productRequestDto2.setTotalCount(10000);


        //when
        Long productId1 = productService.save(productRequestDto1);
        Long productId2 = productService.save(productRequestDto2);
        productService.deleteProduct(productId2);

        //then

        Product findProduct = productService.findById(productId1);
        assertEquals(productRequestDto1.getName(), findProduct.getName());
    }
}