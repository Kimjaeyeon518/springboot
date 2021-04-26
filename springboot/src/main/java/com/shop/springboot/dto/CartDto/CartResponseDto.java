package com.shop.springboot.dto.CartDto;

import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CartResponseDto {

    private Long id;
    private User user;
    private Product product;
    private Integer productCount;
}