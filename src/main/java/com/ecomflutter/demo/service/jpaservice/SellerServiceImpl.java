package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Seller;
import com.ecomflutter.demo.dao.SellerDao;
import com.ecomflutter.demo.service.SellerService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Seller> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Seller> Sellers = this.sellerDao.findAll();
        session.disableFilter("deletedFilter");

        return Sellers;
    }

    @Override
    public Seller findById(Long id) {
        return this.sellerDao.findById(id).get();
    }

    @Override
    public Seller findByUser_Id(Long id) {
        return this.sellerDao.findByUser_Id(id);
    }

    @Override
    public int save(Seller Seller) {
        this.sellerDao.save(Seller);
        return 1;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {

        this.sellerDao.deleteById(id);
        return 1;

    }
}
