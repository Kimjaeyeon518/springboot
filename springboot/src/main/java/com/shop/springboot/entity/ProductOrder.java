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

    @Enumerated(EnumType.STRING)    // JPA로 데이터베이스로 저장할 때 Enum 값을 어떤 형태로 저장할지를 결정 (기본은 int형)
    private ProductOrderStatus productOrderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;

    public ProductOrderResponseDto toResponseDto() {
        LocalDateTime createdDate = this.getUpdatedTime();

        return ProductOrderResponseDto.builder()
                .id(id)
                .productOrderStatus(productOrderStatus.getTitle())
                .addr(user.getAddr())
                .detailAddr(user.getDetailAddr())
                .createdDate(createdDate.getYear() + "." + createdDate.getMonthValue() + "."
                        + createdDate.getDayOfMonth() + " " + createdDate.getHour() + ":" + createdDate.getMinute() + ":"
                        + createdDate.getSecond())
                .carts(carts)
                .build();
    }

}
