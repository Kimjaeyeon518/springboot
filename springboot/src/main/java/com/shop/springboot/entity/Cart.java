package com.shop.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.springboot.dto.CartDto.CartResponseDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor  // 기본 생성자
@Getter
@Setter
@Entity // 테이블과 링크될 클래스
@Builder
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer productCount;      // cart 안의 상품 개수

    @Column
    private Integer totalPrice;      // 총 가격

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column
    private Character disabledYn;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_order_id", referencedColumnName = "id")
    @JsonIgnore
    private ProductOrder productOrder;

    public Cart(Cart cart) {
        this.id = cart.getId();
        this.user = cart.getUser();
        this.product = cart.getProduct();
        this.productCount = cart.getProductCount();
    }

    public Cart disabledCart() {
        disabledYn = 'Y';
        return this;
    }
}
