package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.WishList;

import java.util.List;

public interface WishListService {

    public List<WishList> findAll();

    public WishList findById(Long id);

    public WishList save(WishList WishList);

    public int deleteById(Long id);
}
