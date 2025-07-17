// chess_game/src/com/example/chess/pieces/Bishop.java
package com.example.chess.pieces;

public class Bishop extends Piece {
    public Bishop(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public String toString() {
        return isWhite ? "B" : "b";
    }

    @Override
    public boolean canMove(int toRow, int toCol, Piece[][] board) {
        int drAbs = Math.abs(toRow - row);
        int dcAbs = Math.abs(toCol - col);

        if (drAbs != dcAbs) {
            return false;
        }

        int dr = Integer.compare(toRow, row);
        int dc = Integer.compare(toCol, col); 

        int r = row + dr;
        int c = col + dc;
        while (r != toRow && c != toCol) {
            if (board[r][c] != null) {
                return false;
            }
            r += dr;
            c += dc;
        }
        Piece destinationPiece = board[toRow][toCol];
        if (destinationPiece != null && destinationPiece.isWhite == isWhite) {
            return false;
        }

        return true;
    }
}
