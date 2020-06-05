package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(Long id);

    public User findByEmailAndPassword(String email, String password);

    public User findByUsername(String username);
    public User findByEmail(String email);

    public int save(User User);

    public int deleteById(Long id);
}
