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
            menuGenerator.hangMan = new HangMan();
            menuGenerator.showGameMenu();   
        } while (!menuGenerator.showExitMenu());
    }

    private String showInitMenu(){
        Scanner scan = new Scanner(System.in);
        int option;
                do {
            System.out.println("Menu");
            System.out.println("Opciones");
            System.out.println("1. Añade un cliente");
            System.out.println("2. Enseña todos los clientes");
            System.out.println("3. Elimina un cliente");
            System.out.println("4. Enseña la cantidad de clientes");
            System.out.println("5. Salir");
            System.out.println("Elije una opcion");

            option = scan.nextInt();
            String paco = scan.nextLine();

            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Chao que tenga un buen dia");
                    break;
                default:
                    System.out.println("Introduce un numero valido");
                    break;
            }

        } while (option != 5);
        
        return null;
        
    }
    private void showGameMenu(){
        
    }
    private boolean showExitMenu(){
        
        return false;
        
    }
}
