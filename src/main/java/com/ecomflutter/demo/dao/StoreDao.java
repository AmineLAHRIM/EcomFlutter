package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreDao extends JpaRepository<Store,Long> {
}
