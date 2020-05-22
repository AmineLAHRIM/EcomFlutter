package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product,Long> {
}
