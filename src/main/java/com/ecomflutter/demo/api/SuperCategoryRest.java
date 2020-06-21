package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.SuperCategory;
import com.ecomflutter.demo.service.SuperCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("ecomflutter/supercategory")
public class SuperCategoryRest {

    @Autowired
    private SuperCategoryService superCategoryService;

    @GetMapping("/")
    public List<SuperCategory> findAll() {
        return this.superCategoryService.findAll();
    }

    @GetMapping("/{id}")
    public SuperCategory findById(@PathVariable Long id) {
        return this.superCategoryService.findById(id);
    }

    @PostMapping("/")
    public int save(@RequestBody SuperCategory superCateogy) {
        return this.superCategoryService.save(superCateogy);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.superCategoryService.deleteById(id);
    }
}
