/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistencia;

import tacebook.model.Post;
import tacebook.model.Profile;

/**
 * Clase de acceso a datos para la gestión de publicaciones.
 */
public class PostDB {

    /**
     * Guarda una publicación asociándola al perfil correspondiente.
     * Añade la publicación al inicio de la lista de publicaciones del perfil.
     *
     * @param post Publicación a guardar.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void save(Post post) throws PersistenceException {
        if (post != null && post.getAuthor() != null) {
            // Asignar un ID único basado en timestamp
            post.setId((int) (System.currentTimeMillis() % Integer.MAX_VALUE));
            post.getAuthor().getPosts().add(0, post); // Añadir al inicio para mantener orden cronológico inverso
        }
    }

    /**
     * Guarda un "me gusta" de un perfil en una publicación.
     * Si el perfil no ha dado "me gusta" previamente, se añade a la lista de perfiles que dieron "me gusta".
     *
     * @param post Publicación a la que se le da "me gusta".
     * @param profile Perfil que da "me gusta".
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void saveLike(Post post, Profile profile) throws PersistenceException {
        if (!post.getProfileLikes().contains(profile)) {
            post.getProfileLikes().add(profile);
        }
    }
}
