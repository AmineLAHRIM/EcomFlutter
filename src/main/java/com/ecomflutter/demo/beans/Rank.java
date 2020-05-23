package com.ecomflutter.demo.beans;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.io.Serializable;

@SQLDelete(sql = "UPDATE rank SET deleted=true WHERE id=?")

@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)

@Entity
public class Rank implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numberStar1;
    private int numberStar2;
    private int numberStar3;
    private int numberStar4;
    private int numberStar5;

    @Column(columnDefinition = "boolean default false")
    private boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberStar1() {
        return numberStar1;
    }

    public void setNumberStar1(int numberStar1) {
        this.numberStar1 = numberStar1;
    }

    public int getNumberStar2() {
        return numberStar2;
    }

    public void setNumberStar2(int numberStar2) {
        this.numberStar2 = numberStar2;
    }

    public int getNumberStar3() {
        return numberStar3;
    }

    public void setNumberStar3(int numberStar3) {
        this.numberStar3 = numberStar3;
    }

    public int getNumberStar4() {
        return numberStar4;
    }

    public void setNumberStar4(int numberStar4) {
        this.numberStar4 = numberStar4;
    }

    public int getNumberStar5() {
        return numberStar5;
    }

    public void setNumberStar5(int numberStar5) {
        this.numberStar5 = numberStar5;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
