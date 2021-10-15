//package com.shop.springboot.controller;
//
//import com.shop.springboot.dto.CartDto.CartRequestDto;
//import com.shop.springboot.dto.ProductOrderDto.ProductOrderRequestDto;
//import com.shop.springboot.entity.Cart;
//import com.shop.springboot.entity.Product;
//import com.shop.springboot.entity.ProductOrder;
//import com.shop.springboot.service.CartService;
//import com.shop.springboot.service.ProductOrderService;
//import com.shop.springboot.service.ProductService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@Slf4j
//@RequiredArgsConstructor
//@Controller
//public class ProductOrderController {
//
//    private final CartService cartService;
//    private final ProductService productService;
//    private final ProductOrderService productOrderService;
//
//    // iamport 결제
//    @GetMapping(value = "/product/iamport/{totalDiscountPrice}")
//    public String kakaoPay(Model model, @PathVariable int totalDiscountPrice) {
//
//        int price = totalDiscountPrice;
//        model.addAttribute("totalDiscountPrice", price);
//
//        return "product/iamport";
//    }
//
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//    @GetMapping("/productOrders/{userId}")
//    public String successIamport(@PathVariable Long userId, RedirectAttributes rttr) {
//        productOrderService.save(userId);
//        rttr.addFlashAttribute("registerComplete", "결제 완료");
//        return "redirect:/";
//    }
//
//    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//    @GetMapping("/productOrderList/{userId}")
//    public String getProductOrderList(Model model, @PathVariable Long userId) {
//
//        List<ProductOrder> productOrderList = productOrderService.findProductOrdersList(userId);
//
//        List<Cart> cartList = cartService.findAllByUserId(userId);
//
//        model.addAttribute("cartList", cartList);
//        model.addAttribute("productOrderList", productOrderList);
//
//        return "productOrder/productOrderList";
//    }
//
//}