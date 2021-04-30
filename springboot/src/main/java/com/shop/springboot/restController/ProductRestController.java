package com.shop.springboot.restController;

import com.shop.springboot.dto.productDto.ProductRequestDto;
import com.shop.springboot.entity.Product;
import com.shop.springboot.exception.ResourceNotFoundException;
import com.shop.springboot.service.ProductService;
import com.shop.springboot.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
@RequestMapping
public class ProductRestController {

    private final ProductService productService;
    private final S3Service s3Service;
    String imgPath;
    
//    //  상품 리스트 조회
//    @GetMapping("/products")
//    public ResponseEntity<?> getProductList(Model model, @PageableDefault Pageable pageable
//            , @RequestParam(value="category", required = false) String category) {
//
//        Page<Product> productList = productService.getProductList(pageable, category);
//
//        model.addAttribute("productList", productList);
//        model.addAttribute("category", category);
//
//        return ResponseEntity.ok().build();
//    }



    // 상품 이미지 업로드
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/products/image")
    public ResponseEntity<?> uploadProductImage(@RequestParam("file") MultipartFile file) throws IOException {
//        try {
//            UploadFile uploadedFile = productService.uploadProductImage(file);
//            return ResponseEntity.ok().body("product-upload-image/" + uploadedFile.getSaveFileName());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
////        }
//        imgPath = s3Service.upload(file);

        return ResponseEntity.ok().body("upload success");

    }

}
