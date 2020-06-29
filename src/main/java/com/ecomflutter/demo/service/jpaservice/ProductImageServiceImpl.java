package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductImage;
import com.ecomflutter.demo.dao.ProductImageDao;
import com.ecomflutter.demo.service.ProductImageService;
import com.ecomflutter.demo.service.ProductService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageDao productImageDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ProductImage> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<ProductImage> ProductImages = this.productImageDao.findAll();
        session.disableFilter("deletedFilter");

        return ProductImages;
    }

    @Override
    public ProductImage findById(Long id) {
        return this.productImageDao.findById(id).get();
    }

    @Override
    public List<ProductImage> save(Product product, List<ProductImage> productImages) {
        List<ProductImage> savedProductImages = new ArrayList<>();
        productImages.forEach(productImage -> {
            productImage.setProduct(product);
            savedProductImages.add(this.productImageDao.save(productImage));
        });
        return savedProductImages;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {

        this.productImageDao.deleteById(id);
        return 1;

    }

    @Override
    public List<ProductImage> findAllByProductId(Long productId) {
        return this.productImageDao.findAllByProductId(productId);
    }

    @Transactional
    @Override
    public int deleteAllByProductId(Long productId) {
        return this.productImageDao.deleteAllByProduct_Id(productId);

    }
}
