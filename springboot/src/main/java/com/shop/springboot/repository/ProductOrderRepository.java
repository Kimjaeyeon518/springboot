package com.shop.springboot.repository;

import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.ProductOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long>  {
    Optional<ProductOrder> findById(Long productOrderId);
    Page<ProductOrder> findAllByUserIdOrderByCreatedTimeDesc(Long userId, Pageable pageable);

    List<ProductOrder> findAllByUserIdOrderByCreatedTimeDesc(Long userId);
}
