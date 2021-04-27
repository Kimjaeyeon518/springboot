package com.shop.springboot.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.springboot.entity.enums.Category;
import com.shop.springboot.entity.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor  // 기본 생성자
@Getter
@Setter
@Entity // 테이블과 링크될 클래스
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private String productImg;
    @Column
    private String description;
    @Column
    private Integer price;
    @Column
    private Integer discount;
    @Column
    private Integer limitCount = 2000;     // 상품 재고
    @Column
    private Integer amount;     // 상품 구매 개수
    @Column
    private Integer buyCount = 0;   // 상품 구매 횟수

    @Enumerated(EnumType.STRING)    // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정 (기본은 int형)
    private ProductStatus productStatus;

    @Enumerated(EnumType.STRING)    // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정 (기본은 int형)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Cart> carts;

}
