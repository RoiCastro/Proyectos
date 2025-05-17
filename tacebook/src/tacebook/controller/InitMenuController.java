/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.controller;

import tacebook.model.Profile;
import tacebook.persistencia.PersistenceException;
import tacebook.persistencia.ProfileDB;
import tacebook.persistencia.TacebookDB;
import tacebook.view.InitMenuView;
import tacebook.view.TextInitMenuView;

public class InitMenuController {

    private InitMenuView initMenuView = new TextInitMenuView(this);
    private boolean textMode;
    

    /**
     *
     * @param textMode Si se va a mostrar el menu por consola = false , interfaz
     * grafica = false
     */
    public InitMenuController(boolean textMode) {
        this.textMode = textMode;
        if (textMode) {
            this.initMenuView = new TextInitMenuView(this);  // Vista de texto
        } else {
            this.initMenuView = new GUIInitMenuView(this);   // Vista gráfica
        }
    }

    public static void main(String[] args) {
        //parametros por consola
        boolean textMode = true;
        if (args.length > 0 && args[0].equals("text")) {
            textMode = true;
        }
        InitMenuController controller = new InitMenuController(textMode);

        controller.init();
        TacebookDB.close();
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
