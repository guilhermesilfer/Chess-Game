// chess_game/src/com/example/chess/pieces/Piece.java
package com.example.chess.pieces;

public abstract class Piece {
    public boolean isWhite;
    public int row;       
    public int col;       

    public Piece(boolean isWhite, int row, int col) {
        this.isWhite = isWhite;
        this.row = row;
        this.col = col;
    }

    public abstract boolean canMove(int toRow, int toCol, Piece[][] board);

    public void setPosition(int r, int c) { 
        this.row = r;
        this.col = c;
    }
}
