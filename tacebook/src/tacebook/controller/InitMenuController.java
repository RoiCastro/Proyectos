/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.controller;

import tacebook.model.Profile;
import tacebook.persistencia.PersistenceException;
import tacebook.persistencia.ProfileDB;
import tacebook.persistencia.TacebookDB;
import tacebook.view.GUIInitMenuView;
import tacebook.view.InitMenuView;
import tacebook.view.TextInitMenuView;

/**
 * Controlador principal para el menú de inicio de Tacebook.
 * Permite iniciar sesión, registrar nuevos perfiles y seleccionar el modo de
 * interfaz (texto o gráfica).
 */
public class InitMenuController {

    private InitMenuView initMenuView = new TextInitMenuView(this);
    private boolean textMode;

    /**
     * Constructor del controlador del menú de inicio.
     * 
     * @param textMode Si es true, se utiliza la vista de texto; si es false, se
     *                 utiliza la vista gráfica.
     */
    public InitMenuController(boolean textMode) {
        this.textMode = textMode;
        if (textMode) {
            this.initMenuView = new TextInitMenuView(this); // Vista de texto
        } else {
            this.initMenuView = new GUIInitMenuView(this); // Vista gráfica
        }
    }

    /**
     * Método principal de la aplicación.
     * 
     * @param args Argumentos de la línea de comandos. Si contiene "text", se inicia
     *             en modo texto.
     */
    public static void main(String[] args) {
        // parametros por consola
        boolean textMode = false;
        if (args.length == 1 && args[0].equals("text")) {
            textMode = true;
        }
        InitMenuController controller = new InitMenuController(textMode);

        controller.init();
        TacebookDB.close();

        // Si estamos en modo gráfico, no terminar el programa aquí
        // ya que la interfaz gráfica necesita mantener el hilo principal
        if (textMode) {
            System.exit(0);
        }
    }

    /**
     * Inicializa el menú de inicio y gestiona el bucle principal de la aplicación.
     */
    public void init() {
        boolean salir = false;
        while (!salir) {
            salir = initMenuView.showLoginMenu();
        }
    }

    /**
     * Intenta iniciar sesión con el nombre y contraseña proporcionados.
     * Si las credenciales son incorrectas, muestra un mensaje de error.
     * 
     * @param name     Nombre de usuario.
     * @param password Contraseña del usuario.
     */
    public void login(String name, String password) {
        try {
            ProfileController profileController = new ProfileController(textMode);
            Profile profile = ProfileDB.findByNameAndPassword(name, password, profileController.getPostsShowed());

            if (profile == null) {
                initMenuView.showLoginErrorMessage();
                // No retornamos, el bucle principal seguirá ejecutándose
            } else {
                profileController.openSession(profile);
            }
        } catch (PersistenceException e) {
            initMenuView.showConnectionErrorMessage();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao iniciar sesión: " + e.getMessage());
        }
    }

    /**
     * Muestra el menú de registro para crear un nuevo perfil.
     */
    public boolean register() {
        try {
            return initMenuView.showRegisterMenu();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao mostrar o menú de rexistro: " + e.getMessage());
            return false;
        }
    }

    /**
     * Crea un nuevo perfil con el nombre, contraseña y estado proporcionados.
     * Si el nombre ya existe, solicita uno nuevo.
     * 
     * @param name     Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param status   Estado del usuario.
     */
    public void createProfile(String name, String password, String status) {
        try {
            Profile existingProfile = ProfileDB.findByName(name, 0);
            while (existingProfile != null || name == null || name.trim().isEmpty()) {
                name = initMenuView.showNewNameMenu();
                if (name == null || name.trim().isEmpty()) {
                    System.out.println("Nome de usuario non pode estar baleiro.");
                    return; // Evita bucle infinito si el usuario cancela
                }
                existingProfile = ProfileDB.findByName(name, 0);
            }

            Profile profile = new Profile(name, password, status);
            ProfileDB.save(profile);

            ProfileController profileController = new ProfileController(textMode);
            profileController.openSession(profile);
        } catch (PersistenceException e) {
            initMenuView.showConnectionErrorMessage();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao crear perfil: " + e.getMessage());
        }
    }
}
