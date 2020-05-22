package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Seller;
import com.ecomflutter.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("ecomflutter/seller")
public class SellerRest {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/")
    private List<Seller> findAll() {
        return this.sellerService.findAll();
    }
}
