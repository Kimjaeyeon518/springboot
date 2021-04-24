package com.shop.springboot.restController;

import com.shop.springboot.entity.Product;
import com.shop.springboot.exception.ResourceNotFoundException;
import com.shop.springboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor    // Bean을 주입받을 때 '@Autowired' 방식이 아닌 생성자 주입 방식으로 유도
@RequestMapping(value = "/products")
public class ProductRestController {

    private final ProductService productService;

    //  상품 리스트 조회
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findProducts();
    }

    //  상품 조회
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable (value = "id") long productId) {
        return productService.findOne(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + productId));
    }

    //  상품 등록
    @PostMapping
    public Long createProduct(@RequestBody Product product) {
        productService.save(product);
        return product.getId();
    }

    //  상품 수정
    @PutMapping("/{id}")
    public Long updateProduct(@RequestBody Product product, @PathVariable ("id") long productId) {
        Product existingProduct = productService.findOne(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + productId));


        existingProduct.setName(product.getName());
        existingProduct.setProductImg(product.getProductImg());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setAmount(product.getAmount());
        existingProduct.setBuyCount(product.getBuyCount());

        productService.save(existingProduct);

        return existingProduct.getId();
    }

    //  상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable ("id") long productId){
        Product existingProduct = productService.findOne(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id :" + productId));
        productService.delete(existingProduct);
        return ResponseEntity.ok().build();
    }
    
}
