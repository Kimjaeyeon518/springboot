package com.shop.springboot.entity;

import com.shop.springboot.dto.ProductOrderDto.ProductOrderResponseDto;
import com.shop.springboot.entity.enums.ProductOrderStatus;
import com.shop.springboot.entity.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class ProductOrder extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer totalPrice;

    @Enumerated(EnumType.STRING)    // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정 (기본은 int형)
    private ProductOrderStatus productOrderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;

    public ProductOrder(ProductOrder productOrder) {
        this.id = productOrder.getId();
        this.productOrderStatus = productOrder.getProductOrderStatus();
        this.user = productOrder.getUser();
        this.carts = productOrder.getCarts();
        this.totalPrice = productOrder.getTotalPrice();
    }

}
