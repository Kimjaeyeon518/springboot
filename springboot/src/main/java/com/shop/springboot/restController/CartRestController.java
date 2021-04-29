package com.shop.springboot.restController;

import com.shop.springboot.dto.CartDto.CartRequestDto;
import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.exception.ResourceNotFoundException;
import com.shop.springboot.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
public class CartRestController {

    private final CartService cartService;

//    //  장바구니 담기
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//    @PostMapping("/product/cart")
//    public Long addCart(@RequestBody CartRequestDto cartRequestDto) {
//        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        System.out.println("USER ID == " + cartRequestDto.getUserId());
//        System.out.println("PRODUCT ID == " + cartRequestDto.getProductId());
//        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//        return cartService.addCart(cartRequestDto);
//    }
//
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//    @DeleteMapping("/cart/{cartId}")
//    public Long delete(@PathVariable Long cartId) {
//        cartService.delete(cartId);
//        return cartId;
//    }
}
