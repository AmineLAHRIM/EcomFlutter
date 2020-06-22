package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductCategoryDetail;
import com.ecomflutter.demo.beans.ProductImage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> save(Product product, List<ProductImage> productImages, List<ProductCategoryDetail> productCategoryDetails);

    public int deleteById(Long id);

    public ResponseEntity<?> update(Long id, Product product, List<ProductImage> productImages, List<ProductCategoryDetail> productCategoryDetails);
}
