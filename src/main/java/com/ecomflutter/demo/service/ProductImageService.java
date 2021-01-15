package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductImage;

import java.util.List;

public interface ProductImageService {

    public List<ProductImage> findAll();

    public ProductImage findById(Long id);

    public List<ProductImage> saveAll(Product product, List<ProductImage> productImages);

    public ProductImage save(ProductImage productImage);

    public int deleteById(Long id);

    public List<ProductImage> findAllByProductId(Long productId);

    public int deleteAllByProductId(Long productId);

}
