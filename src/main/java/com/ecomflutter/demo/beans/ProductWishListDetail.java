package com.ecomflutter.demo.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductWishListDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Product product;
    @ManyToOne
    private WishList wishList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }
}
