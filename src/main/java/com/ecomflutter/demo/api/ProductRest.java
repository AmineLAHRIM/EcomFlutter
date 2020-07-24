package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.MaxMinPrice;
import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("ecomflutter/product")
public class ProductRest {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Product> findAll() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<?> findAllByStoreId(@PathVariable Long storeId) {
        return this.productService.findAllByStoreId(storeId);
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Product product) {
        return this.productService.save(product);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
        return this.productService.update(id, product);
    }

    @GetMapping("/maxminprice")
    public MaxMinPrice findMaxMinPrice() {
        return this.productService.findMaxMinPrice();
    }

}
