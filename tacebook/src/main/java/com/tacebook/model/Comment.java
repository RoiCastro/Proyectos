/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author roi.castrocalvar
 */
public class Comment {

    private int id;
    private Date date;
    private String text;

    /**
     * Obtén o identificador do comentario.
     *
     * @return O identificador do comentario.
     */
    public int getId() {
        return id;
    }

    /**
     * Esteablece o identificador do comentario.
     *
     * @param id O identificador do comentario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtén a data do comentario.
     *
     * @return A data do comentario.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Establece a data do comentario.
     *
     * @param date A data do comentario.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Obtén o texto do comentario.
     *
     * @return O texto do comentario.
     */
    public String getText() {
        return text;
    }

    /**
     * Establece o texto do comentario.
     *
     * @param text O texto do comentario.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Constructor da clase Comment.
     *
     * @param id O identificador do comentario.
     * @param date A data do comentario.
     * @param text O texto do comentario.
     */
    public Comment(int id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }
    
     // De aqui para abajo no estoy seguro de lo que hago
    private ArrayList<Post> posts;
    private ArrayList<Profile> sourceProfile;

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Profile> getSourceProfile() {
        return sourceProfile;
    }

    public void setSourceProfile(ArrayList<Profile> sourceProfile) {
        this.sourceProfile = sourceProfile;
    }
    
}
