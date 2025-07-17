// chess_game/src/com/example/chess/pieces/Queen.java
package com.example.chess.pieces;

public class Queen extends Piece {
    public Queen(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public String toString() {
        return isWhite ? "Q" : "q";
    }

    @Override
    public boolean canMove(int toRow, int toCol, Piece[][] board) {
        Rook tempRook = new Rook(isWhite, this.row, this.col);
        Bishop tempBishop = new Bishop(isWhite, this.row, this.col);

        if (tempRook.canMove(toRow, toCol, board)) {
            return true;
        }
        if (tempBishop.canMove(toRow, toCol, board)) {
            return true;
        }
        return false;
    }
}
