package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.WishList;
import com.ecomflutter.demo.dao.WishListDao;
import com.ecomflutter.demo.service.WishListService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    private WishListDao wishListDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<WishList> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<WishList> WishLists = this.wishListDao.findAll();
        session.disableFilter("deletedFilter");

        return WishLists;
    }

    @Override
    public WishList findById(Long id) {
        return this.wishListDao.findById(id).get();
    }

    @Override
    public WishList save(WishList WishList) {
        return this.wishListDao.save(WishList);
    }

    @Transactional
    @Override
    public int deleteById(Long id) {

        this.wishListDao.deleteById(id);
        return 1;

    }
}
