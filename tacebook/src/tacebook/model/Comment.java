/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.model;

import java.util.Date;

/**
 * Clase que representa un comentario hecho a una publicación.
 */
public class Comment {

    /**
     * Identificador único del comentario.
     */
    private int id;

    /**
     * Fecha en la que se realizó el comentario.
     */
    private Date date;

    /**
     * Texto del comentario.
     */
    private String text;

    /**
     * Perfil que realizó el comentario.
     */
    private Profile sourceProfile;

    /**
     * Publicación a la que pertenece el comentario.
     */
    private Post post;

    /**
     * Constructor de la clase Comment.
     *
     * @param id   Identificador del comentario.
     * @param date Fecha del comentario.
     * @param text Texto del comentario.
     */
    public Comment(int id, Date date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    /**
     * Obtiene el identificador del comentario.
     * @return id del comentario.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene la fecha del comentario.
     * @return fecha del comentario.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Obtiene el texto del comentario.
     * @return texto del comentario.
     */
    public String getText() {
        return text;
    }

    /**
     * Obtiene el perfil que realizó el comentario.
     * @return perfil fuente del comentario.
     */
    public Profile getSourceProfile() {
        return sourceProfile;
    }

    /**
     * Establece el perfil que realizó el comentario.
     * @param sourceProfile perfil fuente.
     */
    public void setSourceProfile(Profile sourceProfile) {
        this.sourceProfile = sourceProfile;
    }

    /**
     * Obtiene la publicación a la que pertenece el comentario.
     * @return publicación asociada.
     */
    public Post getPost() {
        return post;
    }

    /**
     * Establece la publicación a la que pertenece el comentario.
     * @param post publicación asociada.
     */
    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * Establece el identificador del comentario.
     * @param id identificador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Establece la fecha del comentario.
     * @param date fecha.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Establece el texto del comentario.
     * @param text texto.
     */
    public void setText(String text) {
        this.text = text;
    }
}
