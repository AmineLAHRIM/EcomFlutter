package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageDao extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findAllByProductId(Long productId);

    int deleteAllByProduct_Id(Long productId);
}
