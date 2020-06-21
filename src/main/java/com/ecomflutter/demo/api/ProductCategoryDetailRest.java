package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Category;
import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductCategoryDetail;
import com.ecomflutter.demo.service.ProductCategoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("ecomflutter/productcategorydetail")
public class ProductCategoryDetailRest {

    @Autowired
    private ProductCategoryDetailService productCategoryDetailService;

    @GetMapping("/productId/{productId}")
    public List<Category> findAllByProductId(@PathVariable Long productId) {
        return this.productCategoryDetailService.findAllByProduct_Id(productId);
    }

    @GetMapping("/categoryId/{categoryId}")
    public List<Product> findAllByCategoryId(@PathVariable Long categoryId) {
        return this.productCategoryDetailService.findAllByCategory_Id(categoryId);
    }

    @GetMapping("/")
    public List<ProductCategoryDetail> findAll() {
        return this.productCategoryDetailService.findAll();
    }
}
