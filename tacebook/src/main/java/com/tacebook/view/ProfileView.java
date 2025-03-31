/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.view;

import com.tacebook.controller.ProfileController;
import com.tacebook.model.Profile;
import java.util.Scanner;

/**
 *
 * @author roi.castrocalvar
 */
public class ProfileView {
    
    private int postsShowed = 10;
    private ProfileController profileController;

    public int getPostsShowed() {
        return postsShowed;
    }

    public ProfileView(ProfileController profileController) {
        this.profileController = profileController;
    }
    
    private void showProfileInfo(boolean ownProfile, Profile profile) {
    // Comprobar si es el propio perfil
    if (ownProfile) {
        // Mostrar el nombre y estado actual del perfil
        System.out.println("Nombre: " + profile.getName());
        System.out.println("Estado actual: " + profile.getStatus());
    } else {
        System.out.println("Este es el perfil de otro usuario.");
        System.out.println("Nombre: " + profile.getName());
        System.out.println("Estado actual: " + profile.getStatus());
    }
}

    private void changeStatus(boolean ownProfile, Scanner scanner, Profile profile){
        
    }
    public void showProfileMenu(Profile profile){
        
    }
}
