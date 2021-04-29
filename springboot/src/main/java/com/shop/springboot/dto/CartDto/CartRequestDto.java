package com.shop.springboot.dto.CartDto;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class CartRequestDto {

    private Long userId;
    private Long productId;
}
