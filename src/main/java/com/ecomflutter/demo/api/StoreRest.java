package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.Store;
import com.ecomflutter.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping("/seller/{sellerId}")
    public List<Store> findAllBySellerId(@PathVariable Long sellerId) {
        return this.storeService.findBySellerId(sellerId);
    }

    @PostMapping("/")
    public Store save(@RequestBody Store store) {
        return this.storeService.save(store);
    }

    @PutMapping("/{id}")
    public Store update(@PathVariable Long id, @RequestBody Store store) {
        return this.storeService.update(id, store);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.storeService.deleteById(id);
    }
}
