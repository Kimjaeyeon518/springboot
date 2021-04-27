package com.shop.springboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductController {

    @GetMapping("/productList")
    public String productList(Model model) {

        model.addAttribute("pageName", "productList");

        return "product/productList";
    }

    @GetMapping("/productDetails")
    public String productDetails(Model model, @RequestParam("productId") Long id) {

        model.addAttribute("productId", id);

        return "product/productDetails";
    }

}
