package com.shop.springboot.dto.CartDto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class CartRequestDto {

    @NotNull(message = "userId가 NULL 입니다.")
    private Long userId;

    @NotNull(message = "존재하지 않는 상품 입니다.")
    private Long productId;
}
