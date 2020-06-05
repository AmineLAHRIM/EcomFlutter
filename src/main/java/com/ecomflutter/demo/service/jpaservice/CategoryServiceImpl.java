package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Category;
import com.ecomflutter.demo.dao.CategoryDao;
import com.ecomflutter.demo.dao.SuperCategoryDao;
import com.ecomflutter.demo.service.CategoryService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private SuperCategoryDao superCategoryDao;


    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Category> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Category> categories = this.categoryDao.findAll();
        session.disableFilter("deletedFilter");

        return categories;
    }

    @Override
    public List<Category> findBySuperCategoryId(Long id) {
        return this.categoryDao.findBySuperCategoryId(id);
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
        return this.categoryDao.findById(id).get();
    }

    @Override
    public int save(Category category) {
        this.categoryDao.save(category);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.categoryDao.deleteById(id);
        return 1;

    }
}
