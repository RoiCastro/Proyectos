/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aforcado;

import java.util.Scanner;

/**
 *
 * @author roi.castrocalvar
 */
public class MenuGenerator {

    private HangMan hangMan;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MenuGenerator menuGenerator = new MenuGenerator();

        do {
            menuGenerator.hangMan = new HangMan(menuGenerator.showInitMenu());
            menuGenerator.showGameMenu();
        } while (!menuGenerator.showExitMenu());
    }

    private String showInitMenu() {
        Scanner scan = new Scanner(System.in);
        int option;

        do {
            System.out.println("Posivilidades");
            System.out.println("1. Iniciar el juego");
            System.out.println("2. Para el futro");
            System.err.println("3. Salir");

            option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1:
                    while (!hangMan.isGameOver()) {
                        System.out.println("Palabra: " + hangMan.showHiddenWord());
                        System.out.println("Fallos: " + hangMan.getStringFails());
                        System.out.print("Introduce unha letra: ");
                        char c = scan.nextLine().charAt(0);
                        hangMan.tryChar(c);
                    }
                case 2:
                    System.out.println("Aun no hace nada");
                    break;
                case 3:
                    System.out.println("Chao que tenga un buen dia");
                    break;
                default:
                    System.out.println("Introduce un numero valido");
                    System.out.println("");
                    break;

            }

        } while (option != 3);

        return null;

    }

    private void showGameMenu() {
    }

    private boolean showExitMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Queres xogar outra partida? (s/n): ");
        char response = scan.nextLine().toLowerCase().charAt(0);
        return response != 's';

    }
}
