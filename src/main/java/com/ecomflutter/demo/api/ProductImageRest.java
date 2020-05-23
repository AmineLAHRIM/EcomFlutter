package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.ProductImage;
import com.ecomflutter.demo.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ecomflutter/productimage")
public class ProductImageRest {

    @Autowired
    private ProductImageService ProductImageImageService;

    @GetMapping("/")
    public List<ProductImage> findAll() {
        return this.ProductImageImageService.findAll();
    }

    @GetMapping("/{id}")
    public ProductImage findById(@PathVariable Long id) {
        return this.ProductImageImageService.findById(id);
    }

    @PostMapping("/")
    public int save(@RequestBody ProductImage productImage) {
        return this.ProductImageImageService.save(productImage);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.ProductImageImageService.deleteById(id);
    }
}
