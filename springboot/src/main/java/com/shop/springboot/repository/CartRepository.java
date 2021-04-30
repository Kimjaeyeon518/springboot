package com.shop.springboot.repository;

import com.shop.springboot.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>  {
    Optional<Cart> findById(Long cartId);

    List<Cart> findAllByUserId(Long userId);

    List<Cart> findAllByUserIdOrderByCreatedTimeDesc(Long userId);

    @Query(value = "SELECT c.id FROM Cart c WHERE c.user.id=:userId AND c.product.id=:productId")
    Long findAllByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
}
