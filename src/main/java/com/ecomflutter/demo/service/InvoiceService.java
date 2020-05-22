package com.ecomflutter.demo.service;

import com.ecomflutter.demo.beans.Invoice;

import java.util.List;

public interface InvoiceService {

    public List<Invoice> findAll();

    public Invoice findById(Long id);

    public int save(Invoice Invoice);

    public int deleteById(Long id);
}
