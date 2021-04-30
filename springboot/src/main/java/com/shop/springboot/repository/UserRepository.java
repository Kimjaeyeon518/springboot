package com.shop.springboot.repository;

import com.shop.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u ORDER BY u.id DESC")
    List<User> findAll();

    Optional<User> findById(Long userId);
    Optional<User> findByIdentifier(String identifier);
    boolean existsByIdentifier(String identifier);
    boolean existsByEmail(String email);
}
