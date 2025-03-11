/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado.model;

import java.util.ArrayList;

/**
 * Clase que xestiona o estado dunha partida ao Aforcado. 
 * Contén a lóxica para probar caracteres, almacenar fallos e verificar o estado do xogo.
 * 
 * @author roi.castrocalvar
 */
public class HangMan {

    private static final int MAX_FAILS = 6; // Número máximo de fallos permitidos antes de perder a partida.
    private HiddenWord hiddenWord; // Obxecto que xestiona a palabra oculta a adiviñar.
    private ArrayList<Character> fails; // Lista de caracteres fallados polo usuario.

    /**
     * Construtor que inicializa a partida co obxecto HiddenWord e a lista de fallos.
     *
     * @param word A palabra a adiviñar.
     */
    public HangMan(String word) {
        this.hiddenWord = new HiddenWord(word);
        this.fails = new ArrayList<>();
    }

    /**
     * Obtén a lista de caracteres fallados polo usuario durante a partida.
     * 
     * @return A lista de caracteres fallados.
     */
    public ArrayList<Character> getFails() {
        return fails;
    }

    /**
     * Obtén unha representación en formato String da lista de caracteres fallados,
     * separados por espazos en branco.
     * 
     * @return Unha cadea de texto cos caracteres fallados separados por espazos.
     */
    public String getStringFails() {
        StringBuilder resultado = new StringBuilder();
        for (Character fail : fails) {
            resultado.append(fail).append(' '); // Engade cada fallo seguido dun espazo.
        }
        return resultado.toString().trim(); // Elimina espazos finais ou iniciais.
    }

    /**
     * Devolve a representación da palabra oculta, mostrando só os caracteres acertados 
     * e substituíndo os restantes por guións.
     * 
     * @return Unha cadea de texto coa palabra parcialmente revelada.
     */
    public String showHiddenWord() {
        return hiddenWord.show();
    }

    /**
     * Devolve a palabra completa, mostrando todos os caracteres sen ocultar.
     * 
     * @return A palabra completa como unha cadea de texto.
     */
    public String showFullWord() {
        return hiddenWord.showFullWord();
    }

    /**
     * Proba se un caracter forma parte da palabra oculta. 
     * Se o caracter non está, engádeo á lista de fallos.
     * 
     * @param c O caracter a probar.
     */
    public void tryChar(char c) {
        if (!hiddenWord.checkChar(c)) {
            if (!fails.contains(c)) { // Evita engadir fallos repetidos.
                fails.add(c); // Engade o caracter fallado á lista.
            }
        }
    }

    /**
     * Comproba se o xogo rematou, ben porque o usuario acertou toda a palabra 
     * ou porque chegou ao número máximo de fallos permitidos.
     * 
     * @return true se o xogo rematou, false en caso contrario.
     */
    public boolean isGameOver() {
        return maxFailsExceeded() || hiddenWord.isVisible();
    }

    /**
     * Comproba se se chegou ao límite máximo de fallos permitidos.
     * 
     * @return true se se superou o número máximo de fallos, false en caso contrario.
     */
    public boolean maxFailsExceeded() {
        return fails.size() >= MAX_FAILS;
    }
}
