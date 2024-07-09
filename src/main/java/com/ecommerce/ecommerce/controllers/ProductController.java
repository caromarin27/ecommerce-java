package com.ecommerce.ecommerce.controllers;

import com.ecommerce.ecommerce.entities.Product;
import com.ecommerce.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    @Autowired private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product data) {
        try {
            Product product = productService.saveProduct(data);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.readProducts();
            if(!products.isEmpty()) {
                return ResponseEntity.ok(products);
            } else {
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        try {
            Optional<Product> product = productService.getProductById(id);
            if(product.isPresent()) return ResponseEntity.ok(product.get());
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product data) {
        try {
            Optional<Product> optionalProduct = productService.getProductById(id);
            if(optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                product.setDescription(data.getDescription());
                product.setCode(data.getCode());
                product.setStock(data.getStock());
                product.setPrice(data.getPrice());
                return ResponseEntity.ok(productService.saveProduct(product));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        try {
            Optional<Product> product = productService.getProductById(id);
            if(product.isPresent()) {
              productService.deleteProduct(id);
              return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
