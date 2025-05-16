/*
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licenza
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar este modelo
 */
package com.tacebook.model;

import java.util.Date;

/**
 * Clase que representa un mensaje enviado entre perfiles.
 */
public class Message {
    private int id;
    private String text;
    private Date date;
    private boolean read;

    private Profile sourceProfile;
    private Profile destProfile;

    /**
     * Constructor de la clase Message.
     * @param id Identificador del mensaje.
     * @param text Texto del mensaje.
     * @param date Fecha del mensaje.
     * @param read Estado de lectura del mensaje.
     */
    public Message(int id, String text, Date date, boolean read) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.read = read;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public boolean isRead() {
        return read;
    }

    public Profile getSourceProfile() {
        return sourceProfile;
    }

    public void setSourceProfile(Profile sourceProfile) {
        this.sourceProfile = sourceProfile;
    }

    public Profile getDestProfile() {
        return destProfile;
    }

    public void setDestProfile(Profile destProfile) {
        this.destProfile = destProfile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
