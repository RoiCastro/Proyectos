/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.controller;

import java.util.Date; // Puede ser
import tacebook.model.Comment;
import tacebook.model.Message;
import tacebook.model.Post;
import tacebook.model.Profile;
import tacebook.persistencia.CommentDB;
import tacebook.persistencia.MessageDB;
import tacebook.persistencia.PersistenceException;
import tacebook.persistencia.PostDB;
import tacebook.persistencia.ProfileDB;
import tacebook.view.GUIProfileView;
import tacebook.view.ProfileView;
import tacebook.view.TextProfileView;

/**
 * Controlador para la gestión de perfiles en Tacebook.
 * Permite gestionar la sesión, publicaciones, comentarios, likes, amistades y
 * mensajes.
 */
public class ProfileController {

    private ProfileView profileView = new TextProfileView(this);
    private Profile sessionProfile;
    private Profile shownProfile;
    private boolean textMode;

    /**
     * Crea un nuevo controlador de perfil.
     * 
     * @param textMode Si es true, se utiliza la vista de texto; si es false, se
     *                 utiliza la vista gráfica.
     */
    public ProfileController(boolean textMode) {
        this.textMode = textMode;

        // Instanciamos la vista dependiendo del valor de textMode
        if (textMode) {
            this.profileView = new TextProfileView(this); // Vista en modo texto
        } else {
            this.profileView = new GUIProfileView(this); // Vista en modo gráfico (GUI)
        }
    }

    /**
     * Obtiene el perfil de la sesión actual.
     * 
     * @return El perfil de la sesión.
     */
    public Profile getSessionProfile() {
        return this.sessionProfile;
    }

    /**
     * Obtiene el perfil actualmente mostrado.
     * 
     * @return El perfil mostrado.
     */
    public Profile getShownProfile() {
        return this.shownProfile;
    }

    /**
     * Establece el perfil que se va a mostrar y lo recarga.
     * 
     * @param profile Perfil a mostrar.
     */
    public void setShownProfile(Profile profile) {
        this.shownProfile = profile;
        reloadProfile();
    }

    /**
     * Devuelve el número de publicaciones que se muestran.
     * 
     * @return Número de publicaciones mostradas.
     */
    public int getPostsShowed() {
        return this.profileView.getPostsShowed();
    }

    /**
     * Recarga el perfil mostrado desde la base de datos.
     */
    public void reloadProfile() {
        try {
            this.shownProfile = ProfileDB.findByName(this.shownProfile.getName(), this.profileView.getPostsShowed());
        } catch (PersistenceException e) {
            System.out.println("Erro ao recargar o perfil: " + e.getMessage());

            this.profileView.showProfileMenu(this.shownProfile);
        }
    }

    /**
     * Abre una sesión con el perfil proporcionado y muestra el menú de perfil.
     * 
     * @param sessionProfile Perfil de la sesión.
     */
    public void openSession(Profile sessionProfile) {
        this.sessionProfile = sessionProfile;
        this.shownProfile = sessionProfile;
        profileView.showProfileMenu(shownProfile);

        if (profileView instanceof GUIProfileView gUIProfileView) {
            gUIProfileView.setVisible(true); // AQUI se fai visible
        }
    }

    /**
     * Actualiza el estado del perfil de la sesión.
     * 
     * @param newStatus Nuevo estado.
     */
    public void updateProfileStatus(String newStatus) {
        try {
            this.sessionProfile.setStatus(newStatus);
            ProfileDB.update(this.sessionProfile);
        } catch (PersistenceException e) {
            System.out.println("Erro ao actualizar estado: " + e.getMessage());
        }

        this.reloadProfile();
    }

    /**
     * Crea una nueva publicación en el perfil de destino.
     * 
     * @param text        Texto de la publicación.
     * @param destProfile Perfil de destino.
     */
    public void newPost(String text, Profile destProfile) {
        try {
            if (text == null || text.trim().isEmpty() || destProfile == null) {
                return;
            }
            Post post = new Post(0, new Date(), text.trim());
            post.setAuthor(sessionProfile);
            PostDB.save(post);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao crear publicación: " + e.getMessage());
        }
    }

    /**
     * Añade un nuevo comentario a una publicación.
     * 
     * @param post        Publicación a comentar.
     * @param commentText Texto del comentario.
     */
    public void newComment(Post post, String commentText) {
        try {
            Comment comment = new Comment(0, new Date(), commentText);
            comment.setSourceProfile(sessionProfile);
            comment.setPost(post);
            CommentDB.save(comment);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao engadir comentario: " + e.getMessage());
        }
    }

    /**
     * Da like a una publicación si no es del propio usuario y no se ha dado like
     * antes.
     * 
     * @param post Publicación a la que dar like.
     */
    public void newLike(Post post) {
        try {
            if (!post.getAuthor().equals(sessionProfile)
                    && !post.getProfileLikes().contains(sessionProfile)) {
                PostDB.saveLike(post, sessionProfile);
            }
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao dar like: " + e.getMessage());
        }
    }

