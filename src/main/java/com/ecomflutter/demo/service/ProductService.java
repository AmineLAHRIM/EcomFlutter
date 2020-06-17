package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductImage;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product findById(Long id);

    public int save(Product product, List<ProductImage> productImages);

    public int deleteById(Long id);

    public int update(Long id, Product product);
}
