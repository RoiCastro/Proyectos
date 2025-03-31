/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tacebook.view;

import com.tacebook.controller.InitMenuController;
import java.util.Scanner;

public class InitMenuView {

    private InitMenuController controller;
    private Scanner scan = new Scanner(System.in);

    public boolean showLoginMenu() {
        System.out.println("1. Inicio de sesion");
        System.out.println("2. Crear Usuario");
        System.out.println("3. Sair");

        int var = scan.nextInt();
        switch (var) {
            case 1:
                controller.login();
                break;
            case 2:
                controller.register();
                break;
            case 3:
                return true;
            default:
                throw new AssertionError();
        }
        return false;
    }

    public void showLoginErrorMessage() {
        System.out.println("Contrasinal incorrecta");
    }

    public void showRegisterMenu() {
        System.out.println("Escribe a continuacion tu nombre de usuario");
        String name = scan.next();
        //Ponemos las dos contraseñas a distintas para entrar al bucle
        String contrasinal = "";
        String contrasinal2 = null;
        while (contrasinal != contrasinal2) {
            System.out.println("Escribe a continuacion tu contraseña");
            contrasinal = scan.next();
            System.out.println("Escribe de nuevo la contraseña");
            contrasinal2 = scan.next();
            if (contrasinal != contrasinal2)  {
                System.out.println("La contraseña tiene que coincidir");
            }
            controller.createProfile();
        }

    }
    public String showNewNameMenu(){
        System.out.println("O nome xa esta en uso");
        System.out.println("Escribe un novo nome:");
        return scan.nextLine();
    }
}
