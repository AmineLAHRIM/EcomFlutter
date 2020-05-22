package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Rank;
import com.ecomflutter.demo.dao.RankDao;
import com.ecomflutter.demo.service.RankService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankDao RankDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Rank> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Rank> Ranks = this.RankDao.findAll();
        session.disableFilter("deletedFilter");

        return Ranks;
    }

    @Override
    public Rank findById(Long id) {
        return this.RankDao.findById(id).get();
    }

    @Override
    public int save(Rank Rank) {
        this.RankDao.save(Rank);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.RankDao.deleteById(id);
        return 1;

    }
}
