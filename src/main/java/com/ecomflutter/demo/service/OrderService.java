package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Order;

import java.util.List;

public interface OrderService {

    public List<Order> findAll();

    public Order findById(Long id);

    public int save(Order Order);

    public int deleteById(Long id);
}
