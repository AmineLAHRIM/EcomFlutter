package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.Upsell;

import java.util.List;

public interface UpsellService {

    public List<Upsell> save(Product product, List<Upsell> upsells);

    public List<Upsell> findAllByProductId(Long productId);

    public int deleteAllByProductId(Long productId);

}
