/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.persistencia;

import com.tacebook.model.*;

public class PostDB {

    public static void save(Post post) throws PersistenceException {
        Profile profile = post.getAuthor();
        profile.getPosts().add(0, post); // Engadir ao comezo da lista
    }

    public static void saveLike(Post post, Profile profile) throws PersistenceException {
        if (!post.getProfileLikes().contains(profile)) {
            post.getProfileLikes().add(profile);
        }
    }
}
