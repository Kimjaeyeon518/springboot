package com.shop.springboot.controller;

import com.shop.springboot.dto.CartDto.CartRequestDto;
import com.shop.springboot.dto.ProductOrderDto.ProductOrderRequestDto;
import com.shop.springboot.entity.Product;
import com.shop.springboot.service.ProductOrderService;
import com.shop.springboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductOrderController {

    private final ProductService productService;
    private final ProductOrderService productOrderService;

    @GetMapping("/productOrders")
    public String productOrder(Model model) {

        return "member/productOrders";
    }

//    // 상품창에서 바로 구매
//    @GetMapping(value = "/product/buy/{productId}")
//    public String buyProduct(Model model, @PathVariable Long productId) {
//        Product product = productService.findById(productId);
//
//        int totalPrice = product.getPrice();
//        int totalDiscountPrice = product.getPrice()-(product.getPrice()*product.getDiscount())/100;
//
//        model.addAttribute("totalPrice", totalPrice);
//        model.addAttribute("totalDiscountPrice", totalDiscountPrice);
//        model.addAttribute("product", product);
//
//        return "product/product-order";
//    }

    // iamport 결제
    @GetMapping(value = "/product/iamport/{totalDiscountPrice}")
    public String kakaoPay(Model model, @PathVariable int totalDiscountPrice) {

        int price = totalDiscountPrice;
        model.addAttribute("totalDiscountPrice", price);

        return "product/iamport";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/productOrders")
    public String successIamport(@ModelAttribute @Valid ProductOrderRequestDto productOrderRequestDto, RedirectAttributes rttr) {
        productOrderService.save(productOrderRequestDto);
        rttr.addFlashAttribute("registerComplete", "결제 완료");
        return "redirect:/productOrders/" + productOrderRequestDto.getUserId();
    }

}