package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductImageDao extends JpaRepository<ProductImage,Long> {
}
