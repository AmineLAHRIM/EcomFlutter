package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.ProductImage;
import com.ecomflutter.demo.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("ecomflutter/productimage")
public class ProductImageRest {

    @Autowired
    private ProductImageService productImageService;

    @GetMapping("/")
    public List<ProductImage> findAll() {
        return this.productImageService.findAll();
    }

    @GetMapping("/{id}")
    public ProductImage findById(@PathVariable Long id) {
        return this.productImageService.findById(id);
    }

    @PostMapping("/")
    public int save(@RequestBody ProductImage productImage) {
        return this.productImageService.save(productImage);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.productImageService.deleteById(id);
    }
}
