package com.shop.springboot.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.springboot.dto.productDto.ProductResponseDto;
import com.shop.springboot.entity.enums.Category;
import com.shop.springboot.entity.enums.ProductStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor  // 기본 생성자
@Getter
@Setter
@Entity // 테이블과 링크될 클래스
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column(length = 150)
    private String productImg;
    @Column
    private String description;
    @Column
    private Integer price;
    @Column
    private Integer discount;
    @Column
    private Integer limitCount;     // 상품 입고
    @Column
    private Integer totalCount;     // 상품 재고
    @Column
    private Integer buyCount;     // 상품 구매 횟수
    @Column
    private String category;
    @Column
    private Character disabledYn;

    @Enumerated(EnumType.STRING)    // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정 (기본은 int형)
    private ProductStatus productStatus;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProductImg> productImgList;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cart> carts;

    public Product(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.productImg = product.getProductImg();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.discount = product.getDiscount();
        this.limitCount = product.getLimitCount();
        this.totalCount = product.getTotalCount();
        this.buyCount = product.getBuyCount();
        this.category = product.getCategory();
    }

    public ProductResponseDto toResponseDto() {

        return ProductResponseDto.builder()
                .id(id)
                .name(name)
                .category(category)
                .price(price)
                .discount(discount)
                .buyCount(buyCount)
                .limitCount(limitCount)
                .totalCount(totalCount)
                .productStatus(productStatus)
                .productImg(productImg)
                .build();
    }

    public ProductResponseDto.MainProductResponseDto toMainProductResponseDto() {

        return ProductResponseDto.MainProductResponseDto.builder()
                .id(id)
                .name(name)
                .productImg(productImg)
                .price(price)
                .discount(discount)
                .buyCount(buyCount)
                .build();
    }

    public ProductResponseDto.AdminProductResponseDto toAdminProductResponseDto() {

        return ProductResponseDto.AdminProductResponseDto.builder()
                .id(id)
                .name(name)
                .productImg(productImg)
                .price(price)
                .discount(discount)
                .buyCount(buyCount)
                .totalCount(totalCount)
                .build();
    }

}
