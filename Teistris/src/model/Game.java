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
package model;

import view.MainWindow;
import java.util.HashMap;
import java.util.Map;
import view.MainWindowA;

/**
 * Clase que implementa o comportamento do xogo do Tetris
 *
 * @author Profe de Programación
 */
public class Game {

    /**
     * Constante que define o tamaño en pixels do lado dun cadrado
     */
    public final static int SQUARE_SIDE = 20;
    /**
     * Constante que define o valor máximo da coordenada x no panel de cadrados
     */
    public final static int MAX_X = 400;
    /**
     * Constante que define o valor máximo da coordenada y no panel de cadrados
     */
    public final static int MAX_Y = 450;

    private Map<String, Square> groundSquares = new HashMap<>();

    /**
     * Referenza á peza actual do xogo, que é a única que se pode mover
     */
    private Piece currentPiece;

    /**
     * Referenza á ventá principal do xogo
     */
    private MainWindowA mainWindowa;

    /**
     * Flag que indica se o xogo está en pausa ou non
     */
    private boolean paused = false;

    /**
     * Número de liñas feitas no xogo
     */
    private int numberOfLines = 0;

    /**
     * @return Referenza á ventá principal do xogo
     */
    public MainWindowA getMainWindow() {
        return mainWindowa;
    }

    /**
     * @param mainWindow Ventá principal do xogo a establecer
     */
    public void setMainWindowA(MainWindowA mainWindow) {
        this.mainWindowa = mainWindow;
    }

    /**
     * @return O estado de pausa do xogo
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * @param paused O estado de pausa a establecer
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * @return Número de liñas feitas no xogo
     */
    public int getNumberOfLines() {
        return numberOfLines;
    }

    /**
     * @param numberOfLines O número de liñas feitas no xogo
     */
    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    /**
     * Construtor da clase, que crea unha primeira peza
     *
     * @param mainWindow Referenza á ventá principal do xogo
     */
    public Game(MainWindowA mainWindow) {
        this.mainWindowa = mainWindow;
        this.createNewPiece();
    }

    /**
     * Move a ficha actual á dereita, se o xogo non está pausado
     */
    public void movePieceRight() {
        if (!paused) {
            currentPiece.moveRight();
        }
    }

    /**
     * Move a ficha actual á esquerda, se o xogo non está pausado
     */
    public void movePieceLeft() {
        if (!paused) {
            currentPiece.moveLeft();
        }
    }

    /**
     * Rota a ficha actual, se o xogo non está pausado
     */
    public void rotatePiece() {
        if (!paused) {
            currentPiece.rotate();
        }
    }

    /**
     * Move a peza actual abaixo, se o xogo non está pausado Se a peza choca con
     * algo e xa non pode baixar, pasa a formar parte do chan e créase unha nova
     * peza
     */
    public void movePieceDown() {
        if ((!paused) && (!currentPiece.moveDown())) {
            this.addPieceToGround();
            this.createNewPiece();
            if (this.hitPieceTheGround()) {
                this.mainWindowa.showGameOver();
            }
        }
    }

    /**
     * Método que permite saber se unha posición x,y é válida para un cadrado
     *
     * @param x Coordenada x
     * @param y Coordenada y
     * @return true se esa posición é válida, se non false
     */
    public boolean isValidPosition(int x, int y) {
        if (x < 0 || x >= MAX_X || y < 0 || y >= MAX_Y) {
            return false;
        }
        return !groundSquares.containsKey(x + "," + y);
    }

    /**
     * Crea unha nova peza e a establece como peza actual do xogo
     */
    private void createNewPiece() {
        int pieceType = new java.util.Random().nextInt(5);
        
        if (pieceType == 0) {
            currentPiece = new SquarePiece(this);
        } 
        if(pieceType == 1){
            currentPiece = new LPiece(this);
        }
        if(pieceType == 2){
            currentPiece = new BarPiece(this);
        }
        if(pieceType == 3){
            currentPiece = new TPiece(this);
        }   
        if(pieceType == 4){
            currentPiece = new InvLPiece(this);
        } 
    }

    /**
     * Engade unha peza ao chan
     */
    private void addPieceToGround() {
        // Engadimos os cadrados da peza ao chan
        for (Square square : currentPiece.getSquares()) {
            groundSquares.put(square.getCoordinates(), square);
        }

        // Chamamos ao método que borra as liñas do chan que estean completas
        this.deleteCompletedLines();
    }

    /**
     * Se os cadrados que están forman unha liña completa, bórranse eses
     * cadrados do chan e súmase unha nova liña no número de liñas realizadas
     */
    private void deleteCompletedLines() {
        for (int i = 0; i < MAX_Y; i += SQUARE_SIDE) {
            boolean isComplete = true;
            for (int j = 0; j < MAX_X; j += SQUARE_SIDE) {
                if (!groundSquares.containsKey(j + "," + i)) {
                    isComplete = false;
                    break;
                }
            }
            if (isComplete) {
                deleteLine(i);
                numberOfLines++;
                mainWindowa.showNumberOfLines(numberOfLines);
            }
        }
    }

    /**
     * Borra todos os cadrados que se atopan na liña indicada pola coordenada y,
     * e baixa todos os cadrados que estean situados por enriba unha posición
     * cara abaixo
     *
     * @param y Coordenada y da liña a borrar
     */
    private void deleteLine(int y) {
        for (int i = 0; i < MAX_X; i += SQUARE_SIDE) {
            Square square = groundSquares.remove(i + "," + y);
            if (square != null) {
                mainWindowa.deleteSquare(square.getLblSquare());
            }
        }
        for (int ny = y - SQUARE_SIDE; ny >= 0; ny -= SQUARE_SIDE) {
            for (int j = 0; j < MAX_X; j += SQUARE_SIDE) {
                Square square = groundSquares.remove(j + "," + ny);
                if (square != null) {
                    square.setY(square.getY() + SQUARE_SIDE);
                    groundSquares.put(square.getCoordinates(), square);
                }
            }
        }
    }

    /**
     * Comproba se a peza actual choca cos cadrados do chan
     *
     * @return true se a peza actual choca cos cadrados do chan; se non false
     */
    private boolean hitPieceTheGround() {
        for (Square square : currentPiece.getSquares()) {
            if (groundSquares.containsKey(square.getCoordinates())) {
                return true;
            }
        }
        return false;
    }
}
