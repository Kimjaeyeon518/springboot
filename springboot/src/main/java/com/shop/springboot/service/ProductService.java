package com.shop.springboot.service;

import com.shop.springboot.entity.Product;
import com.shop.springboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional      // Service 의 모든 메소드의 트랜잭션 처리
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 등록
    public Long save(Product product) {
        return productRepository.save(product).getId();
    }

    // 상품 리스트 조회
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    // 상품 조회
    public Optional<Product> findOne(Long productId) {
        return productRepository.findById(productId);
    }

    // 상품 삭제
    public void delete(Product product) {
        productRepository.delete(product);
    }
}
