package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Seller;
import com.ecomflutter.demo.dao.SellerDao;
import com.ecomflutter.demo.service.SellerService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerDao SellerDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Seller> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Seller> Sellers = this.SellerDao.findAll();
        session.disableFilter("deletedFilter");

        return Sellers;
    }

    @Override
    public Seller findById(Long id) {
        return this.SellerDao.findById(id).get();
    }

    @Override
    public int save(Seller Seller) {
        this.SellerDao.save(Seller);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.SellerDao.deleteById(id);
        return 1;

    }
}
