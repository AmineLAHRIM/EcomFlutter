package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Rank;

import java.util.List;

public interface RankService {

    public List<Rank> findAll();

    public Rank findById(Long id);

    public int save(Rank Rank);

    public int deleteById(Long id);
}
