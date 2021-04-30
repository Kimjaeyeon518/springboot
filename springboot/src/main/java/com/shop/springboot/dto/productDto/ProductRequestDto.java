package com.shop.springboot.dto.productDto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class ProductRequestDto {

    @NotBlank(message = "상품명을 작성하세요.")
    @Size(max = 200, message = "상품명을 알맞게 작성해주세요.")
    private String name;
    
    @NotBlank(message = "상품 설명을 작성하세요.")
    @Size(max = 200, message = "상품 설명을 알맞게 작성해주세요.")
    private String description;
    
    @NotNull(message = "상품 가격을 작성하세요.")
    private Integer price;

    private String productImg;

    @NotBlank(message = "카테고리를 작성하세요.")
    @Size(max = 50, message = "카테고리를 알맞게 작성해주세요.")
    private String category;

    @NotNull(message = "상품 입고를 작성하세요.")
    private Integer limitCount;

    @NotNull(message = "상품 할인율을 작성하세요.")
    private Integer discount;

}
