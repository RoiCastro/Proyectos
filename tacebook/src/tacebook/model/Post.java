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

    /**
     * Identificador único de la publicación.
     */
    private int id;

    /**
     * Fecha en la que se realizó la publicación.
     */
    private Date date;

    /**
     * Texto de la publicación.
     */
    private String text;

    /**
     * Perfil autor de la publicación.
     */
    private Profile author;

    /**
     * Lista de comentarios asociados a la publicación.
     */
    private ArrayList<Comment> comments = new ArrayList<>();

    /**
     * Lista de perfiles que han dado "me gusta" a la publicación.
     */
    private ArrayList<Profile> profileLikes = new ArrayList<>();

    /**
     * Constructor de la clase Post.
     *
     * @param id   Identificador del post.
     * @param date Fecha del post.
     * @param text Texto de la publicación.
     */
    public Post(int id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    /**
     * Obtiene el identificador de la publicación.
     * @return id de la publicación.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene la fecha de la publicación.
     * @return fecha de la publicación.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Obtiene el texto de la publicación.
     * @return texto de la publicación.
     */
    public String getText() {
        return text;
    }

    /**
     * Obtiene el autor de la publicación.
     * @return perfil autor.
     */
    public Profile getAuthor() {
        return author;
    }

    /**
     * Establece el autor de la publicación.
     * @param author perfil autor.
     */
    public void setAuthor(Profile author) {
        this.author = author;
    }

    /**
     * Obtiene la lista de comentarios de la publicación.
     * @return lista de comentarios.
     */
    public ArrayList<Comment> getComments() {
        return comments;
    }

    /**
     * Obtiene la lista de perfiles que han dado "me gusta".
     * @return lista de perfiles que han dado like.
     */
    public ArrayList<Profile> getProfileLikes() {
        return profileLikes;
    }

    /**
     * Establece el identificador de la publicación.
     * @param id identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Establece la fecha de la publicación.
     * @param date fecha.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Establece el texto de la publicación.
     * @param text texto.
     */
    public void setText(String text) {
        this.text = text;
    }
}
