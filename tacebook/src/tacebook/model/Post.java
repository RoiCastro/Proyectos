/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa una publicación realizada por un perfil.
 */
public class Post {

    private int id;
    private Date date;
    private String text;

    private Profile author;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ArrayList<Profile> profileLikes = new ArrayList<>();

    /**
     * Constructor de la clase Post.
     *
     * @param id Identificador del post.
     * @param date Fecha del post.
     * @param text Texto de la publicación.
     */
    public Post(int id, Date date, String text) {
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

    public Profile getAuthor() {
        return author;
    }

    public void setAuthor(Profile author) {
        this.author = author;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Profile> getProfileLikes() {
        return profileLikes;
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
