// chess_game/src/com/example/chess/pieces/Pawn.java
package com.example.chess.pieces;

public class Pawn extends Piece {
    public Pawn(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    @Override
    public String toString() {
        return isWhite ? "P" : "p";
    }

    @Override
    public boolean canMove(int toRow, int toCol, Piece[][] board) {
        int direction = isWhite ? -1 : 1;

        if (col == toCol) { 
            if (board[toRow][toCol] == null) { 
                if (toRow - row == direction) {
                    return true;
                }
                if (isWhite && row == 6 && toRow == row + (2 * direction) && board[row + direction][col] == null) {
                    return true;
                }
                if (!isWhite && row == 1 && toRow == row + (2 * direction) && board[row + direction][col] == null) {
                    return true;
                }
            }
        }
        else if (Math.abs(toCol - col) == 1 && toRow - row == direction) {
            Piece targetPiece = board[toRow][toCol];
            if (targetPiece != null && targetPiece.isWhite != isWhite) {
                return true; 
            }
        }
        return false;
    }
}
