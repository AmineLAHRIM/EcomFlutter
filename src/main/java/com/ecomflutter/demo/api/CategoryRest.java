package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Category;
import com.ecomflutter.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("ecomflutter/category")
public class CategoryRest {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public List<Category> findAll() {
        return this.categoryService.findAll();
    }

    @GetMapping("/parent")
    public List<Category> findAllParentCategories() {
        return this.categoryService.findAllParentCategories();
    }

    /*@GetMapping("/bysupercategory/{id}")
    public List<Category> findAllBySuperCategory(@PathVariable Long id) {
        System.out.println("findAllBySuperCategory id="+id);
        return this.categoryService.findAllBySuperCategory(id);
    }*/



    @GetMapping("/byparentcategoryid/{id}")
    public List<Category> findAllByParentCategoryId(@PathVariable Long id) {
        return this.categoryService.findAllByParentCategoryId(id);
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
