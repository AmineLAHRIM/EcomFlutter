package com.ecomflutter.demo.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;

@SQLDelete(sql = "UPDATE upsell SET deleted=true WHERE id=?")


@Entity
public class Upsell implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Product upsellProduct;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getUpsellProduct() {
        return upsellProduct;
    }

    public void setUpsellProduct(Product upsellProduct) {
        this.upsellProduct = upsellProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
