package com.shop.springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductOrderController {

    @GetMapping("/productOrders")
    public String productOrder(Model model) {

        model.addAttribute("pageName", "order");

        return "user/order";
    }
}