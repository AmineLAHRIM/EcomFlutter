package com.ecomflutter.demo.dao;

import com.ecomflutter.demo.beans.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice,Long> {
}
