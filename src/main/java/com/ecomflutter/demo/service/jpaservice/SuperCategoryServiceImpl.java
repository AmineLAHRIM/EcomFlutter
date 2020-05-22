package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.SuperCategory;
import com.ecomflutter.demo.dao.SuperCategoryDao;
import com.ecomflutter.demo.service.SuperCategoryService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SuperCategoryServiceImpl implements SuperCategoryService {

    @Autowired
    private SuperCategoryDao SuperCategoryDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<SuperCategory> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<SuperCategory> SuperCategorys = this.SuperCategoryDao.findAll();
        session.disableFilter("deletedFilter");

        return SuperCategorys;
    }

    @Override
    public SuperCategory findById(Long id) {
        return this.SuperCategoryDao.findById(id).get();
    }

    @Override
    public int save(SuperCategory SuperCategory) {
        this.SuperCategoryDao.save(SuperCategory);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.SuperCategoryDao.deleteById(id);
        return 1;

    }
}
