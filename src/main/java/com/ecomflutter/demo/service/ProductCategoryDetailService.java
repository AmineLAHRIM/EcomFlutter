package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Category;
import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductCategoryDetail;

import java.util.List;

public interface ProductCategoryDetailService {


    public List<Category> findAllByProduct_Id(Long productId);

    public List<Product> findAllByCategory_Id(Long categoryId);

    public int deleteAllByCategory_Id(Long categoryId);

    public int deleteAllByProduct_Id(Long productId);

    public int save(Product product, List<ProductCategoryDetail> productCategoryDetails);


    public List<ProductCategoryDetail> findAll();
}
