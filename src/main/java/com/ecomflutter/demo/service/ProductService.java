package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product findById(Long id);

    public int save(Product Product);

    public int deleteById(Long id);
}
