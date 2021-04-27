package com.shop.springboot.service;

import com.shop.springboot.dto.productDto.ProductRequestDto;
import com.shop.springboot.dto.productDto.ProductResponseDto;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.enums.ProductStatus;
import com.shop.springboot.entity.enums.Role;
import com.shop.springboot.exception.NoValidProductSortException;
import com.shop.springboot.exception.NotExistProductException;
import com.shop.springboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Transactional      // Service 의 모든 메소드의 트랜잭션 처리
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
@Service
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 조회
    public Product findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        return product;
    }


    // 상품 등록
    public Long save(ProductRequestDto productRequestDto) {

        Product product = new Product();

        product.setName(productRequestDto.getName());
        product.setCategory(productRequestDto.getCategory());
        product.setDescription(productRequestDto.getDescription());
        product.setDiscount(productRequestDto.getDiscount());
        product.setPrice(productRequestDto.getPrice());;
        product.setProductImg(productRequestDto.getProductImg());
        product.setProductStatus(ProductStatus.SALE);

        return productRepository.save(product).getId();
    }

    // 카테고리로 상품 리스트 조회
    public Page<Product> getProductList(Pageable pageable, String category) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "id"); // <- Sort 추가

        return productRepository.findAllByCategory(pageable, category);
    }

    // 관리자 상품 상세조회
    public ProductResponseDto.AdminProductDetailResponseDto getAdminProductDetails(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);

        if (!productOpt.isPresent())
            throw new NotExistProductException("존재하지 않는 상품입니다.");

        Product product = productOpt.get();

        return ProductResponseDto.AdminProductDetailResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .productImg(product.getProductImg())
                .category(product.getCategory())
                .totalCount(product.getTotalCount())
                .build();
    }

    // 상품 정보 수정
    public Long updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + id));

        product.setName(productRequestDto.getName());
        product.setCategory(productRequestDto.getCategory());
        product.setDescription(productRequestDto.getDescription());
        product.setDiscount(productRequestDto.getDiscount());
        product.setPrice(productRequestDto.getPrice());
        product.setProductImg(productRequestDto.getProductImg());
        product.setProductStatus(ProductStatus.SALE);

        return productRepository.save(product).getId();
    }

    // 상품 삭제
    public void deleteProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. id=" + productId));

        productRepository.delete(product);
    }

    // 상품 검색
    public Page<Product> searchProduct(Pageable pageable, String keyword) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "id"); // <- Sort 추가

        return productRepository.findAllByNameContaining(pageable, keyword);
    }
}
