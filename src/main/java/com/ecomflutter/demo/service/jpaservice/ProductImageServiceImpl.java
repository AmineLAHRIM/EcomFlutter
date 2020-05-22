package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.ProductImage;
import com.ecomflutter.demo.dao.ProductImageDao;
import com.ecomflutter.demo.service.ProductImageService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageDao ProductImageDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ProductImage> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<ProductImage> ProductImages = this.ProductImageDao.findAll();
        session.disableFilter("deletedFilter");

        return ProductImages;
    }

    @Override
    public ProductImage findById(Long id) {
        return this.ProductImageDao.findById(id).get();
    }

    @Override
    public int save(ProductImage ProductImage) {
        this.ProductImageDao.save(ProductImage);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.ProductImageDao.deleteById(id);
        return 1;

    }
}
