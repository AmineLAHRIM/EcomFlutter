package com.ecomflutter.demo.beans;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;

@SQLDelete(sql = "UPDATE order SET deleted=true WHERE id=?")

@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)

@Table(name = "`order`")
@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    @ManyToOne
    private User user;
    @ManyToOne
    private Invoice invoice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


}
