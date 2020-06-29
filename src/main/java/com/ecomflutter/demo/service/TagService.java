package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Product;
import com.ecomflutter.demo.beans.Tag;

import java.util.List;

public interface TagService {

    public List<Tag> save(Product product, List<Tag> tags);

    public List<Tag> findAllByProductId(Long productId);

    public int deleteAllByProductId(Long productId);

}
