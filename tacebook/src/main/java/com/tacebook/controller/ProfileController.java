/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.controller;

import com.tacebook.model.*;
import com.tacebook.view.*;
import com.tacebook.persistencia.*;
import java.util.Date;

public class ProfileController {

    private ProfileView profileView = new ProfileView(this);
    private Profile sessionProfile;
    private Profile shownProfile;

    public Profile getSessionProfile() {
        return sessionProfile;
    }

    public Profile getShownProfile() {
        return shownProfile;
    }

    public void setShownProfile(Profile profile) {
        this.shownProfile = profile;
        reloadProfile();
    }

    public int getPostsShowed() {
        return profileView.getPostsShowed();
    }

    public void reloadProfile() {
        try {
            Profile updatedProfile = ProfileDB.findByName(shownProfile.getName(), profileView.getPostsShowed());
            this.shownProfile = updatedProfile;
            profileView.showProfileMenu(shownProfile);
        } catch (PersistenceException e) {
            System.out.println("Erro ao recargar o perfil: " + e.getMessage());
        }
    }

    public void openSession(Profile sessionProfile) {
        this.sessionProfile = sessionProfile;
        this.shownProfile = sessionProfile;
        profileView.showProfileMenu(shownProfile);
    }

    public void updateProfileStatus(String newStatus) {
        try {
            sessionProfile.setStatus(newStatus);
            ProfileDB.update(sessionProfile);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao actualizar estado: " + e.getMessage());
        }
    }

    public void newPost(String text, Profile destProfile) {
        try {
            Post post = new Post(0, new Date(), text);
            post.setAuthor(sessionProfile);
            PostDB.save(post);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao crear publicación: " + e.getMessage());
        }
    }

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

    public void newFriendshipRequest(String profileName) {
        try {
            Profile destProfile = ProfileDB.findByName(profileName, profileView.getPostsShowed());

            if (destProfile != null
                    && !sessionProfile.getFriends().contains(destProfile)
                    && !sessionProfile.getFriendshipRequests().contains(destProfile)
                    && !destProfile.getFriendshipRequests().contains(sessionProfile)) {
                ProfileDB.saveFrienshipRequest(destProfile, sessionProfile);
            }
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao enviar solicitude de amizade: " + e.getMessage());
        }
    }

    public void acceptFriendshipRequest(Profile sourceProfile) {
        try {
            ProfileDB.removeFrienshipRequest(sessionProfile, sourceProfile);
            ProfileDB.saveFriendship(sessionProfile, sourceProfile);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao aceptar amizade: " + e.getMessage());
        }
    }

    public void rejectFriendshipRequest(Profile sourceProfile) {
        try {
            ProfileDB.removeFrienshipRequest(sessionProfile, sourceProfile);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao rexeitar amizade: " + e.getMessage());
        }
    }

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

    public void deleteMessage(Message message) {
        try {
            MessageDB.remove(message);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao eliminar mensaxe: " + e.getMessage());
        }
    }

    public void markMessageAsRead(Message message) {
        try {
            message.setRead(true);
            MessageDB.update(message);
            reloadProfile();
        } catch (PersistenceException e) {
            System.out.println("Erro ao marcar mensaxe como lida: " + e.getMessage());
        }
    }

    public void replyMessage(Message message, String text) {
        try {
            message.setRead(true);
            MessageDB.update(message);
            newMessage(message.getSourceProfile(), text);
        } catch (PersistenceException e) {
            System.out.println("Erro ao responder mensaxe: " + e.getMessage());
        }
    }
}
