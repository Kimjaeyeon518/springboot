package com.shop.springboot.restController;

import com.shop.springboot.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
@RequestMapping(value = "/productOrders")
public class ProductOrderRestController {

    private ProductOrderService productOrderService;

////    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//    @GetMapping("/productOrders/{productOrderId}")
//    public ResponseEntity<?> getOrderDetails(@PathVariable Long orderId) {
//
//        return ResponseEntity.ok().body(productOrderService.findOne(orderId));
//    }

////    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//    @GetMapping("/users/{userId}/productOrders/{page}")
//    public ResponseEntity<?> getAllOrder(@PathVariable("userId") Long userId, @PathVariable("page") int page) {
//
//        return ResponseEntity.ok().body(productOrderService.findProductOrders(userId, page));
//    }

}
