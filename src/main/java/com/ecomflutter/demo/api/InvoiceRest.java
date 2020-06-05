package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Invoice;
import com.ecomflutter.demo.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("ecomflutter/invoice")
public class InvoiceRest {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/")
    public List<Invoice> findAll() {
        return this.invoiceService.findAll();
    }

    @GetMapping("/{id}")
    public Invoice findById(@PathVariable Long id) {
        return this.invoiceService.findById(id);
    }

    @PostMapping("/")
    public int save(@RequestBody Invoice invoice) {
        return this.invoiceService.save(invoice);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.invoiceService.deleteById(id);
    }
}
