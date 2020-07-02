package com.ecomflutter.demo.beans;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class FileObj implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String type;
    private String path;
    private String autorisation;
    @DateTimeFormat(style = "dd/mm/aaaa HH:MM")
    private Date date=new Date();

    public FileObj(String name, String type, String path, String autorisation, Date date) {
        this.name = name;
        this.type = type;
        this.path = path;
        this.autorisation = autorisation;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(String autorisation) {
        this.autorisation = autorisation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
