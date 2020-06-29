package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Category;
import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductCategoryDetail;
import com.ecomflutter.demo.dao.ProductCategoryDetailDao;
import com.ecomflutter.demo.service.ProductCategoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCategoryDetailServiceImpl implements ProductCategoryDetailService {

    @Autowired
    private ProductCategoryDetailDao productCategoryDetailDao;

    @Override
    public List<Category> findAllByProduct_Id(Long productId) {
        List<ProductCategoryDetail> productCategoryDetails = this.productCategoryDetailDao.findAllByProduct_Id(productId);
        List<Category> categories = new ArrayList<>();
        productCategoryDetails.forEach(productCategoryDetail -> {
            categories.add(productCategoryDetail.getCategory());
        });

        return categories;

    }

    @Override
    public List<Product> findAllByCategory_Id(Long categoryId) {
        List<ProductCategoryDetail> productCategoryDetails = this.productCategoryDetailDao.findAllByCategory_Id(categoryId);
        List<Product> products = new ArrayList<>();
        productCategoryDetails.forEach(productCategoryDetail -> {
            products.add(productCategoryDetail.getProduct());
        });

        return products;

    }

    @Override
    public int deleteAllByCategory_Id(Long categoryId) {
        return this.productCategoryDetailDao.deleteAllByCategory_Id(categoryId);
    }

    @Transactional
    @Override
    public int deleteAllByProduct_Id(Long productId) {
        return this.productCategoryDetailDao.deleteAllByProduct_Id(productId);
    }

    @Override
    public int save(Product product, List<ProductCategoryDetail> productCategoryDetails) {

        productCategoryDetails.forEach(productCategoryDetail -> {
            productCategoryDetail.setProduct(product);
            this.productCategoryDetailDao.save(productCategoryDetail);
        });
        return 1;
    }

    @Override
    public List<ProductCategoryDetail> findAll() {
        return this.productCategoryDetailDao.findAll();
    }
}
