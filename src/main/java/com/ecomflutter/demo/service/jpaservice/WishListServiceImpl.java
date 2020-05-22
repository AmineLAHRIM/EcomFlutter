package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.WishList;
import com.ecomflutter.demo.dao.WishListDao;
import com.ecomflutter.demo.service.WishListService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishListDao WishListDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<WishList> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<WishList> WishLists = this.WishListDao.findAll();
        session.disableFilter("deletedFilter");

        return WishLists;
    }

    @Override
    public WishList findById(Long id) {
        return this.WishListDao.findById(id).get();
    }

    @Override
    public int save(WishList WishList) {
        this.WishListDao.save(WishList);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.WishListDao.deleteById(id);
        return 1;

    }
}
