package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Upsell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpsellDao extends JpaRepository<Upsell, Long> {

    List<Upsell> findAllByProduct_Id(Long productId);

    int deleteAllByProduct_Id(Long productId);
}
