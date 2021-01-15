package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.SuperCategory;

import java.util.List;

public interface SuperCategoryService {

    public List<SuperCategory> findAll();

    public SuperCategory findById(Long id);

    public int save(SuperCategory SuperCategory);

    public int deleteById(Long id);
}
