package com.shop.springboot.repository;

import com.shop.springboot.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>  {
    Optional<Cart> findById(Long cartId);

    Page<Cart> findAllByUserIdOrderByCreatedTimeDesc(Long userId, Pageable pageable);

    List<Cart> findAllByUserIdOrderByCreatedTimeDesc(Long userId);

    List<Cart> findAllByUserIdAndProductId(Long userId, Long productId);
}
