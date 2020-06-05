package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Store;
import com.ecomflutter.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("ecomflutter/store")
public class StoreRest {

    @Autowired
    private StoreService storeService;

    @GetMapping("/")
    public List<Store> findAll() {
        return this.storeService.findAll();
    }

    @GetMapping("/{id}")
    public Store findById(@PathVariable Long id) {
        return this.storeService.findById(id);
    }

    @PostMapping("/")
    public int save(@RequestBody Store store) {
        return this.storeService.save(store);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.storeService.deleteById(id);
    }
}
