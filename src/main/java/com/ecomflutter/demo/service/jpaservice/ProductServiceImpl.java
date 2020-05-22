package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.dao.ProductDao;
import com.ecomflutter.demo.service.ProductService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao ProductDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Product> Products = this.ProductDao.findAll();
        session.disableFilter("deletedFilter");

        return Products;
    }

    @Override
    public Product findById(Long id) {
        return this.ProductDao.findById(id).get();
    }

    @Override
    public int save(Product Product) {
        this.ProductDao.save(Product);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.ProductDao.deleteById(id);
        return 1;

    }
}
