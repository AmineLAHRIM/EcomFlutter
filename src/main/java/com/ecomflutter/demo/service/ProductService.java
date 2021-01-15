package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.MaxMinPrice;
import com.ecomflutter.demo.beans.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public ResponseEntity<?> findById(Long id);

    public ResponseEntity<?> save(Product product);

    public int deleteById(Long id);

    public ResponseEntity<?> update(Long id, Product product);
    ResponseEntity<?> findAllByStoreId(Long storeId);

    MaxMinPrice findMaxMinPrice();

    MaxMinPrice findMaxMinPriceByStoreId(Long storeId);
}
