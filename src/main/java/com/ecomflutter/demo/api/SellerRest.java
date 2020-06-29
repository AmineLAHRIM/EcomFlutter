package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Seller;
import com.ecomflutter.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("ecomflutter/seller")
public class SellerRest {

    @Autowired
    private SellerService sellerService;


    @GetMapping("/")
    public List<Seller> findAll() {
        return this.sellerService.findAll();
    }

    @GetMapping("/{id}")
    public Seller findById(@PathVariable Long id) {
        return this.sellerService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public Seller findByUserId(@PathVariable Long userId) {
        return this.sellerService.findByUser_Id(userId);
    }


    @PostMapping("/")
    public int save(@RequestBody Seller seller) {
        return this.sellerService.save(seller);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.sellerService.deleteById(id);
    }
}
