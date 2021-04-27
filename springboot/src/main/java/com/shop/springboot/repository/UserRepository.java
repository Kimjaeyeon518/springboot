package com.shop.springboot.repository;

import com.shop.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long userId);
    Optional<User> findByIdentifier(String identifier);
    boolean existsByIdentifier(String identifier);
    boolean existsByEmail(String email);
}
