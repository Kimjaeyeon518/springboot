package com.shop.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor  // 기본 생성자
@Getter
@Setter
@Entity // 테이블과 링크될 클래스
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String productImg;
    private String description;
    private Integer price;
    private Integer discount;
    private Integer amount;
    private Integer buyCount = 0;

}
