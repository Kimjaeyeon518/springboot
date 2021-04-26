package com.shop.springboot.service;

import com.shop.springboot.dto.CartDto.CartRequestDto;
import com.shop.springboot.dto.CartDto.CartResponseDto;
import com.shop.springboot.dto.pagingDto.PagingDto;
import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.ProductOrder;
import com.shop.springboot.entity.User;
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
    public Long save(Cart cart) {
        User user = userRepository.findById(cart.getUser().getId()).orElseThrow(()
                -> new NotExistUserException("존재하지 않는 유저입니다."));

        Optional<Product> productOpt = productRepository.findById(cart.getProduct().getId());

        if(!productOpt.isPresent())
            throw new NotExistProductException("존재하지 않는 상품입니다.");

        Product product = productOpt.get();

        if(product.getLimitCount() < cart.getProduct().getAmount())
            throw new ProductLimitCountException("재고가 없습니다.");

//        if(product.getId() == cart.getProduct().getId())
//            throw new DuplicatedException("장바구니에 이미 상품이 존재합니다.");


        return cartRepository.save(cart).getId();

    }

    // 장바구니 리스트 조회
    public HashMap<String, Object> findCarts(Long userId, int page, Pageable pageable) {
        int realPage = page - 1;
        pageable = PageRequest.of(realPage, 5);

        Page<Cart> cartList = cartRepository.findAllByUserIdOrderByCreatedTimeDesc(userId, pageable);

        if (cartList.getTotalElements() > 0) {
            List<CartResponseDto> cartResponseDtoList = new ArrayList<>();

            for (Cart cart : cartList) {

                cartResponseDtoList.add(cart.toResponseDto(cart));
            }

            PageImpl<CartResponseDto> cartLists = new PageImpl<>(cartResponseDtoList, pageable, cartList.getTotalElements());

            PagingDto cartPagingDto = new PagingDto();
            cartPagingDto.setPagingInfo(cartLists);

            List<Cart> carts = cartRepository.findAllByUserIdOrderByCreatedTimeDesc(userId);
            int checkoutPrice = 0;
            List<Long> cartIdList = new ArrayList<>();

            for (Cart cart : carts) {
                cartIdList.add(cart.getId());

                int salePrice = (int)((((float) 100 - (float)cart.getProduct().getDiscount()) / (float)100) * cart.getProduct().getPrice());

                checkoutPrice += salePrice * cart.getProductCount();
            }

            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("cartList", cartLists);
            resultMap.put("cartPagingDto", cartPagingDto);
            resultMap.put("checkoutPrice", checkoutPrice);
            resultMap.put("cartIdList", cartIdList);

            return resultMap;
        }
        return null;
    }

    // 장바구니 삭제
    public void delete(Long cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);

        if (!cartOpt.isPresent()) {
            throw new NotExistCartException("존재하지 않는 장바구니 입니다.");
        }

        cartRepository.delete(cartOpt.get());

    }

    // 주문 리스트 조회    ->  TEST 용
    public List<Cart> findCartsList(Long userId) {
        return cartRepository.findAllByUserIdOrderByCreatedTimeDesc(userId);

    }

    public Cart findOneCart(Long cartId) {
        return cartRepository.findById(cartId).get();
    }
}
