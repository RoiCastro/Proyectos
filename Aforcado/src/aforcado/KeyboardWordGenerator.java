/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado;

import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class KeyboardWordGenerator implements WordGenerator {

    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String generateWord() throws GenerateWordException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Escribe la palabra a adivinar");
        word = scan.next();
         // Si la palabra es vacía, lanzamos la excepción
            if (word == null || word.isEmpty()) {
                throw new GenerateWordException("Palabra no válida introducida", true);
            }
        
        return word;

        
    }

}
