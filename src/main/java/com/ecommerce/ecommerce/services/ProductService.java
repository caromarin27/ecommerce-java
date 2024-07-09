package com.ecommerce.ecommerce.services;

import com.ecommerce.ecommerce.entities.Product;
import com.ecommerce.ecommerce.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired private ProductsRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> readProducts() {
        return repository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return repository.findById(id);
    }

    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
}
