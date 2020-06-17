package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductImage;
import com.ecomflutter.demo.dao.ProductDao;
import com.ecomflutter.demo.service.ProductImageService;
import com.ecomflutter.demo.service.ProductService;
import com.ecomflutter.demo.service.util.NullPropertyNames;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Product> Products = this.productDao.findAll();
        session.disableFilter("deletedFilter");

        return Products;
    }

    @Override
    public Product findById(Long id) {
        return this.productDao.findById(id).get();
    }

    @Override
    public int save(Product product, List<ProductImage> productImages) {
        this.productDao.save(product);

        this.productImageService.save(product, productImages);

        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.productDao.deleteById(id);
        return 1;

    }

    @Override
    public int update(Long id, Product product) {
        Product foundedProduct = findById(id);
        if (foundedProduct != null) {
            BeanUtils.copyProperties(product, foundedProduct, NullPropertyNames.getNullPropertyNames(product));
            List<ProductImage> productImages = product.getProductImages();
            this.productImageService.save(foundedProduct, productImages);

            return 0;
        }
        return -1;


    }
}
