/*
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licenza
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar este modelo
 */
package com.tacebook.model;

import java.util.Date;

/**
 * Clase que representa unha mensaxe na aplicación Tacebook.
 * 
 * Contén atributos para almacenar o identificador, texto, data e estado da mensaxe (lida ou non).
 * Inclúe métodos para acceder e modificar estes atributos.
 * 
 * @author roi.castrocalvar
 */
public class Message {
    private int id; // Identificador único da mensaxe
    private String text; // Texto da mensaxe
    private Date date; // Data de envío da mensaxe
    private boolean read; // Indica se a mensaxe foi lida ou non

    /**
     * Obtén o identificador da mensaxe.
     * 
     * @return O identificador da mensaxe.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece o identificador da mensaxe.
     * 
     * @param id O novo identificador da mensaxe.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtén o texto da mensaxe.
     * 
     * @return O texto da mensaxe.
     */
    public String getText() {
        return text;
    }

    /**
     * Establece o texto da mensaxe.
     * 
     * @param text O novo texto da mensaxe.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Obtén a data de envío da mensaxe.
     * 
     * @return A data de envío da mensaxe.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Establece a data de envío da mensaxe.
     * 
     * @param date A nova data de envío da mensaxe.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Comproba se a mensaxe foi lida.
     * 
     * @return `true` se a mensaxe foi lida, `false` en caso contrario.
     */
    public boolean isRead() {
        return read;
    }

    /**
     * Establece o estado de lectura da mensaxe.
     * 
     * @param read `true` se a mensaxe foi lida, `false` en caso contrario.
     */
    public void setRead(boolean read) {
        this.read = read;
    }

    /**
     * Constructor da clase Message.
     * 
     * @param id O identificador único da mensaxe.
     * @param text O texto da mensaxe.
     * @param date A data de envío da mensaxe.
     * @param read O estado de lectura da mensaxe (`true` se foi lida, `false` en caso contrario).
     */
    public Message(int id, String text, Date date, boolean read) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.read = read;
    }
}
