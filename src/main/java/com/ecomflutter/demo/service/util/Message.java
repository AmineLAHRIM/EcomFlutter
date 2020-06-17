package com.ecomflutter.demo.service.util;


public class Message {

    private int code;
    private String libelle;
    private TypeMessage typeMessage;

    public Message(int code, String libelle, TypeMessage typeMessage) {
        this.code = code;
        this.libelle = libelle;
        this.typeMessage = typeMessage;
    }

    public Message() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
}
