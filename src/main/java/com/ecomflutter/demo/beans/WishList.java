package com.ecomflutter.demo.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@SQLDelete(sql = "UPDATE wish_list SET deleted=true WHERE id=?")

@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)
@Entity
public class WishList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(targetEntity = ProductWishListDetail.class, mappedBy = "wishList", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProductWishListDetail> productWishListDetailList;

    @Transient
    private List<Product> products;

    public WishList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductWishListDetail> getProductWishListDetailList() {
        return productWishListDetailList;
    }

    public void setProductWishListDetailList(List<ProductWishListDetail> productWishListDetailList) {
        this.productWishListDetailList = productWishListDetailList;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
