package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<Order,Long> {
}
