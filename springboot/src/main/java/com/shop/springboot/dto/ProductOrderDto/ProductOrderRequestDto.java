package com.shop.springboot.dto.ProductOrderDto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class ProductOrderRequestDto {

    @NotNull(message = "productOrderStatus NULL 입니다.")
    private String productOrderStatus;

    @NotNull(message = "userId가 NULL 입니다.")
    private Long userId;
}
