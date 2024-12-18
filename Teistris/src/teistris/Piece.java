/*
 * Copyright (C) 2019 Antonio de Andrés Lema
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package teistris;

import java.awt.Color;

/**
 * Clase que implementa a peza cadrada do xogo do Tetris
 *
 * @author Profe de Programación
 */
public class Piece {

    private Square[] squares;

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    /**
     * Referenza ao obxecto xogo
     */
    private Game game;

    /**
     * Referenzas aos catro cadrados que forman a peza
     */
    private Square a, b, c, d;

    /**
     * Obtiene el objeto del juego asociado con la pieza.
     *
     * @return El objeto de tipo 'Game' asociado a esta pieza.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Establece el objeto del juego asociado con la pieza.
     *
     * @param game El objeto de tipo 'Game' a asociar con esta pieza.
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Obtiene el cuadrado 'a' que forma parte de la pieza.
     *
     * @return El objeto de tipo 'Square' correspondiente al cuadrado 'a'.
     */
    public Square getA() {
        return a;
    }

    /**
     * Establece el cuadrado 'a' que forma parte de la pieza.
     *
     * @param a El objeto de tipo 'Square' a asignar al cuadrado 'a'.
     */
    public void setA(Square a) {
        this.a = a;
    }

    /**
     * Obtiene el cuadrado 'b' que forma parte de la pieza.
     *
     * @return El objeto de tipo 'Square' correspondiente al cuadrado 'b'.
     */
    public Square getB() {
        return b;
    }

    /**
     * Establece el cuadrado 'b' que forma parte de la pieza.
     *
     * @param b El objeto de tipo 'Square' a asignar al cuadrado 'b'.
     */
    public void setB(Square b) {
        this.b = b;
    }

    /**
     * Obtiene el cuadrado 'c' que forma parte de la pieza.
     *
     * @return El objeto de tipo 'Square' correspondiente al cuadrado 'c'.
     */
    public Square getC() {
        return c;
    }

    /**
     * Establece el cuadrado 'c' que forma parte de la pieza.
     *
     * @param c El objeto de tipo 'Square' a asignar al cuadrado 'c'.
     */
    public void setC(Square c) {
        this.c = c;
    }

    /**
     * Obtiene el cuadrado 'd' que forma parte de la pieza.
     *
     * @return El objeto de tipo 'Square' correspondiente al cuadrado 'd'.
     */
    public Square getD() {
        return d;
    }

    /**
     * Establece el cuadrado 'd' que forma parte de la pieza.
     *
     * @param d El objeto de tipo 'Square' a asignar al cuadrado 'd'.
     */
    public void setD(Square d) {
        this.d = d;
    }
    
    private void initializeSquares() {
        squares = new Square[4];
        squares[0] = a;
        squares[1] = b;
        squares[2] = c;
        squares[3] = d;
    }

    /**
     * Construtor da clase, que crea os catro cadrados que forman a peza
     */
    public Piece(Game game) {
        this.game = game;

        a = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, 0, Color.BLUE, game);
        b = new Square(Game.MAX_X / 2, 0, Color.BLUE, game);
        c = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE,
                Color.BLUE, game);
        d = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE, Color.BLUE, game);
        
        this.initializeSquares();
    }

    /**
     * Move a ficha á dereita se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean moveRight() {
        if (game.isValidPosition(a.getX() + Game.SQUARE_SIDE, a.getY()) &&
            game.isValidPosition(b.getX() + Game.SQUARE_SIDE, b.getY()) &&
            game.isValidPosition(c.getX() + Game.SQUARE_SIDE, c.getY()) &&
            game.isValidPosition(d.getX() + Game.SQUARE_SIDE, d.getY())) {
            
            a.setX(a.getX() + Game.SQUARE_SIDE);
            b.setX(b.getX() + Game.SQUARE_SIDE);
            c.setX(c.getX() + Game.SQUARE_SIDE);
            d.setX(d.getX() + Game.SQUARE_SIDE);
            return true;
        }
        return false;
    }

    /**
     * Move a ficha á esquerda se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean moveLeft() {
        if (game.isValidPosition(a.getX() - Game.SQUARE_SIDE, a.getY()) &&
            game.isValidPosition(b.getX() - Game.SQUARE_SIDE, b.getY()) &&
            game.isValidPosition(c.getX() - Game.SQUARE_SIDE, c.getY()) &&
            game.isValidPosition(d.getX() - Game.SQUARE_SIDE, d.getY())) {
            
            a.setX(a.getX() - Game.SQUARE_SIDE);
            b.setX(b.getX() - Game.SQUARE_SIDE);
            c.setX(c.getX() - Game.SQUARE_SIDE);
            d.setX(d.getX() - Game.SQUARE_SIDE);
            return true;
        }
        return false;
    }

    /**
     * Move a ficha a abaixo se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean moveDown() {
        if (game.isValidPosition(a.getX(), a.getY() + Game.SQUARE_SIDE) &&
            game.isValidPosition(b.getX(), b.getY() + Game.SQUARE_SIDE) &&
            game.isValidPosition(c.getX(), c.getY() + Game.SQUARE_SIDE) &&
            game.isValidPosition(d.getX(), d.getY() + Game.SQUARE_SIDE)) {
            
            a.setY(a.getY() + Game.SQUARE_SIDE);
            b.setY(b.getY() + Game.SQUARE_SIDE);
            c.setY(c.getY() + Game.SQUARE_SIDE);
            d.setY(d.getY() + Game.SQUARE_SIDE);
            return true;
        }
        return false;
    }

    /**
     * Rota a ficha se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean rotate() {
        // A rotación da ficha cadrada non supón ningunha variación na ficha,
        // por iso simplemente devolvemos true
        return true;
    }

}
