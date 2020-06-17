package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductImage;

import java.util.List;

public interface ProductImageService {

    public List<ProductImage> findAll();

    public ProductImage findById(Long id);

    public int save(Product product, List<ProductImage> productImages);

    public int deleteById(Long id);

    public List<ProductImage> findAllByProductId(Long productId);

    public int saveAll(List<ProductImage> productImages, Long productId);
}
