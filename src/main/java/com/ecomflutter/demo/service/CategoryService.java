package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();

    /* public List<Category> findAllBySuperCategory(Long id);*/


    public Category findById(Long id);

    public Category save(Category category);

    public int deleteById(Long id);


    List<Category> findAllByParentCategoryId(Long id);

    List<Category> findAllParentCategories();

    Category update(Long id, Category category);
}
