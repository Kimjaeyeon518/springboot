package com.shop.springboot.controller;

import com.shop.springboot.entity.Product;
import com.shop.springboot.service.ProductService;
import com.shop.springboot.service.UserService;
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

    private final ProductService productService;

    @GetMapping("/productList")
    public String getProductList(Model model, @PageableDefault Pageable pageable
            , @RequestParam(value="category", required = false) String category) {

        Page<Product> productList = productService.getProductList(pageable, category);

        model.addAttribute("productList", productList);
        model.addAttribute("category", category);

        return "product/productList";
    }

    @GetMapping("/product/update/{id}")
    public String productUpdate(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);

        model.addAttribute("product", product);

        return "product/product-update";
    }

    @GetMapping(value = "/selectCategory")
    public String openSelectCategory(Model model) {
        return "product/selectCategory";
    }

    @GetMapping(value = "/addProduct")
    public String openProductInsert(@RequestParam(value="category", required = false) String category, Model model) {
        model.addAttribute("category", category);
        return "product/product-save";
    }


}
