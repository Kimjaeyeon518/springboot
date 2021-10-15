//package com.shop.springboot.entity;
//
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.util.List;
//

//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Entity;
//
//@NoArgsConstructor
//@Getter
//@Builder
//@Entity
//public class ProductOrder extends BaseTimeEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column
//    private Integer totalPrice;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;
//
//    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Cart> carts;
//
//}
