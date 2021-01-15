package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.SuperCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperCategoryDao extends JpaRepository<SuperCategory,Long> {
}
