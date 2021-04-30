package com.shop.springboot.entity;

import com.shop.springboot.dto.ProductOrderDto.ProductOrderResponseDto;
import com.shop.springboot.entity.enums.ProductOrderStatus;
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
    private String productOrderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;

    public ProductOrderResponseDto toResponseDto() {
        LocalDateTime createdDate = this.getUpdatedTime();

        return ProductOrderResponseDto.builder()
                .id(id)
                .productOrderStatus(ProductOrderStatus.WAIT.getKey())
                .addr(user.getAddr())
                .detailAddr(user.getDetailAddr())
                .carts(carts)
                .build();
    }

}
