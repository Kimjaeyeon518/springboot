package com.shop.springboot.repository;

import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long productId);

    @Query(value = "SELECT p FROM Product p ORDER BY p.totalCount ASC")
    List<Product> findAll();

    @Query(value = "SELECT p FROM Product p WHERE p.category=:category ORDER BY p.id DESC")
    Page<Product> findAllByCategory(Pageable pageable, @Param("category") String category);

    Page<Product> findAllByNameContaining(Pageable pageable, String keyword);
}
