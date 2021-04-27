package com.shop.springboot.dto.productDto;

import com.shop.springboot.entity.enums.ProductStatus;
import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto implements Serializable {

    private Long id;
    private String name;
    private String productImg;
    private String description;
    private String category;
    private Integer price;
    private Integer discount;
    private Integer buyCount;
    private Integer limitCount;
    private Integer totalCount;
    private ProductStatus productStatus;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MainProductResponseDto implements Serializable {
        private Long id;
        private String name;
        private String productImg;
        private Integer price;
        private Integer discount;
        private Integer buyCount;
        private Long timestamp;
    }

    @Getter
    @Builder
    @ToString
    public static class AdminProductResponseDto {
        private Long id;
        private String name;
        private String productImg;
        private Integer price;
        private Integer discount;
        private Integer buyCount;
        private Integer totalCount;
    }

    @Getter
    @Builder
    @ToString
    public static class AdminProductDetailResponseDto {
        private Long id;
        private String name;
        private String productImg;
        private Integer price;
        private Integer discount;
        private String category;
        private Integer totalCount;
    }
}