package com.shop.springboot.dto.CartDto;

import com.shop.springboot.entity.Cart;
import com.shop.springboot.repository.ProductRepository;
import com.shop.springboot.repository.UserRepository;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CartRequestDto {

    @NotNull(message = "userId가 NULL 입니다.")
    private Long userId;

    @NotNull(message = "존재하지 않는 상품 입니다.")
    private Long productId;

    @NotNull(message = "존재하지 않는 상품 입니다.")
    private Integer count;

}
