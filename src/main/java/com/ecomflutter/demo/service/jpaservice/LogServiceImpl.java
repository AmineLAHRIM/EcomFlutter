package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Log;
import com.ecomflutter.demo.dao.LogDao;
import com.ecomflutter.demo.service.LogService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Log> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Log> Logs = this.logDao.findAll();
        session.disableFilter("deletedFilter");
        return Logs;
    }

    @Override
    public Log findById(Long id) {
        return this.logDao.findById(id).get();
    }

    @Override
    public int save(Log Log) {
        this.logDao.save(Log);
        return 1;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {

        this.logDao.deleteById(id);
        return 1;

    }
}
