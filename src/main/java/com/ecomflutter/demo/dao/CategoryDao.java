package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
    /*List<Category> findAllBySuperCategory(SuperCategory superCategory);*/

    List<Category> findAllByOrderByIdDesc();
    List<Category> findAllByParentCategoryId(Long id);
    List<Category> findAllByParentIsTrue();


    //List<Category> findBySuperCategoryId(Long id);

}
