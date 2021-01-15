package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Log;

import java.util.List;

public interface LogService {

    public List<Log> findAll();

    public Log findById(Long id);

    public int save(Log Log);

    public int deleteById(Long id);
}
