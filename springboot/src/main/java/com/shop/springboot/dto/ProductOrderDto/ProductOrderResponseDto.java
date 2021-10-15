package com.shop.springboot.dto.ProductOrderDto;

import com.shop.springboot.entity.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class ProductOrderResponseDto {

    private Long id;
    private String addr;
    private String detailAddr;
    private String createdDate;
    private List<Cart> carts;
}