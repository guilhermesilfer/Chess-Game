// chess_game/src/com/example/chess/pieces/Rook.java
package com.example.chess.pieces;

public class Rook extends Piece {
    public Rook(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public String toString() {
        return isWhite ? "R" : "r";
    }

    @Override
    public boolean canMove(int toRow, int toCol, Piece[][] board) {
        if (row != toRow && col != toCol) {
            return false;
        }

        int dr = Integer.compare(toRow, row); 
        int dc = Integer.compare(toCol, col);

        int r = row + dr;
        int c = col + dc;
        while (r != toRow || c != toCol) {
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
