package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Store;

import java.util.List;

public interface StoreService {

    public List<Store> findAll();

    public Store findById(Long id);

    public List<Store> findBySellerId(Long sellerId);

    public Store save(Store store);

    public int deleteById(Long id);

    Store update(Long id, Store store);
}
