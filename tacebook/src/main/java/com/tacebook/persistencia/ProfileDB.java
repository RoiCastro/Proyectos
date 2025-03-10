/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.persistencia;

import com.tacebook.model.Profile;
import java.util.Scanner;

/**
 *
 * @author roi.castrocalvar
 */
public class ProfileDB {

    /**
     * Recibe como parámetro o nome dun usuario e o número de publicacións dese
     * perfil que queremos recuperar, e devolve o obxecto "Profile" asociado a
     * ese usuario, ou "null" se o usuario non existe.
     *
     * @param name O nombre del perfil
     * @param numberOfPosts A cantidadde de posts do perfil
     * @return
     */
    public static Profile findByName(String name, int numberOfPosts) {
        Profile profile = null;
        for (int i = 0; i < TacebookDB.profiles.size(); i++) {
            if (TacebookDB.profiles.get(i).getName().equals(name)
                    && TacebookDB.profiles.get(i).getPosts().size() == numberOfPosts) {
                profile = TacebookDB.profiles.get(i);
            }
        }
        return profile;
    }

    /**
     * Recibe como parámetros o nome dun usuario, o contrasinal e o número de
     * publicacións dese perfil que queremos recuperar, e devolve o obxecto
     * "Profile" asociado a ese usuario, ou "null" se ese usuario non existe.
     *
     * @param name
     * @param password
     * @param numberOfPosts
     * @return
     */
    public static Profile findByNameAndPassword(String name, String password, int numberOfPosts) {
        Profile profile = null;
        for (int i = 0; i < TacebookDB.profiles.size(); i++) {
            if (TacebookDB.profiles.get(i).getName().equals(name)
                    && TacebookDB.profiles.get(i).getPosts().size() == numberOfPosts
                     && TacebookDB.profiles.get(i).getPassword().equals(password)) {
                profile = TacebookDB.profiles.get(i);
            }
        }
        return profile;

    }

    /**
     * Almacena o perfil no almacenamento.
     *
     * @param profile
     */
    public static void save(Profile profile) {
        TacebookDB.profiles.add(profile);
        System.out.println("Perfil guardado");
    }

    /**
     * Actualiza o perfil no almacemento.
     *
     * @param profile
     */
    public static void update(Profile profile) {

    }
}
