package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {


    User findByUsername(String username);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

}
