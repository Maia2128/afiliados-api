package com.erick.afiliados.controller;

import com.erick.afiliados.entity.Product;
import com.erick.afiliados.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        if (product.getActive() == null) {
            product.setActive(true);
        }
        return repository.save(product);
    }

    @GetMapping
    public List<Product> findAll() {
        return repository.findAll();
    }

    @GetMapping("/active/ordered")
    public List<Product> findActiveOrdered() {
        return repository.findByActiveTrueOrderByQueueOrderAsc();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product product = repository.findById(id).orElse(null);

        if (product == null) {
            return null;
        }

        product.setName(updatedProduct.getName());
        product.setCategory(updatedProduct.getCategory());
        product.setAffiliateUrl(updatedProduct.getAffiliateUrl());
        product.setImageUrl(updatedProduct.getImageUrl());
        product.setPrice(updatedProduct.getPrice());
        product.setOldPrice(updatedProduct.getOldPrice());
        product.setActive(updatedProduct.getActive());
        product.setQueueOrder(updatedProduct.getQueueOrder());

        return repository.save(product);
    }

    @PatchMapping("/{id}/queue/{queueOrder}")
    public Product updateQueueOrder(@PathVariable Long id, @PathVariable Integer queueOrder) {
        Product product = repository.findById(id).orElse(null);

        if (product == null) {
            return null;
        }

        product.setQueueOrder(queueOrder);
        return repository.save(product);
    }

    @PatchMapping("/{id}/activate")
    public Product activateProduct(@PathVariable Long id) {
        Product product = repository.findById(id).orElse(null);

        if (product == null) {
            return null;
        }

        product.setActive(true);
        return repository.save(product);
    }

    @PatchMapping("/{id}/deactivate")
    public Product deactivateProduct(@PathVariable Long id) {
        Product product = repository.findById(id).orElse(null);

        if (product == null) {
            return null;
        }

        product.setActive(false);
        return repository.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}