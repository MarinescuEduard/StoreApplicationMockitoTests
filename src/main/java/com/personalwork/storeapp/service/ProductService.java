package com.personalwork.storeapp.service;

import com.personalwork.storeapp.model.Product;
import com.personalwork.storeapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer productId) {
        Optional <Product> requestedProduct = productRepository.findById(productId);
        if (requestedProduct.isPresent()){
            return requestedProduct.get();
        } else {
            throw new IllegalArgumentException("Person with id " + productId + " not found.");
        }
    }
}
