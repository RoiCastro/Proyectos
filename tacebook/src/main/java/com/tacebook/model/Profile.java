/*
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licenza
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar este modelo
 */
package com.tacebook.model;

import java.util.ArrayList;

/**
 *
 * @author roi.castrocalvar
 */
public class Profile {

    private String name; // Nome do usuario
    private String password; // Contrasinal do usuario
    private String status; // Estado actual do usuario

    /**
     * Obtén o nome do usuario.
     *
     * @return O nome do usuario.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece o nome do usuario.
     *
     * @param name O novo nome do usuario.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtén o contrasinal do usuario.
     *
     * @return O contrasinal do usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece o contrasinal do usuario.
     *
     * @param password O novo contrasinal do usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtén o estado actual do usuario.
     *
     * @return O estado do usuario.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece o estado actual do usuario.
     *
     * @param status O novo estado do usuario.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Constructor da clase Profile.
     *
     * @param name O nome do usuario.
     * @param password O contrasinal do usuario.
     * @param status O estado inicial do usuario.
     */
    public Profile(String name, String password, String status) {
        this.name = name;
        this.password = password;
        this.status = status;
    }

    // De aqui para abajo no estoy seguro de lo que hago
    private ArrayList<Post> posts;
    private ArrayList<Profile> friends;
    private ArrayList<Profile> friendshipRequests;
    private ArrayList<Message> messages;

    /**
     * Obtén o arraylist de posts.
     *
     * @return o arraylist de posts.
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Establece o arraylist de posts.
     *
     * @param posts O arraylist de posts.
     */
    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    /**
     * Obtén o arraylist de friends.
     *
     * @return O arraylist de friends.
     */
    public ArrayList<Profile> getFriends() {
        return friends;
    }

    /**
     * Establece o arraylist de friends.
     *
     * @param friends O arraylist de friends.
     */
    public void setFriends(ArrayList<Profile> friends) {
        this.friends = friends;
    }

    /**
     * Obtén o arraylist de friendshiprequests.
     *
     * @return O arraylist de friendshiprequests.
     */
    public ArrayList<Profile> getFriendshipRequests() {
        return friendshipRequests;
    }

    /**
     * Establece o arraylist de friendshiprequests.
     *
     * @param friendshipRequests O arraylist de friendshiprequests.
     */
    public void setFriendshipRequests(ArrayList<Profile> friendshipRequests) {
        this.friendshipRequests = friendshipRequests;
    }

    /**
     * Obtén o arraylist de messages.
     *
     * @return O arraylist de messages.
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Establece o arraylist de messages.
     *
     * @param messages O arraylist de messages.
     */
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

}
