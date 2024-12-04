/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aforcado;

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

    private String showInitMenu(){
        
        return null;
        
    }
    private void showGameMenu(){
        
    }
    private boolean showExitMenu(){
        
        return false;
        
    }
}
