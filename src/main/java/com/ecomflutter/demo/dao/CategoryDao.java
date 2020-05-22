package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category,Long> {

}
