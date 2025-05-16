/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.controller;

import com.tacebook.view.*;
import com.tacebook.model.*;
import com.tacebook.persistencia.*;

public class InitMenuController {

    private InitMenuView initMenuView = new InitMenuView(this);

    public static void main(String[] args) {
        InitMenuController controller = new InitMenuController();
        controller.init();
    }

    private void init() {
        boolean salir = false;
        while (!salir) {
            salir = initMenuView.showLoginMenu();
        }
    }

    public void login(String name, String password) {
        try {
            ProfileController profileController = new ProfileController();
            Profile profile = ProfileDB.findByNameAndPassword(name, password, profileController.getPostsShowed());
            if (profile == null) {
                initMenuView.showLoginErrorMessage();
            } else {
                profileController.openSession(profile);
            }
        } catch (PersistenceException e) {
            System.out.println("Erro ao iniciar sesión: " + e.getMessage());
        }
    }

    public void register() {
        initMenuView.showRegisterMenu();
    }

    public void createProfile(String name, String password, String status) {
        try {
            Profile existingProfile = ProfileDB.findByName(name, 0);
            while (existingProfile != null) {
                name = initMenuView.showNewNameMenu();
                existingProfile = ProfileDB.findByName(name, 0);
            }

            Profile profile = new Profile(name, password, status);
            ProfileDB.save(profile);

            ProfileController profileController = new ProfileController();
            profileController.openSession(profile);
        } catch (PersistenceException e) {
            System.out.println("Erro ao crear perfil: " + e.getMessage());
        }
    }
}
