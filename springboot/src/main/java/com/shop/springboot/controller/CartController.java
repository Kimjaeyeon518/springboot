package com.shop.springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class CartController {

    @GetMapping("/carts")
    public String cartList(Model model) {

        model.addAttribute("pageName", "cart");

        return "cart/cart";
    }
}