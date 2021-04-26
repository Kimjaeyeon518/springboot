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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
@RequestMapping(value = "/carts")
public class CartRestController {

    private final CartService cartService;

    //  장바구니 목록 조회
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/users/{userId}/carts/{page}")
    public ResponseEntity<?> getCartList(@PathVariable("userId") Long userId, @PathVariable("page") int page,
                                         @PageableDefault(size = 5, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok().body(cartService.findCarts(userId, page, pageable));
    }

    //  장바구니 담기
    @PostMapping
    public Long createCart(@RequestBody Cart cart) {
        cartService.save(cart);
        return cart.getId();
    }

    //  장바구니 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Cart> deleteCart(@PathVariable ("id") long cartId){
        cartService.delete(cartId);
        return ResponseEntity.ok().build();
    }
}
