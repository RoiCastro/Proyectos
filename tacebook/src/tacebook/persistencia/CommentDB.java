/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistencia;

import tacebook.model.Comment;
import tacebook.model.Post;

/**
 * Clase de acceso a datos para la gestión de comentarios.
 */
public class CommentDB {

    /**
     * Guarda un comentario asociándolo al post correspondiente.
     * Añade el comentario al inicio de la lista de comentarios del post.
     *
     * @param comment El comentario a guardar.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void save(Comment comment) throws PersistenceException {
        Post post = comment.getPost();
        post.getComments().add(0, comment); // Engadir ao comezo da lista
    }
}
