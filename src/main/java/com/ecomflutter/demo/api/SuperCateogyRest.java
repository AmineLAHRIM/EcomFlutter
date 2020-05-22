package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.SuperCategory;
import com.ecomflutter.demo.service.SuperCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ecomflutter/supercategory")
public class SuperCateogyRest {

    @Autowired
    private SuperCategoryService superCategoryService;

    @GetMapping("/")
    private List<SuperCategory> findAll() {
        return this.superCategoryService.findAll();
    }
}
