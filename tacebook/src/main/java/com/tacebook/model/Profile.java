/*
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licenza
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar este modelo
 */
package com.tacebook.model;

import java.util.ArrayList;

/**
 * Clase que representa un perfil de usuario en la red social Tacebook.
 */
public class Profile {

    private String name;
    private String password;
    private String status;

    private ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<Profile> friends = new ArrayList<>();
    private ArrayList<Profile> friendshipRequests = new ArrayList<>();

    /**
     * Constructor de la clase Profile.
     *
     * @param name Nombre del perfil.
     * @param password Contraseña del perfil.
     * @param status Estado del perfil.
     */
    public Profile(String name, String password, String status) {
        this.name = name;
        this.password = password;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ArrayList<Profile> getFriends() {
        return friends;
    }

    public ArrayList<Profile> getFriendshipRequests() {
        return friendshipRequests;
    }
}
