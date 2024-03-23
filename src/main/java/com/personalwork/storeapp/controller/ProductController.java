package com.personalwork.storeapp.controller;

import com.personalwork.storeapp.model.Product;
import com.personalwork.storeapp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService personService;

    public ProductController(ProductService personService) {
        this.personService = personService;
    }


    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
        Product addedProduct = personService.addProduct(newProduct);
        return ResponseEntity.ok(addedProduct);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = personService.getAllProducts();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/getProductById/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        try {
            Product selectedProduct = personService.getProductById(productId);
            return ResponseEntity.ok(selectedProduct);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteById/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Integer productId) {
        try {
            personService.deleteById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
