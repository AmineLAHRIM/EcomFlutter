package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Order;
import com.ecomflutter.demo.dao.OrderDao;
import com.ecomflutter.demo.service.OrderService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Order> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Order> Orders = this.orderDao.findAll();
        session.disableFilter("deletedFilter");

        return Orders;
    }

    @Override
    public Order findById(Long id) {
        return this.orderDao.findById(id).get();
    }

    @Override
    public int save(Order Order) {
        this.orderDao.save(Order);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.orderDao.deleteById(id);
        return 1;

    }
}
