/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistencia;

import tacebook.model.Profile;

/**
 * Clase de acceso a datos para la gestión de perfiles.
 */
public class ProfileDB {

    /**
     * Busca un perfil por nombre.
     *
     * @param name Nombre del perfil.
     * @param numberOfPosts Número de publicaciones (no usado).
     * @return El perfil encontrado o null si no existe.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static Profile findByName(String name, int numberOfPosts) throws PersistenceException {
        for (Profile p : TacebookDB.profiles) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Busca un perfil por nombre y contraseña.
     *
     * @param name Nombre del perfil.
     * @param password Contraseña del perfil.
     * @param numberOfPosts Número de publicaciones (no usado).
     * @return El perfil encontrado o null si no existe.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static Profile findByNameAndPassword(String name, String password, int numberOfPosts)
            throws PersistenceException {
        for (Profile p : TacebookDB.profiles) {
            if (p.getName().equalsIgnoreCase(name) && p.getPassword().equals(password)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Guarda un perfil en la base de datos.
     *
     * @param profile Perfil a guardar.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void save(Profile profile) throws PersistenceException {
        TacebookDB.profiles.add(profile);
    }

    /**
     * Actualiza un perfil en la base de datos.
     *
     * @param profile Perfil a actualizar.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void update(Profile profile) throws PersistenceException {
        // No se necesita acción para esta fase
    }

    /**
     * Guarda una solicitud de amistad.
     *
     * @param destProfile Perfil destinatario.
     * @param sourceProfile Perfil solicitante.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void saveFriendshipRequest(Profile destProfile, Profile sourceProfile) throws PersistenceException {
        if (!destProfile.getFriendshipRequests().contains(sourceProfile)) {
            destProfile.getFriendshipRequests().add(sourceProfile);
        }
    }

    /**
     * Elimina una solicitud de amistad.
     *
     * @param destProfile Perfil destinatario.
     * @param sourceProfile Perfil solicitante.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void removeFriendshipRequest(Profile destProfile, Profile sourceProfile) throws PersistenceException {
        destProfile.getFriendshipRequests().remove(sourceProfile);
    }

    /**
     * Guarda una amistad entre dos perfiles.
     *
     * @param profile1 Primer perfil.
     * @param profile2 Segundo perfil.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void saveFriendship(Profile profile1, Profile profile2) throws PersistenceException {
        if (!profile1.getFriends().contains(profile2)) {
            profile1.getFriends().add(profile2);
        }
        if (!profile2.getFriends().contains(profile1)) {
            profile2.getFriends().add(profile1);
        }
    }
}
