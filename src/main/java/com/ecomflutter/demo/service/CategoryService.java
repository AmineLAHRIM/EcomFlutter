package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();

    public Category findById(Long id);

    public int save(Category category);

    public int deleteById(Long id);


}
