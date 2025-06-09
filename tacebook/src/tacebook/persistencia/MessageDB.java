/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.persistencia;

import tacebook.model.Message;
import tacebook.model.Profile;

/**
 * Clase de acceso a datos para la gestión de mensajes.
 */
public class MessageDB {

    /**
     * Guarda un mensaje asociándolo al perfil destinatario.
     * Añade el mensaje al inicio de la lista de mensajes del perfil.
     *
     * @param message Mensaje a guardar.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void save(Message message) throws PersistenceException {
        Profile destProfile = message.getDestProfile();
        destProfile.getMessages().add(0, message); // Engadir ao comezo da lista
    }

    /**
     * Actualiza un mensaje.
     *
     * @param message Mensaje a actualizar.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void update(Message message) throws PersistenceException {
        // Non se fai nada neste momento
    }

    /**
     * Elimina un mensaje del perfil destinatario.
     *
     * @param message Mensaje a eliminar.
     * @throws PersistenceException Si ocurre un error de persistencia.
     */
    public static void remove(Message message) throws PersistenceException {
        Profile destProfile = message.getDestProfile();
        destProfile.getMessages().remove(message);
    }
}
