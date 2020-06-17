package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("ecomflutter/product")
public class ProductRest {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<Product> findAll() {
        return this.productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @PostMapping("/")
    public int save(@RequestBody Product product) {
        return this.productService.save(product, product.getProductImages());
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Long id, @RequestBody Product product) {
        return this.productService.update(id, product);
    }
}
