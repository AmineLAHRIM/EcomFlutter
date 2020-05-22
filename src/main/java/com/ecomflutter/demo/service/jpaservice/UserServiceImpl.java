package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.User;
import com.ecomflutter.demo.dao.UserDao;
import com.ecomflutter.demo.service.UserService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao UserDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<User> Users = this.UserDao.findAll();
        session.disableFilter("deletedFilter");

        return Users;
    }

    @Override
    public User findById(Long id) {
        return this.UserDao.findById(id).get();
    }

    @Override
    public int save(User User) {
        this.UserDao.save(User);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.UserDao.deleteById(id);
        return 1;

    }
}
