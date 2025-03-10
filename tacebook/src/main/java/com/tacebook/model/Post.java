/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.model;

import java.util.Date;

/**
 *
 * @author roi.castrocalvar
 */
public class Post {

    private int id; //identificador unico del post
    private Date date; //Data de publicacion del post
    private String text; //Texto del post

    /**
     * Obtén o identificador do post.
     *
     * @return O identificador do post.
     */
    public int getId() {
        return id;
    }

    /**
     * Estabblece o identificador do post.
     *
     * @param id O identificador do post.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtén a data do post.
     *
     * @return A data do post.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Estabblece a data do post.
     *
     * @param date A data do post.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Obtén o texto do post.
     *
     * @return O texto do post.
     */
    public String getText() {
        return text;
    }

    /**
     * Establece o texto do post.
     *
     * @param text O texto do post.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Constructor da clase Post.
     *
     * @param id O identificador do post.
     * @param date A data do post.
     * @param text O texto do post.
     */
    public Post(int id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

}
