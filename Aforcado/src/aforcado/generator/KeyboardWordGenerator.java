/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado.generator;

import aforcado.ui.GenerateWordException;
import java.io.Console;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class KeyboardWordGenerator implements WordGenerator {
@Override
    public String generateWord() throws GenerateWordException {
        String word = null;
        Console console = System.console();
        
        if (console != null) {
            // Si tenemos acceso a un terminal (no desde el IDE), usamos readPassword() para ocultar la entrada
            char[] input = console.readPassword("Escribe la palabra a adivinar: ");
            word = new String(input);
        } else {
            // Si estamos en un entorno sin terminal (como un IDE), usamos Scanner
            Scanner scan = new Scanner(System.in);
            System.out.println("Escribe la palabra a adivinar:");
            word = scan.next();
        }

        // Si la palabra es vacía o nula, lanzamos la excepción
        if (word == null || word.isEmpty()) {
            throw new GenerateWordException("Palabra no válida introducida", true);
        }

        return word;
    }

}
