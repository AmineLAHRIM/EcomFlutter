package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.MaxMinPrice;
import com.ecomflutter.demo.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    List<Product> findAllByStoreId(Long id);

    @Query(value = "SELECT new com.ecomflutter.demo.beans.MaxMinPrice (MAX(p.price),MIN(p.price)) FROM Product p")
    MaxMinPrice findMaxMinPrice();

}