    /**
     * Envía una solicitud de amistad a otro perfil.
     * 
     * @param profileName Nombre del perfil de destino.
     */
    public void newFriendshipRequest(String profileName) {
        try {
            Profile destProfile = ProfileDB.findByName(profileName, profileView.getPostsShowed());

            if (destProfile == null) {
                profileView.showProfileNotFoundMessage();
                return;
            }
            if (destProfile.equals(sessionProfile)) {
                if (profileView instanceof tacebook.view.GUIProfileView gui) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Non podes enviarte unha solicitude de amizade a ti mesmo.", "Erro", javax.swing.JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println("Non podes enviarte unha solicitude de amizade a ti mesmo.");
                }
                return;
            }
            if (!sessionProfile.getFriends().contains(destProfile)
                    && !sessionProfile.getFriendshipRequests().contains(destProfile)
                    && !destProfile.getFriendshipRequests().contains(sessionProfile)) {
                ProfileDB.saveFriendshipRequest(destProfile, sessionProfile);
            }
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao enviar solicitude de amizade: " + e.getMessage());
        }
    }

    /**
     * Acepta una solicitud de amistad recibida.
     * 
     * @param sourceProfile Perfil que envió la solicitud.
     */
    public void acceptFriendshipRequest(Profile sourceProfile) {
        try {
            ProfileDB.removeFriendshipRequest(sessionProfile, sourceProfile);
            ProfileDB.saveFriendship(sessionProfile, sourceProfile);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao aceptar amizade: " + e.getMessage());
        }
    }

    /**
     * Rechaza una solicitud de amistad recibida.
     * 
     * @param sourceProfile Perfil que envió la solicitud.
     */
    public void rejectFriendshipRequest(Profile sourceProfile) {
        try {
            ProfileDB.removeFriendshipRequest(sessionProfile, sourceProfile);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao rexeitar amizade: " + e.getMessage());
        }
    }

    /**
     * Envía un nuevo mensaje a otro perfil.
     * 
     * @param destProfile Perfil de destino.
     * @param text        Texto del mensaje.
     */
    public void newMessage(Profile destProfile, String text) {
        try {
            Message message = new Message(0, text, new Date(), false);
            message.setSourceProfile(sessionProfile);
            message.setDestProfile(destProfile);
            MessageDB.save(message);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao enviar mensaxe: " + e.getMessage());
        }
    }

    /**
     * Elimina un mensaje.
     * 
     * @param message Mensaje a eliminar.
     */
    public void deleteMessage(Message message) {
        try {
            MessageDB.remove(message);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao eliminar mensaxe: " + e.getMessage());
        }
    }

    /**
     * Marca un mensaje como leído.
     * 
     * @param message Mensaje a marcar como leído.
     */
    public void markMessageAsRead(Message message) {
        try {
            message.setRead(true);
            MessageDB.update(message);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao marcar mensaxe como lida: " + e.getMessage());
        }
    }

    /**
     * Responde a un mensaje recibido.
     * 
     * @param message Mensaje original.
     * @param text    Texto de la respuesta.
     */
    public void replyMessage(Message message, String text) {
        try {
            message.setRead(true);
            MessageDB.update(message);
            newMessage(message.getSourceProfile(), text);
        } catch (PersistenceException e) {
            System.out.println("Erro ao responder mensaxe: " + e.getMessage());
        }
    }

    public void showProfileMenu(Profile profile) {
        if (profile == null) return;
        
        // Crear un nuevo perfil temporal para mostrar
        Profile temp = new Profile(profile.getName(), profile.getPassword(), profile.getStatus());
        
        // Recoger todos los posts (propios y de amigos) si es el perfil de sesión
        java.util.Set<Post> allPosts = new java.util.TreeSet<>((a, b) -> {
            int dateCompare = b.getDate().compareTo(a.getDate());
            return dateCompare != 0 ? dateCompare : Integer.compare(b.getId(), a.getId());
        });
        
        // Añadir posts propios
        if (profile.getPosts() != null) {
            allPosts.addAll(profile.getPosts());
        }
        
        // Si es el perfil propio, añadir también los posts de amigos
        if (profile.equals(sessionProfile)) {
            for (Profile friend : profile.getFriends()) {
                if (friend != null && friend.getPosts() != null) {
                    allPosts.addAll(friend.getPosts());
                }
            }
        }
        
        // Convertir a lista y limitar número de posts
        java.util.List<Post> toShow = new java.util.ArrayList<>(allPosts);
        int max = Math.min(getPostsShowed(), toShow.size());
        if (max > 0) {
            toShow = toShow.subList(0, max);
        }
        
        // Añadir los posts y resto de información al perfil temporal
        temp.getPosts().addAll(toShow);
        temp.getFriends().addAll(profile.getFriends());
        temp.getFriendshipRequests().addAll(profile.getFriendshipRequests());
        temp.getMessages().addAll(profile.getMessages());
        
        // Mostrar el perfil temporal
        profileView.showProfileMenu(temp);
    }
}
