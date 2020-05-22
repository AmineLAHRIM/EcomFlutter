package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListDao extends JpaRepository<WishList, Long> {
}
