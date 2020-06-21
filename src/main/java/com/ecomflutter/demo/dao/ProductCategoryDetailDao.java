package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.ProductCategoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryDetailDao extends JpaRepository<ProductCategoryDetail, Long> {

    List<ProductCategoryDetail> findAllByProduct_Id(Long productId);

    List<ProductCategoryDetail> findAllByCategory_Id(Long categoryId);

}
