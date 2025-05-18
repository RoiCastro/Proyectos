/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tacebook.view;

import java.util.Scanner;
import tacebook.controller.InitMenuController;

/**
 * Clase que representa la vista de texto del menú inicial de la aplicación.
 * Permite al usuario iniciar sesión, registrarse o salir mediante consola.
 */
public class TextInitMenuView implements InitMenuView {

    private InitMenuController initMenuController;

    /**
     * Constructor de la vista de menú inicial en modo texto.
     * 
     * @param controller el controlador del menú inicial
     */
    public TextInitMenuView(InitMenuController controller) {
        this.initMenuController = controller;
    }

    /**
     * Muestra el menú de inicio de sesión y registro por consola.
     * 
     * @return true si se debe salir del bucle principal, false para continuar
     */
    @Override
    public boolean showLoginMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- MENÚ INICIAL ---");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear nuevo perfil");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Nombre de usuario: ");
                    String name = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String password = scanner.nextLine();
                    initMenuController.login(name, password);
                    break;
                case "2":
                    initMenuController.register();
                    return true; // <-- Añadido: salir del bucle tras registro
                case "3":
                    System.out.println("Saliendo de la aplicación...");
                    return true;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
    }

    /**
     * Muestra un mensaje de error cuando el login es incorrecto.
     */
    @Override
    public void showLoginErrorMessage() {
        System.out.println("Error: Usuario o contraseña incorrectos.");
    }

    /**
     * Muestra el menú de registro de usuario por consola.
     */
    @Override
    public void showRegisterMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n--- REGISTRO DE NUEVO PERFIL ---");
        String name;
        String password;
        String password2;
        String status;

        while (true) {
            System.out.print("Nombre de usuario: ");
            name = scanner.nextLine();

            System.out.print("Contraseña: ");
            password = scanner.nextLine();

            System.out.print("Repite la contraseña: ");
            password2 = scanner.nextLine();

            if (!password.equals(password2)) {
                System.out.println("Las contraseñas no coinciden, volviendo al menú de inicio de sesión.");
                return; // Volver al menú de inicio de sesión
            }
            break;
        }

        System.out.print("Estado: ");
        status = scanner.nextLine();

        initMenuController.createProfile(name, password, status);
    }

    /**
     * Muestra un mensaje para introducir un nuevo nombre de usuario cuando el anterior ya existe.
     * 
     * @return el nuevo nombre de usuario introducido
     */
    @Override
    public String showNewNameMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("El nombre de usuario introducido ya está en uso. Por favor, introduce un nuevo nombre:");
        return scanner.nextLine();
    }

    /**
     * Lee un número desde la entrada estándar.
     * 
     * @param scanner el objeto Scanner para leer la entrada
     * @return el número leído
     */
    private int readNumber(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número. Inténtalo de nuevo.");
            }
        }
    }

    /**
     * Muestra un mensaje de error de conexión.
     */
    @Override
    public void showConnectionErrorMessage() {
        System.out.println("Erro na conexión co almacén de datos!");
    }

    /**
     * Muestra un mensaje de error de lectura de datos.
     */
    @Override
    public void showReadErrorMessage() {
        System.out.println("Erro na lectura de datos!");
    }

    /**
     * Muestra un mensaje de error de escritura de datos.
     */
    @Override
    public void showWriteErrorMessage() {
        System.out.println("Erro na escritura dos datos!");
    }
}
