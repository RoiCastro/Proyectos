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

    // Instancia de la clase HangMan que representa el juego del ahorcado
    private HangMan hangMan;

    /**
     * Método principal de ejecución del programa
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Se crea una nueva instancia de MenuGenerator
        MenuGenerator menuGenerator = new MenuGenerator();

        // Bucle para ejecutar el juego mientras el usuario quiera jugar más partidas
        do {
            // Inicializa un nuevo juego de HangMan con una palabra generada aleatoriamente
            menuGenerator.hangMan = new HangMan(menuGenerator.showInitMenu());
            // Muestra el menú del juego y se juega hasta que termine
            menuGenerator.showGameMenu();
        } while (!menuGenerator.showExitMenu()); // Repite el juego si el usuario elige jugar otra vez
    }

    /**
     * Muestra el menú de inicio y genera la palabra oculta
     *
     * @return la palabra generada
     */
    private String showInitMenu() {
        // Se crea una instancia de ArrayWordGenerator para generar una palabra aleatoria
        ArrayWordGenerator wordGenerator = new ArrayWordGenerator();
        // Muestra mensaje de bienvenida y genera la palabra
        System.out.println("Benvido ao Aforcado! Xenerando palabra oculta...");
        return wordGenerator.generateWord(); // Devuelve la palabra generada
    }

    /**
     * Muestra el menú del juego y gestiona las interacciones del usuario
     */
    private void showGameMenu() {
        // Se crea un objeto Scanner para leer las entradas del usuario
        Scanner scan = new Scanner(System.in);
        // Bucle principal del juego: se juega hasta que el juego termine
        while (!hangMan.isGameOver()) {
            // Muestra la palabra oculta (con las letras adivinadas y los guiones)
            System.out.println("Palabra: " + hangMan.showHiddenWord());
            // Muestra el número de fallos realizados
            System.out.println("Fallos: " + hangMan.getStringFails());
            System.out.println(""); // Salto de línea para claridad
            // Pide al usuario que ingrese una letra
            System.out.println("Introduce una letra:");
            // Lee la letra ingresada y la convierte a minúsculas
            char caracter = scan.nextLine().toLowerCase().charAt(0);

            // Intenta adivinar la letra ingresada en el juego
            hangMan.tryChar(caracter);

            // Comprueba si se ha superado el número máximo de fallos
            if (hangMan.maxFailsExceeded()) {
                // Si se exceden los fallos, muestra mensaje de derrota
                System.out.println("Superaches o limite de fallos! Perdeches.");
                // Muestra la palabra completa que no fue adivinada
                System.out.println("A palabra era: " + hangMan.showFullWord());
            }
            // Comprueba si el juego ha terminado con una victoria
            if (hangMan.isGameOver()) {
                // Si el juego terminó, muestra mensaje de victoria
                System.out.println("Ganaste.");
            }
        }
    }

    /**
     * Muestra el menú de salida y pregunta si el usuario quiere jugar otra
     * partida
     *
     * @return true si el usuario elige no jugar más, false si desea jugar otra
     * vez
     */
    private boolean showExitMenu() {
        // Se crea un objeto Scanner para leer la entrada del usuario
        Scanner scan = new Scanner(System.in);
        // Pide al usuario que indique si desea jugar otra partida
        System.out.print("Queres xogar outra partida? (s/n): ");
        // Lee la respuesta y la convierte a minúsculas
        char response = scan.nextLine().toLowerCase().charAt(0);
        // Devuelve verdadero si la respuesta es diferente de 's' (es decir, 'no' para jugar otra partida)
        return response != 's';
    }
}
