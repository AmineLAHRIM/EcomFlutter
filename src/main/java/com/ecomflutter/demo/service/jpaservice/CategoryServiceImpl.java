package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Category;
import com.ecomflutter.demo.dao.CategoryDao;
import com.ecomflutter.demo.dao.ProductDao;
import com.ecomflutter.demo.dao.SuperCategoryDao;
import com.ecomflutter.demo.service.CategoryService;
import com.ecomflutter.demo.service.ProductCategoryDetailService;
import com.ecomflutter.demo.service.util.NullPropertyNames;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private SuperCategoryDao superCategoryDao;
    @Autowired
    private ProductCategoryDetailService productCategoryDetailService;
    @Autowired
    private ProductDao productDao;


    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Category> categories = this.categoryDao.findAllByOrderByIdDesc();
        session.disableFilter("deletedFilter");

        return categories;
    }


    /*@Override
    public List<Category> findAllBySuperCategory(Long id) {
        SuperCategory superCategory = this.superCategoryDao.findById(id).get();
        List<Category> categories = null;

        if (superCategory != null) {
            categories = this.categoryDao.findAllBySuperCategory(superCategory);
        }

        return categories;
    }*/

    @Override
    public Category findById(Long id) {
        Category currentCategory = this.categoryDao.findById(id).get();
        if (currentCategory != null) {
            currentCategory.setProducts(this.productCategoryDetailService.findAllByCategory_Id(currentCategory.getId()));
        }
        return currentCategory;
    }

    @Override
    public Category save(Category category) {
        return this.categoryDao.save(category);
    }

    @Transactional
    @Override
    public int deleteById(Long id) {

        this.categoryDao.deleteById(id);
        return 1;

    }

    @Override
    public List<Category> findAllByParentCategoryId(Long id) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Category> categories = this.categoryDao.findAllByParentCategoryId(id);
        session.disableFilter("deletedFilter");
        return categories;
    }

    @Override
    public List<Category> findAllParentCategories() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Category> categories = this.categoryDao.findAllByParentIsTrue();
        session.disableFilter("deletedFilter");
        return categories;
    }

    @Override
    public Category update(Long id, Category category) {

        Optional<Category> byId = this.categoryDao.findById(id);
        Category currentCategory = null;

        if (byId.isPresent()) {
            currentCategory = byId.get();
            BeanUtils.copyProperties(category, currentCategory, NullPropertyNames.getNullPropertyNames(category));
            if(currentCategory.isParent()){
                currentCategory.setParentCategory(null);
            }
            currentCategory = this.categoryDao.save(currentCategory);
        }

        return currentCategory;

    }

}
