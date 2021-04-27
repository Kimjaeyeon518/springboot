package com.shop.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.springboot.dto.CartDto.CartResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer productCount;      // cart 안의 상품 개수
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_order_id", referencedColumnName = "id")
    @JsonIgnore
    private ProductOrder productOrder;

    public CartResponseDto toResponseDto(Cart cart) {

        return CartResponseDto.builder()
                .id(cart.id)
                .user(cart.user)
                .product(cart.product)
                .productCount(cart.productCount)
                .build();
    }
}
