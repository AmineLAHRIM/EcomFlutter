package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Seller;

import java.util.List;

public interface SellerService {

    public List<Seller> findAll();

    public Seller findById(Long id);

    public Seller findByUser_Id(Long id);

    public int save(Seller Seller);

    public int deleteById(Long id);
}
