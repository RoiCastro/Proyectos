/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package visteisminas;

import java.util.ArrayList;

/**
 *
 * @author aitor.martinezparente
 */
public class Game {
    
    // todos los return por ahora son para que no salte en rojo
    // los datos por ahora seran de prueba
    
    private Cell cells [][];
    
    private int raws;
    private int columns;

    public int getRaws() {
        return raws;
    }

    public int getColumns() {
        return columns;
    }
    
    public Cell getCell(int raw, int column){
        
        return null;
    }
    
    private ArrayList<Cell>getAdjacentCells(Cell cell){
        
        return null;
    }
    
    public int getAdjacentMines(Cell cell){
        
        return 0;
    }
    
    public void openCell(Cell cell){
        
    }
    
    public void openAllMines(){
        
    }
    
    public boolean checkCellsToOpen(){
        
        return true;
    }
    
    private void fillMines(int mines){
        
    }
    
    public Game(int raws, int columns, int mines){
        
        Game game = new Game(6, 6, 3);
        
    }
}
