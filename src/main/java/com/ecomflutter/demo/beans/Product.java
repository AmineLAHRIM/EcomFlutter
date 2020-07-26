package com.ecomflutter.demo.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@SQLDelete(sql = "UPDATE product SET deleted=true WHERE id=?")

@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String shortDescription;
    @Column(columnDefinition="TEXT")
    private String longDescription;
    private double price;
    private double pricePromo;
    private double shippingPrice;
    private int quantityStock;
    @Enumerated(EnumType.STRING)
    private Unit unit;
    @OneToOne
    private ProductImage featuredImage;
    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @OneToOne
    private Rank rank;

    @ManyToOne
    private Store store;

/*    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> category;*/

    @Transient
    private List<Category> categories;

    @Transient
    public boolean loadDetail = false;

    @OneToMany(targetEntity = ProductCategoryDetail.class, mappedBy = "product", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProductCategoryDetail> productCategoryDetails;


    @OneToMany(targetEntity = ProductWishListDetail.class, mappedBy = "product", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ProductWishListDetail> productWishListDetails;

    @JsonManagedReference
    @Where(clause = "deleted = 'false'")
    @OneToMany(targetEntity = ProductImage.class, mappedBy = "product", cascade = CascadeType.DETACH)
    private List<ProductImage> productImages;

    @JsonManagedReference
    @OneToMany(targetEntity = Upsell.class, mappedBy = "product", cascade = CascadeType.DETACH)
    private List<Upsell> upsells;

    @JsonManagedReference
    @OneToMany(targetEntity = Tag.class, mappedBy = "product", cascade = CascadeType.DETACH)
    private List<Tag> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPricePromo() {
        return pricePromo;
    }

    public void setPricePromo(double pricePromo) {
        this.pricePromo = pricePromo;
    }

    public double getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(double shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public ProductImage getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(ProductImage featuredImage) {
        this.featuredImage = featuredImage;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

   /* public List<ProductCategoryDetail> getProductCategoryDetails() {
        return productCategoryDetails;
    }

    public void setProductCategoryDetails(List<ProductCategoryDetail> productCategoryDetails) {
        this.productCategoryDetails = productCategoryDetails;
    }*/

    public List<ProductWishListDetail> getProductWishListDetails() {
        return productWishListDetails;
    }


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<ProductImage> getProductImages() {
        if (loadDetail) {
            return productImages;
        }
        return null;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<ProductCategoryDetail> getProductCategoryDetails() {
        return productCategoryDetails;
    }

    public List<Upsell> getUpsells() {
        if (loadDetail) {
            return upsells;
        }
        return null;
    }

    public List<Tag> getTags() {
        if (loadDetail) {
            return tags;
        }
        return null;
    }


}
