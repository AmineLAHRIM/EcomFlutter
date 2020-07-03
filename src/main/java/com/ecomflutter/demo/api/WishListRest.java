package com.ecomflutter.demo.api;

import com.ecomflutter.demo.beans.WishList;
import com.ecomflutter.demo.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("ecomflutter/wishlist")
public class WishListRest {

    @Autowired
    private WishListService wishListService;

    @GetMapping("/")
    public List<WishList> findAll() {
        return this.wishListService.findAll();
    }

    @GetMapping("/{id}")
    public WishList findById(@PathVariable Long id) {
        return this.wishListService.findById(id);
    }

    @PostMapping("/")
    public WishList save(@RequestBody WishList wishList) {
        return this.wishListService.save(wishList);
    }

    @DeleteMapping("/{id}")
    public int deleteById(@PathVariable Long id) {
        return this.wishListService.deleteById(id);
    }
}
