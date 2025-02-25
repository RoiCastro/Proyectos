/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado;

import java.nio.charset.Charset;
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
    public String generateWord() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escribe la palabra a adivinar");
        char[] pass = System.console().readPassword();
        word = String.copyValueOf(pass);
        return word;

    }

}
