/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.model;

import java.util.Date;

/**
 * Clase que representa un comentario hecho a una publicación.
 */
public class Comment {

    private int id;
    private Date date;
    private String text;

    private Profile sourceProfile;
    private Post post;

    /**
     * Constructor de la clase Comment.
     *
     * @param id Identificador del comentario.
     * @param date Fecha del comentario.
     * @param text Texto del comentario.
     */
    public Comment(int id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public Profile getSourceProfile() {
        return sourceProfile;
    }

    public void setSourceProfile(Profile sourceProfile) {
        this.sourceProfile = sourceProfile;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }
}
