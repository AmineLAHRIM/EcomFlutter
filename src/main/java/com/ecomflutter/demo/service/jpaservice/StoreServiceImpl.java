package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Store;
import com.ecomflutter.demo.dao.StoreDao;
import com.ecomflutter.demo.service.StoreService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Store> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Store> stores = this.storeDao.findAll();
        session.disableFilter("deletedFilter");

        return stores;
    }

    @Override
    public Store findById(Long id) {
        return this.storeDao.findById(id).get();
    }

    @Override
    public List<Store> findBySellerId(Long sellerId) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Store> stores = this.storeDao.findAllBySeller_Id(sellerId);
        session.disableFilter("deletedFilter");
        return stores;
    }

    @Override
    public Store save(Store store) {
        Store s = this.storeDao.save(store);
        System.out.println("id hada " + s.getId());
        return s;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {

        this.storeDao.deleteById(id);
        return 1;

    }

    @Override
    public Store update(Long id, Store store) {
        Optional<Store> byId = this.storeDao.findById(id);

        if (byId.isPresent()) {
            Store currentStore = byId.get();
            store.setId(id);
            //BeanUtils.copyProperties(store, currentStore);
            Store savedStore = this.storeDao.save(store);

            return savedStore;
        }
        return null;
    }
}
