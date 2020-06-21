package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.ProductImage;
import com.ecomflutter.demo.dao.ProductDao;
import com.ecomflutter.demo.service.ProductCategoryDetailService;
import com.ecomflutter.demo.service.ProductImageService;
import com.ecomflutter.demo.service.ProductService;
import com.ecomflutter.demo.service.util.NullPropertyNames;
import com.ecomflutter.demo.service.util.Response;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        Response response = new Response();

        Product currentProduct = null;
        Optional<Product> byId = this.productDao.findById(id);
        if (byId.isPresent()) {
            currentProduct = byId.get();
            if (currentProduct != null) {
                currentProduct.setCategories(this.productCategoryDetailService.findAllByProduct_Id(currentProduct.getId()));
            }
            response.setOutput(currentProduct);
        }
        return currentProduct;

    }

    @Override
    public Product save(Product product, List<ProductImage> productImages) {

        Product currentProduct = this.productDao.save(product);
        if (currentProduct != null) {
            if (productImages != null) {
                this.productImageService.save(product, productImages);
            }
        }
        return currentProduct;

    }

    @Override
    public int deleteById(Long id) {

        this.productDao.deleteById(id);
        return 1;

    }

    @Override
    public Product update(Long id, Product product) {


        Product foundedProduct = findById(id);

        if (foundedProduct != null) {
            BeanUtils.copyProperties(product, foundedProduct, NullPropertyNames.getNullPropertyNames(product));
            List<ProductImage> productImages = product.getProductImages();
            if (productImages != null && !productImages.isEmpty()) {
                this.productImageService.save(foundedProduct, productImages);
            }
        }
        return foundedProduct;
    }
}
