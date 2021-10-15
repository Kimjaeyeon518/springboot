package com.shop.springboot.controller.admin;

import com.shop.springboot.entity.Cart;
import com.shop.springboot.entity.Product;
import com.shop.springboot.entity.User;
import com.shop.springboot.service.ProductService;
import com.shop.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminController {

    private final ProductService productService;
    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String index(Model model) {
        model.addAttribute("template", "fragments/content/main");
        return "index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/productLimitCountList")
    public String productLimitCountList(Model model) {

        List<Product> productList = productService.findProducts();

        model.addAttribute("productList", productList);

        return "admin/productLimitCountList";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/userList")
    public String userList(Model model) {
        List<User> userList = userService.findUsers();

        model.addAttribute("userList", userList);

        return "admin/userList";
    }
}
