package com.shop.springboot.service;

import static org.junit.jupiter.api.Assertions.*;

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
        ProductRequestDto productRequestDto1
                = new ProductRequestDto("productName1", "Description1", 100000, "Path",
                "OUTER", 1000, 10);

        ProductRequestDto productRequestDto2
                = new ProductRequestDto("productName2", "Description1", 100000, "Path",
                "OUTER", 1000, 10);


        //when
        productService.save(productRequestDto1);
        productService.save(productRequestDto2);
//        productService.deleteProduct(productId2);
//
//  //       then
//
//        Product findProduct = productService.findById(productId1);
//        assertEquals(productRequestDto1.getName(), findProduct.getName());
    }
}