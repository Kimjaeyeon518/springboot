package com.shop.springboot.service;

import com.shop.springboot.dto.CartDto.CartRequestDto;
import com.shop.springboot.dto.CartDto.CartResponseDto;
import com.shop.springboot.dto.pagingDto.PagingDto;
import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.ProductOrder;
import com.shop.springboot.entity.User;
import com.shop.springboot.entity.enums.ProductStatus;
import com.shop.springboot.exception.*;
import com.shop.springboot.repository.CartRepository;
import com.shop.springboot.repository.ProductRepository;
import com.shop.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void addCart(CartRequestDto cartRequestDto) {

        Optional<User> user = userRepository.findById(cartRequestDto.getUserId());
        Optional<Product> product = productRepository.findById(cartRequestDto.getProductId());
        Long duplicateProduct = 0l;
        duplicateProduct = cartRepository.findAllByUserIdAndProductId(cartRequestDto.getUserId(), cartRequestDto.getProductId());

        if(duplicateProduct == null) {
            cartRepository.save(Cart.builder()
                    .user(user.get())
                    .product(product.get())
                    .productCount(1)
                    .disabledYn('N')
                    .totalPrice(product.get().getPrice() - (product.get().getPrice() * product.get().getDiscount()) / 100)
                    .build());
        }

        // 중복된 상품이 이미 장바구니에 있는 경우
        else {
            Optional<Cart> cart = cartRepository.findById(duplicateProduct);
            cart.get().setProductCount(cart.get().getProductCount() + 1);
            cart.get().setTotalPrice((product.get().getPrice() - (product.get().getPrice() * product.get().getDiscount()) / 100)
                    * cart.get().getProductCount());

            cartRepository.save(Cart.builder()
                    .user(user.get())
                    .product(product.get())
                    .productCount(1)
                    .build());
        }
    }

    // 장바구니 리스트 조회
    public List<Cart> findAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId).stream()
                .map(Cart::new)
                .collect(Collectors.toList());
    }

    // 장바구니 삭제
    public void delete(Long cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);

        if (!cartOpt.isPresent()) {
            throw new NotExistCartException("존재하지 않는 장바구니 입니다.");
        }
        Cart cart = cartOpt.get();
        cartRepository.delete(cart);

    }

    // 주문 리스트 조회    ->  TEST 용
    public List<Cart> findCartsList(Long userId) {
        return cartRepository.findAllByUserIdOrderByCreatedTimeDesc(userId);

    }

    public Cart findOneCart(Long cartId) {
        return cartRepository.findById(cartId).get();
    }
}
