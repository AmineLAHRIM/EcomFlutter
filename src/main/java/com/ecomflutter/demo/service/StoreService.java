package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Store;

import java.util.List;

public interface StoreService {

    public List<Store> findAll();

    public Store findById(Long id);

    public int save(Store Store);

    public int deleteById(Long id);
}
