/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.view;

import com.tacebook.controller.InitMenuController;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase que representa la vista del menú inicial de la aplicación.
 */
public class InitMenuView {

    private InitMenuController initMenuController;

    public InitMenuView(InitMenuController controller) {
        this.initMenuController = controller;
    }

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
                    break;
                case "3":
                    System.out.println("Saliendo de la aplicación...");
                    return true;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
            }
        }
    }

    public void showLoginErrorMessage() {
        System.out.println("Error: Usuario o contraseña incorrectos.");
    }

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
                System.out.println("Las contraseñas no coinciden, inténtalo de nuevo.");
                continue;
            }
            break;
        }

        System.out.print("Estado: ");
        status = scanner.nextLine();

        initMenuController.createProfile(name, password, status);
    }

    public String showNewNameMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("El nombre de usuario introducido ya está en uso. Por favor, introduce un nuevo nombre:");
        return scanner.nextLine();
    }

    private int readNumber(Scanner scanner) {
        try {
            int number = scanner.nextInt();
            scanner.nextLine(); // Limpiar salto de línea
            return number;
        } catch (NoSuchElementException | NumberFormatException e) {
            scanner.nextLine(); // Limpiar entrada incorrecta
            System.out.println("Debes introducir un número. Inténtalo de nuevo.");
            return readNumber(scanner);
        }
    }

    public void showConnectionErrorMessage() {
        System.out.println("Erro na conexión co almacén de datos!");
    }

    public void showReadErrorMessage() {
        System.out.println("Erro na lectura de datos!");
    }

    public void showWriteErrorMessage() {
        System.out.println("Erro na escritura dos datos!");
    }
}
