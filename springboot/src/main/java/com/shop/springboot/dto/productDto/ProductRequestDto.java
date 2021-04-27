package com.shop.springboot.dto.productDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
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

    @NotBlank(message = "타이틀 이미지 경로를 작성하세요.")
    @Size(max = 200, message = "타이틀 이미지 경로를 알맞게 작성해주세요.")
    private String productImg;

    @NotBlank(message = "카테고리를 작성하세요.")
    @Size(max = 50, message = "카테고리를 알맞게 작성해주세요.")
    private String category;

    @NotNull(message = "상품 재고를 작성하세요.")
    private Integer totalCount;

    @NotNull(message = "상품 할인율을 작성하세요.")
    private Integer discount;

}
