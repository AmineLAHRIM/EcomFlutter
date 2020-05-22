package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.ProductImage;

import java.util.List;

public interface ProductImageService {

    public List<ProductImage> findAll();

    public ProductImage findById(Long id);

    public int save(ProductImage ProductImage);

    public int deleteById(Long id);
}
