/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;

/**
 *
 * @author aitor.martinezparente
 */
public class BarPiece extends Piece {

    private boolean isVertical = false;

    /**
     * Construtor da clase, que crea os cadrados que forman a peza
     */
    public BarPiece(Game game) {
        this.game = game;

        squares = new Square[4];

        squares[0] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE * 2, 0, Color.CYAN, game);
        squares[1] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, 0, Color.CYAN, game);
        squares[2] = new Square(Game.MAX_X / 2, 0, Color.CYAN, game);
        squares[3] = new Square(Game.MAX_X / 2 + Game.SQUARE_SIDE, 0, Color.CYAN, game);
    }

    @Override
    public boolean rotate() {
        int pivotX = squares[2].getX(); // Usamos el tercer cuadrado como pivote
        int pivotY = squares[2].getY();

        // Matriz para almacenar la nueva posición de cada cuadrado
        int[][] newPositions = new int[4][2];

        if (isVertical) {
            // Cambiar a horizontal
            newPositions[0] = new int[]{pivotX - 2 * Game.SQUARE_SIDE, pivotY};
            newPositions[1] = new int[]{pivotX - Game.SQUARE_SIDE, pivotY};
            newPositions[2] = new int[]{pivotX, pivotY}; // Pivote
            newPositions[3] = new int[]{pivotX + Game.SQUARE_SIDE, pivotY};
        } else {
            // Cambiar a vertical
            newPositions[0] = new int[]{pivotX, pivotY - 2 * Game.SQUARE_SIDE};
            newPositions[1] = new int[]{pivotX, pivotY - Game.SQUARE_SIDE};
            newPositions[2] = new int[]{pivotX, pivotY}; // Pivote
            newPositions[3] = new int[]{pivotX, pivotY + Game.SQUARE_SIDE};
        }

        // Verificar si la nueva posición es válida antes de aplicar la rotación
        for (int i = 0; i < 4; i++) {
            int newX = newPositions[i][0];
            int newY = newPositions[i][1];

            if (!game.isValidPosition(newX, newY)) {
                return false; // No podemos rotar si alguna posición no es válida
            }
        }

        // Aplicamos las nuevas posiciones
        for (int i = 0; i < 4; i++) {
            squares[i].setX(newPositions[i][0]);
            squares[i].setY(newPositions[i][1]);
        }

        // Alternamos el estado de la rotación
        isVertical = !isVertical;

        return true;
    }
}
