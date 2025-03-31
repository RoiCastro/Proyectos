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
public class SquarePiece extends Piece {

    /**
     * Construtor da clase, que crea os cadrados que forman a peza
     */
    public SquarePiece(Game game) {
        this.game = game;

        squares = new Square[4];

        squares[0] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, 0, Color.YELLOW, game);
        squares[1] = new Square(Game.MAX_X / 2, 0, Color.YELLOW, game);
        squares[2] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE,
                Color.YELLOW, game);
        squares[3] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE, Color.YELLOW, game);
    }
    

}
