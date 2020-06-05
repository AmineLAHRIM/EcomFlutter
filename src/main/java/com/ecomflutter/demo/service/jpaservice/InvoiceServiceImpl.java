package com.ecomflutter.demo.service.jpaservice;

import com.ecomflutter.demo.beans.Invoice;
import com.ecomflutter.demo.dao.InvoiceDao;
import com.ecomflutter.demo.service.InvoiceService;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Invoice> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedFilter");
        filter.setParameter("isDeleted", false);
        List<Invoice> invoices = this.invoiceDao.findAll();
        session.disableFilter("deletedFilter");

        return invoices;
    }

    @Override
    public Invoice findById(Long id) {
        return this.invoiceDao.findById(id).get();
    }

    @Override
    public int save(Invoice Invoice) {
        this.invoiceDao.save(Invoice);
        return 1;
    }

    @Override
    public int deleteById(Long id) {

        this.invoiceDao.deleteById(id);
        return 1;

    }
}
