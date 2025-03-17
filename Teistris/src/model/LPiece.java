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
public class LPiece extends Piece {

    /**
     * Construtor da clase, que crea os cadrados que forman a peza
     */
    public LPiece(Game game) {
        this.game = game;

        squares = new Square[4];

        squares[0] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, 0, Color.ORANGE, game);
        squares[1] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE, Color.ORANGE, game);
        squares[2] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE * 2, Color.ORANGE, game);
        squares[3] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE * 2, Color.ORANGE, game);
    }

    @Override
    public boolean rotate() {
        int pivotX = squares[1].getX();
        int pivotY = squares[1].getY();

        // Intentamos calcular las nuevas posiciones
        int[][] newPositions = new int[4][2];

        for (int i = 0; i < squares.length; i++) {
            int relativeX = squares[i].getX() - pivotX;
            int relativeY = squares[i].getY() - pivotY;

            // Aplicar rotación 90°: (x, y) -> (-y, x)
            newPositions[i][0] = pivotX - relativeY;
            newPositions[i][1] = pivotY + relativeX;
        }

        // Verificamos si la rotación es válida (no colisiona con bordes u otras piezas)
        for (int i = 0; i < squares.length; i++) {
            if (!game.isValidPosition(newPositions[i][0], newPositions[i][1])) {
                return false; // No podemos rotar
            }
        }

        // Aplicamos las nuevas posiciones
        for (int i = 0; i < squares.length; i++) {
            squares[i].setX(newPositions[i][0]);
            squares[i].setY(newPositions[i][1]);
        }

        return true;
    }

}
