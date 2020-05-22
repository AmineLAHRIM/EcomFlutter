package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Store;
import com.ecomflutter.demo.dao.StoreDao;
import com.ecomflutter.demo.service.StoreService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDao StoreDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Store> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Store> Stores = this.StoreDao.findAll();
        session.disableFilter("deletedFilter");

        return Stores;
    }

    @Override
    public Store findById(Long id) {
        return this.StoreDao.findById(id).get();
    }

    @Override
    public int save(Store Store) {
        this.StoreDao.save(Store);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.StoreDao.deleteById(id);
        return 1;

    }
}
