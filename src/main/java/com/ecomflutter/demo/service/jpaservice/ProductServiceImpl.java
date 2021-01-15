package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.*;
import com.ecomflutter.demo.dao.ProductDao;
import com.ecomflutter.demo.dao.RankDao;
import com.ecomflutter.demo.service.*;
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
import org.springframework.transaction.annotation.Transactional;

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
    private UpsellService upsellService;

    @Autowired
    private TagService tagService;

    @Autowired
    private RankDao rankDao;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private Helper helper;

    @Override
    @Transactional
    public List<Product> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Product> products = this.productDao.findAll();
        session.disableFilter("deletedFilter");

        return products;
    }

    @Override
    public ResponseEntity<?> findById(Long id) {


        Response response = new Response();
        Product currentProduct = null;


        Optional<Product> byId = this.productDao.findById(id);

        if (byId.isPresent() && !byId.get().isDeleted()) {
            currentProduct = byId.get();
            currentProduct.loadDetail = true;
            if (currentProduct != null) {
                currentProduct.setCategories(this.productCategoryDetailService.findAllByProduct_Id(currentProduct.getId()));
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
    public ResponseEntity<?> save(Product product) {

        Response response = new Response();

        if (product != null) {
            product.loadDetail = true;
            List<ProductCategoryDetail> productCategoryDetails = product.getProductCategoryDetails();

            //setup Rank
            Rank rank = new Rank();
            rank.setNumberStar1(1);
            rank.setNumberStar2(2);
            rank.setNumberStar3(3);
            rank.setNumberStar4(4);
            rank.setNumberStar5(30);
            Rank savedRank = this.rankDao.save(rank);
            product.setRank(savedRank);

            Product currentProduct;

            //setup ProductImage just featured image
            if (product.getFeaturedImage() != null) {
                //save imageUrl until we save the product with no featuredImage
                String imageUrl = product.getFeaturedImage().getImageUrl();
                product.setFeaturedImage(null);

                currentProduct = this.productDao.save(product);

                List<ProductImage> savedProductImages = this.productImageService.saveAll(currentProduct, currentProduct.getProductImages());
                if (savedProductImages != null && savedProductImages.size() > 0) {
                    for (ProductImage productImage : savedProductImages) {
                        if (imageUrl.equals(productImage.getImageUrl())) {
                            currentProduct.setFeaturedImage(productImage);
                            break;
                        }
                    }
                }
            } else {
                currentProduct = this.productDao.save(product);
            }


            if (currentProduct != null) {
                if (productCategoryDetails != null) {
                    this.productCategoryDetailService.save(product, productCategoryDetails);
                    currentProduct.setCategories(this.productCategoryDetailService.findAllByProduct_Id(currentProduct.getId()));
                }
                response.setOutput(currentProduct);
            } else {
                response.addError("PRODUCT NOT SAVED", -1);
            }

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

    @Transactional
    @Override
    public int deleteById(Long id) {

        this.productDao.deleteById(id);
        return 1;

    }

    @Override
    public ResponseEntity<?> update(Long id, Product product) {

        Response response = new Response();

        if (product != null) {
            product.loadDetail = true;
            List<ProductImage> productImages = product.getProductImages();
            List<ProductCategoryDetail> productCategoryDetails = product.getProductCategoryDetails();
            List<Upsell> upsells = product.getUpsells();
            List<Tag> tags = product.getTags();

            Optional<Product> byId = this.productDao.findById(id);
            if (byId.isPresent()) {
                Product currentProduct = byId.get();
                currentProduct.loadDetail = true;

                //Product Images
                if (productImages != null) {
                    this.productImageService.deleteAllByProductId(currentProduct.getId());


                    //get featured image after saved with id
                    List<ProductImage> savedProductImages = this.productImageService.saveAll(currentProduct, productImages);
                    if (product.getFeaturedImage() != null) {
                        String imageUrl = product.getFeaturedImage().getImageUrl();
                        if (!imageUrl.equals("") && savedProductImages != null && savedProductImages.size() > 0) {
                            for (ProductImage productImage : savedProductImages) {
                                if (imageUrl.equals(productImage.getImageUrl())) {
                                    product.setFeaturedImage(productImage);
                                    break;
                                }
                            }
                        }
                    }


                }

                //Product Upsell
                if (upsells != null) {
                    this.upsellService.deleteAllByProductId(currentProduct.getId());
                    this.upsellService.save(currentProduct, upsells);
                }

                //Product Tag
                if (tags != null) {
                    this.tagService.deleteAllByProductId(currentProduct.getId());
                    this.tagService.save(currentProduct, tags);
                }

                //Product Category Detail
                if (productCategoryDetails != null) {
                    this.productCategoryDetailService.deleteAllByProduct_Id(currentProduct.getId());
                    this.productCategoryDetailService.save(currentProduct, productCategoryDetails);
                    currentProduct.setCategories(this.productCategoryDetailService.findAllByProduct_Id(currentProduct.getId()));
                }

                BeanUtils.copyProperties(product, currentProduct, NullPropertyNames.getNullPropertyNames(product));
                //after copie check if url is empty for featuredImage
                if(product.getFeaturedImage()!=null && product.getFeaturedImage().getImageUrl().equals("")){
                    currentProduct.setFeaturedImage(null);
                }

                Product savedProduct = this.productDao.save(currentProduct);
                if (savedProduct != null) {
                    response.setOutput(savedProduct);
                } else {
                    response.addError("PRODUCT NOT SAVED", -1);
                }
            } else {
                response.addError("PRODUCT NOT FOUND", -1);
            }

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

    @Override
    public ResponseEntity<?> findAllByStoreId(Long storeId) {
        Response response = new Response();

        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Product> products = this.productDao.findAllByStoreId(storeId);
        session.disableFilter("deletedFilter");

        if (products != null && !products.isEmpty()) {
            response.setOutput(products);
        } else {
            response.addError("PRODUCTS NOT FOUND", -1);
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

    @Override
    public MaxMinPrice findMaxMinPrice() {
        return this.productDao.findMaxMinPrice();
    }

    @Override
    public MaxMinPrice findMaxMinPriceByStoreId(Long storeId) {
        return this.productDao.findMaxMinPriceByStoreId(storeId);
    }


}
