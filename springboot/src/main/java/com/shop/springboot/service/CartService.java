package com.shop.springboot.service;

import com.shop.springboot.dto.CartDto.CartRequestDto;
import com.shop.springboot.dto.CartDto.CartResponseDto;
import com.shop.springboot.dto.pagingDto.PagingDto;
import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.User;
import com.shop.springboot.entity.enums.ProductStatus;
import com.shop.springboot.exception.*;
import com.shop.springboot.repository.CartRepository;
import com.shop.springboot.repository.ProductRepository;
import com.shop.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class CartService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    //  장바구니 생성
    public Long addCart(CartRequestDto cartRequestDto) {

        Long userId = cartRequestDto.getUserId();
        Long productId = cartRequestDto.getProductId();

        if(cartRepository.exists(userId, productId) == null) {  // 장바구니에 해당 상품이 없다면
            return cartRepository.save(Cart.builder()
                    .user(userRepository.findById(userId).get())
                    .product(productRepository.findById(productId).get())
                    .count(cartRequestDto.getCount())
                    .build())
                    .getId();
        }
        else {
            throw new DuplicatedException("이미 동일한 상품이 장바구니에 있습니다.");
        }
    }

    // 장바구니 리스트 조회
    public List<Cart> findAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    // 장바구니 삭제
    public void delete(Long cartId) {
        if (cartRepository.exists(cartId) == null) {
            throw new NotExistCartException("존재하지 않는 장바구니 입니다.");
        }
        cartRepository.deleteById(cartId);
    }

    // 주문 리스트 조회    ->  TEST 용
    public List<Cart> findCartsList(Long userId) {
        return cartRepository.findAllByUserIdOrderByCreatedTimeDesc(userId);
    }

    public Cart findOneCart(Long cartId) {
        return cartRepository.findById(cartId).get();
    }
}
