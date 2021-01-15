package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao extends JpaRepository<Tag, Long> {

    List<Tag> findAllByProduct_Id(Long productId);

    int deleteAllByProduct_Id(Long productId);
}
