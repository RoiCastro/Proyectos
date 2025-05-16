/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.persistencia;

import com.tacebook.model.*;

public class MessageDB {

    public static void save(Message message) throws PersistenceException {
        Profile destProfile = message.getDestProfile();
        destProfile.getMessages().add(0, message); // Engadir ao comezo da lista
    }

    public static void update(Message message) throws PersistenceException {
        // Non se fai nada neste momento
    }

    public static void remove(Message message) throws PersistenceException {
        Profile destProfile = message.getDestProfile();
        destProfile.getMessages().remove(message);
    }
}
