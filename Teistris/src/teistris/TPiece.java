/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teistris;

import java.awt.Color;

/**
 *
 * @author aitor.martinezparente
 */
public class TPiece extends Piece {
    
    /**
     * Construtor da clase, que crea os cadrados que forman a peza
     */
    public TPiece(Game game) {
        this.game = game;

        squares = new Square[4];

        squares[0] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, 0, Color.RED, game);
        squares[1] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE, Color.RED, game);
        squares[2] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE * 2, Color.RED, game);
        squares[3] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE, Color.RED, game);
    }
}
