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
    private UserDao userDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<User> Users = this.userDao.findAll();
        session.disableFilter("deletedFilter");

        return Users;
    }

    @Override
    public User findById(Long id) {
        return this.userDao.findById(id).get();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return this.userDao.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByUsername(String username) {
        return this.userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return this.userDao.findByEmail(email);
    }

    @Override
    public int save(User user) {
        if (this.findByUsername(user.getUsername()) != null) {

            return -1;
        } else if (userDao.findByEmail(user.getEmail()) != null) {
            return -2;
        } else {
            this.userDao.save(user);
            return 1;
        }
    }

    @Override
    public int deleteById(Long id) {

        this.userDao.deleteById(id);
        return 1;

    }
}
