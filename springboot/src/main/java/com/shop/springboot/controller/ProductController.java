package com.shop.springboot.controller;

import com.shop.springboot.dto.productDto.ProductRequestDto;
import com.shop.springboot.entity.Product;
import com.shop.springboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String getProductList(Model model, @PageableDefault Pageable pageable
            , @RequestParam(value="category", required = false) String category) {

        Page<Product> productList = productService.getProductList(pageable, category);

        model.addAttribute("productList", productList);
        model.addAttribute("category", category);

        return "product/index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/product/update/{id}")
    public String productUpdate(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);

        model.addAttribute("product", product);

        return "update_form";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/selectCategory")
    public String openSelectCategory(Model model) {
        return "product/selectCategory";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/addProduct")
    public String openProductInsert(@RequestParam(value="category", required = false) String category, Model model) {
        model.addAttribute("category", category);
        return "save_form";
    }

    //  상품 조회
    @GetMapping("/products/{id}")
    public String productView(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("category", product.getCategory());

        return "show";
    }

    // 상품 추가 (관리자 권한)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/products")
    public String save(@ModelAttribute @Valid ProductRequestDto productRequestDto, RedirectAttributes rttr) {
        productRequestDto.setProductImg("zzz");
        productService.save(productRequestDto);
        rttr.addFlashAttribute("registerComplete", "상품 등록이 완료되었습니다.");
        return "redirect:/";
    }

    // 상품 수정 (관리자 권한)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/products/{id}")
    public String save(@ModelAttribute @Valid ProductRequestDto productRequestDto, @PathVariable Long id, RedirectAttributes rttr) {
        productRequestDto.setProductImg("zzz");
        productService.updateProduct(id, productRequestDto);
        rttr.addFlashAttribute("registerComplete", "상품 수정이 완료되었습니다.");
        return "redirect:/products/" + id;
    }

    //  상품 삭제 (관리자 권한)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/products/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        productService.deleteProduct(id);
        rttr.addFlashAttribute("registerComplete", "상품 삭제가 완료되었습니다.");
        return "redirect:/";
    }
}
