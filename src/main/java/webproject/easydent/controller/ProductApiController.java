package webproject.easydent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webproject.easydent.entities.Product;
import webproject.easydent.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductApiController {

    private final ProductService productService;

    @GetMapping("/products") // API 엔드포인트
    public List<Product> getAllProducts() {
        return productService.getAllProducts(); // 모든 제품 데이터 반환
    }
}
