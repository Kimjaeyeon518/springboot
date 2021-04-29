package com.shop.springboot.controller;

import com.shop.springboot.dto.CartDto.CartRequestDto;
import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.service.CartService;
import com.shop.springboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/cartList/{id}")
    public String cartList(Model model, @PathVariable("id") Long userId) {

        List<Cart> cartList = cartService.findAllByUserId(userId);
        int totalPrice = 0;
        int totalDiscountPrice = 0;
        for(Cart cart : cartList) {
            Long productId = cart.getProduct().getId();
            Product product = productService.findById(productId);

            totalPrice += product.getPrice() * cart.getProductCount();
            totalDiscountPrice += (product.getPrice()-(product.getPrice()*product.getDiscount())/100) * cart.getProductCount();
        }

        model.addAttribute("cartList", cartList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalDiscountPrice", totalDiscountPrice);

        return "cart/cartList";
    }

    // 장바구니에서 구매
    @GetMapping(value = "/cart/buy/{id}")
    public String buyCartProduct(Model model, @PathVariable("id") Long userId) {

        List<Cart> cartList = cartService.findCartsList(userId);

        int totalPrice = 0;
        int totalDiscountPrice = 0;
        for(Cart cart : cartList) {

            Long productId = cart.getProduct().getId();
            Product product = productService.findById(productId);

            totalPrice += product.getPrice() * cart.getProductCount();
            totalDiscountPrice += (product.getPrice()-(product.getPrice()*product.getDiscount())/100) * cart.getProductCount();
        }

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalDiscountPrice", totalDiscountPrice);
        model.addAttribute("cartList", cartList);

        return "product/product-order";
    }

    //  장바구니 담기
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(value= "/product/cart")
    public @ResponseBody CartRequestDto addCart(@RequestBody CartRequestDto cartRequestDto) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("USER ID == " + cartRequestDto.getUserId());
        System.out.println("PRODUCT ID == " + cartRequestDto.getProductId());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        cartService.addCart(cartRequestDto);

        return cartRequestDto;
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @DeleteMapping("/cart/{cartId}")
    public Long delete(@PathVariable Long cartId) {
        cartService.delete(cartId);
        return cartId;
    }

}