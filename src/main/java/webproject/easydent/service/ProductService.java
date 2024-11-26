package webproject.easydent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import webproject.easydent.entities.Product;
import webproject.easydent.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

}
