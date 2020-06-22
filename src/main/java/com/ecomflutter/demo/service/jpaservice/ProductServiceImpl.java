package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductCategoryDetail;
import com.ecomflutter.demo.beans.ProductImage;
import com.ecomflutter.demo.dao.ProductDao;
import com.ecomflutter.demo.service.ProductCategoryDetailService;
import com.ecomflutter.demo.service.ProductImageService;
import com.ecomflutter.demo.service.ProductService;
import com.ecomflutter.demo.service.util.Helper;
import com.ecomflutter.demo.service.util.NullPropertyNames;
import com.ecomflutter.demo.service.util.Response;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductCategoryDetailService productCategoryDetailService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private Helper helper;

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
    public ResponseEntity<?> findById(Long id) {
        Response response = new Response();

        Product currentProduct = null;
        Optional<Product> byId = this.productDao.findById(id);
        if (byId.isPresent()) {
            currentProduct = byId.get();
            if (currentProduct != null) {
                currentProduct.setCategories(this.productCategoryDetailService.findAllByProduct_Id(currentProduct.getId()));
                this.productImageService.findAllByProductId(currentProduct.getId());
            }
            response.setOutput(currentProduct);
        } else {
            response.addError("PRODUCT NOT FOUND", -1);
        }

        if (response.hasErrors()) {
            return helper.response(HttpStatus.NOT_FOUND, response);
        } else {
            response.addInfo("SUCESS", 1);
            response.setSucces(true);
            return helper.response(HttpStatus.OK, response);
        }

    }

    @Override
    public ResponseEntity<?> save(Product product, List<ProductImage> productImages, List<ProductCategoryDetail> productCategoryDetails) {

        Response response = new Response();

        Product currentProduct = this.productDao.save(product);
        if (currentProduct != null) {
            if (productImages != null) {
                this.productImageService.save(product, productImages);
            }
            if (productCategoryDetails != null) {
                this.productCategoryDetailService.save(product, productCategoryDetails);
                currentProduct.setCategories(this.productCategoryDetailService.findAllByProduct_Id(currentProduct.getId()));
            }
            response.setOutput(currentProduct);
        } else {
            response.addError("PRODUCT NOT SAVED", -1);
        }


        //check res
        if (response.hasErrors()) {
            return this.helper.response(HttpStatus.NOT_FOUND, response);
        } else {
            response.addInfo("SUCESS", 1);
            response.setSucces(true);
            return this.helper.response(HttpStatus.OK, response);
        }

    }

    @Override
    public int deleteById(Long id) {

        this.productDao.deleteById(id);
        return 1;

    }

    @Override
    public ResponseEntity<?> update(Long id, Product product, List<ProductImage> productImages, List<ProductCategoryDetail> productCategoryDetails) {

        Response response = new Response();

        Product currentProduct = null;
        Optional<Product> byId = this.productDao.findById(id);
        if (byId.isPresent()) {
            currentProduct = byId.get();
            if (productImages != null && !productImages.isEmpty()) {
                this.productImageService.save(currentProduct, productImages);
            }

            if (productCategoryDetails != null && !productCategoryDetails.isEmpty()) {
                this.productCategoryDetailService.save(currentProduct, productCategoryDetails);
                currentProduct.setCategories(this.productCategoryDetailService.findAllByProduct_Id(currentProduct.getId()));
            }
            BeanUtils.copyProperties(product, currentProduct, NullPropertyNames.getNullPropertyNames(product));
            Product savedProduct = this.productDao.save(currentProduct);

            if (savedProduct != null) {
                response.setOutput(currentProduct);
            } else {
                response.addError("PRODUCT NOT SAVED", -1);
            }
        } else {
            response.addError("PRODUCT NOT FOUND", -1);
        }


        //check
        if (response.hasErrors()) {
            return helper.response(HttpStatus.NOT_FOUND, response);
        } else {
            response.addInfo("SUCESS", 1);
            response.setSucces(true);
            return helper.response(HttpStatus.OK, response);
        }
    }
}
