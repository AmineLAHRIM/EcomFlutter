package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerDao extends JpaRepository<Seller,Long> {
}
