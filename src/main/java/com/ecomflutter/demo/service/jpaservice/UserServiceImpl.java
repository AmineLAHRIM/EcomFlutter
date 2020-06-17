package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.User;
import com.ecomflutter.demo.beans.WishList;
import com.ecomflutter.demo.dao.UserDao;
import com.ecomflutter.demo.service.UserService;
import com.ecomflutter.demo.service.WishListService;
import com.ecomflutter.demo.service.util.Helper;
import com.ecomflutter.demo.service.util.Response;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private Helper helper;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private WishListService wishListService;


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
    public ResponseEntity<?> findByEmailAndPassword(String email, String password) {

        Response response = new Response();
        response.setOutput(this.userDao.findByEmailAndPassword(email, password));
        if (response.getOutput() == null) {
            response.addError("EMAIL OR PASSWORD INCORRECT", -1);
        }
        // VERIFY ERRORS
        if (response.hasErrors()) {
            return helper.response(HttpStatus.NOT_FOUND, response);
        } else {
            response.addInfo("SUCESS", 1);
            return helper.response(HttpStatus.OK, response);
        }
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
    public ResponseEntity<?> save(User user) {

        Response response = new Response();

        if (this.findByUsername(user.getUsername()) != null) {
            response.addError("USERNAME EXISTS", -1);
        }
        if (userDao.findByEmail(user.getEmail()) != null) {
            response.addError("EMAIL EXISTS", -2);
        }

        // VERIFY ERRORS
        if (response.hasErrors()) {
            return helper.response(HttpStatus.NOT_FOUND, response);
        } else {
            WishList currentW = this.wishListService.save(new WishList());
            user.setWishList(currentW);
            this.userDao.save(user);
            response.setOutput(user);
            response.addInfo("SUCESS", 1);
            return helper.response(HttpStatus.OK, response);
        }

    }

    @Override
    public int deleteById(Long id) {

        this.userDao.deleteById(id);
        return 1;

    }
}
