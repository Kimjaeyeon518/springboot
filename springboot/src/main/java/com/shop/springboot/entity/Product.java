package com.shop.springboot.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.springboot.entity.enums.Category;
import com.shop.springboot.entity.enums.ProductStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Integer price;
    @Column
    private Integer discount;
    @Column
    private Float discount_rate;
    @Column
    private Integer count;     // 상품 재고
    @Column
    private String productImg;
    @Column
    private String category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cart> carts;

//    public Product(Product product) {
//        this.id = product.getId();
//        this.name = product.getName();
//        this.price = product.getPrice();
//        this.description = product.getDescription();
//        this.discount = product.getDiscount();
//        this.limitCount = product.getLimitCount();
//        this.totalCount = product.getTotalCount();
//        this.buyCount = product.getBuyCount();
//        this.category = product.getCategory();
//    }
//
//    public ProductResponseDto toResponseDto() {
//
//        return ProductResponseDto.builder()
//                .id(id)
//                .name(name)
//                .category(category)
//                .price(price)
//                .discount(discount)
//                .buyCount(buyCount)
//                .limitCount(limitCount)
//                .totalCount(totalCount)
//                .productStatus(productStatus)
//                .build();
//    }
//
//    public ProductResponseDto.MainProductResponseDto toMainProductResponseDto() {
//
//        return ProductResponseDto.MainProductResponseDto.builder()
//                .id(id)
//                .name(name)
//                .price(price)
//                .discount(discount)
//                .buyCount(buyCount)
//                .build();
//    }
//
//    public ProductResponseDto.AdminProductResponseDto toAdminProductResponseDto() {
//
//        return ProductResponseDto.AdminProductResponseDto.builder()
//                .id(id)
//                .name(name)
//                .price(price)
//                .discount(discount)
//                .buyCount(buyCount)
//                .totalCount(totalCount)
//                .build();
//    }

}
