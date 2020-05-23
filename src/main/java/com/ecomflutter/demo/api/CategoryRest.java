package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Category;
import com.ecomflutter.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ecomflutter/category")
public class CategoryRest {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public List<Category> findAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        return this.categoryService.findById(id);
    }

    @PostMapping("/")
    public int save(@RequestBody Category category) {
        return this.categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.categoryService.deleteById(id);
    }
}
