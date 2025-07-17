// chess_game/src/com/example/chess/pieces/King.java
package com.example.chess.pieces;

public class King extends Piece {
    public King(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public String toString() {
        return isWhite ? "K" : "k";
    }

    @Override
    public boolean canMove(int toRow, int toCol, Piece[][] board) {
        int dr = Math.abs(toRow - row);
        int dc = Math.abs(toCol - col);

        if (dr <= 1 && dc <= 1) { 
            Piece destinationPiece = board[toRow][toCol];
            if (destinationPiece != null && destinationPiece.isWhite == isWhite) {
                return false;
            }
            return true;
        }
        return false;
    }
}
