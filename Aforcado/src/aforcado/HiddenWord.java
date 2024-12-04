/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aforcado;

import java.util.ArrayList;

/**
 *
 * @author roi.castrocalvar
 */
public class HiddenWord {
    
    private char[] characters;
    private ArrayList<Boolean> hits ; 

    public HiddenWord() {
        WordGenerator word = new WordGenerator();
        characters = word.generateWord().toCharArray();
        for(int i=0;i<characters.length;i++){
            hits.add(false);
        }
    }

    public HiddenWord(char[] characters) {
        this.characters = characters;
    }

    
    public boolean checkChar(char c){
        
        return false;
        
    }
    public String show(){
        
        return null;
        
    }
    public String showFullWord(){
        
        return null;
        
    }
    public boolean isVisible(){
        
        return false;
        
    }
}
