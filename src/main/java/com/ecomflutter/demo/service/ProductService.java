package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductImage;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product findById(Long id);

    public Product save(Product product, List<ProductImage> productImages);

    public int deleteById(Long id);

    public Product update(Long id, Product product);
}
