/*
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para cambiar esta licenza
 * Fai clic en nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar este modelo
 */
package tacebook.model;

import java.util.ArrayList;

/**
 * Clase que representa un perfil de usuario en la red social Tacebook.
 */
public class Profile {

    /**
     * Nombre del perfil.
     */
    private String name;

    /**
     * Contraseña del perfil.
     */
    private String password;

    /**
     * Estado del perfil.
     */
    private String status;

    /**
     * Lista de publicaciones realizadas por el perfil.
     */
    private ArrayList<Post> posts = new ArrayList<>();

    /**
     * Lista de mensajes recibidos o enviados por el perfil.
     */
    private ArrayList<Message> messages = new ArrayList<>();

    /**
     * Lista de amigos del perfil.
     */
    private ArrayList<Profile> friends = new ArrayList<>();

    /**
     * Lista de solicitudes de amistad recibidas.
     */
    private ArrayList<Profile> friendshipRequests = new ArrayList<>();

    /**
     * Constructor de la clase Profile.
     *
     * @param name     Nombre del perfil.
     * @param password Contraseña del perfil.
     * @param status   Estado del perfil.
     */
    public Profile(String name, String password, String status) {
        this.name = name;
        this.password = password;
        this.status = status;
    }

    /**
     * Obtiene el nombre del perfil.
     * @return nombre del perfil.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del perfil.
     * @param name nombre del perfil.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la contraseña del perfil.
     * @return contraseña del perfil.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del perfil.
     * @param password contraseña del perfil.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el estado del perfil.
     * @return estado del perfil.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece el estado del perfil.
     * @param status estado del perfil.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtiene la lista de publicaciones del perfil.
     * @return lista de publicaciones.
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Obtiene la lista de mensajes del perfil.
     * @return lista de mensajes.
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * Obtiene la lista de amigos del perfil.
     * @return lista de amigos.
     */
    public ArrayList<Profile> getFriends() {
        return friends;
    }

    /**
     * Obtiene la lista de solicitudes de amistad recibidas.
     * @return lista de solicitudes de amistad.
     */
    public ArrayList<Profile> getFriendshipRequests() {
        return friendshipRequests;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Profile other = (Profile) obj;
        return name != null && name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.toLowerCase().hashCode() : 0;
    }

    public void addPost(Post post) {
        if (post != null && !posts.contains(post)) {
            posts.add(0, post); // Añadir al inicio para mantener orden cronológico inverso
        }
    }

    public void addFriend(Profile friend) {
        if (friend != null && !friends.contains(friend)) {
            friends.add(friend);
        }
    }
}
