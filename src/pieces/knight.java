// chess_game/src/com/example/chess/pieces/Knight.java
package com.example.chess.pieces;

public class Knight extends Piece {
    public Knight(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public String toString() {
        return isWhite ? "N" : "n";
    }

    @Override
    public boolean canMove(int toRow, int toCol, Piece[][] board) {

        int dr = Math.abs(toRow - row);
        int dc = Math.abs(toCol - col);

        if ((dr == 2 && dc == 1) || (dr == 1 && dc == 2)) {
            Piece destinationPiece = board[toRow][toCol];
            // Cannot capture own piece
            if (destinationPiece != null && destinationPiece.isWhite == isWhite) {
                return false;
            }
            return true;
        }
        return false;
    }
}
