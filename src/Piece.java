abstract class Piece {
    boolean isWhite;
    int row, col;

    Piece(boolean isWhite, int row, int col) {
        this.isWhite = isWhite;
        this.row = row;
        this.col = col;
    }

    abstract boolean canMove(int toRow, int toCol, Piece[][] board);

    void setPosition(int r, int c) {
        this.row = r;
        this.col = c;
    }
}

class Knight extends Piece {
    Knight(boolean isWhite, int row, int col) { super(isWhite, row, col); }
    public String toString() { return isWhite ? "N" : "n"; }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        int dr = Math.abs(toRow - row), dc = Math.abs(toCol - col);
        if ((dr == 2 && dc == 1) || (dr == 1 && dc == 2)) {
            return board[toRow][toCol] == null || board[toRow][toCol].isWhite != isWhite;
        }
        return false;
    }
}

class Rook extends Piece {
    Rook(boolean isWhite, int row, int col) { super(isWhite, row, col); }
    public String toString() { return isWhite ? "R" : "r"; }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        if (row != toRow && col != toCol) return false;
        int dr = Integer.compare(toRow, row), dc = Integer.compare(toCol, col);
        int r = row + dr, c = col + dc;
        while (r != toRow || c != toCol) {
            if (board[r][c] != null) return false;
            r += dr; c += dc;
        }
        return board[toRow][toCol] == null || board[toRow][toCol].isWhite != isWhite;
    }
}

class Bishop extends Piece {
    Bishop(boolean isWhite, int row, int col) { super(isWhite, row, col); }
    public String toString() { return isWhite ? "B" : "b"; }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        if (Math.abs(toRow - row) != Math.abs(toCol - col)) return false;
        int dr = Integer.compare(toRow, row), dc = Integer.compare(toCol, col);
        int r = row + dr, c = col + dc;
        while (r != toRow || c != toCol) {
            if (board[r][c] != null) return false;
            r += dr; c += dc;
        }
        return board[toRow][toCol] == null || board[toRow][toCol].isWhite != isWhite;
    }
}

class Queen extends Piece {
    Queen(boolean isWhite, int row, int col) { super(isWhite, row, col); }
    public String toString() { return isWhite ? "Q" : "q"; }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        return new Rook(isWhite, row, col).canMove(toRow, toCol, board) ||
                new Bishop(isWhite, row, col).canMove(toRow, toCol, board);
    }
}

class King extends Piece {
    King(boolean isWhite, int row, int col) { super(isWhite, row, col); }
    public String toString() { return isWhite ? "K" : "k"; }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        int dr = Math.abs(toRow - row), dc = Math.abs(toCol - col);
        return dr <= 1 && dc <= 1 && (board[toRow][toCol] == null || board[toRow][toCol].isWhite != isWhite);
    }
}

class Pawn extends Piece {
    Pawn(boolean isWhite, int row, int col) { super(isWhite, row, col); }
    public String toString() { return isWhite ? "P" : "p"; }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        int dir = isWhite ? -1 : 1;
        if (col == toCol && board[toRow][toCol] == null) {
            if (toRow - row == dir) return true;
            if ((isWhite && row == 6 || !isWhite && row == 1) && toRow - row == 2 * dir && board[row + dir][col] == null)
                return true;
        } else if (Math.abs(toCol - col) == 1 && toRow - row == dir && board[toRow][toCol] != null && board[toRow][toCol].isWhite != isWhite) {
            return true;
        }
        return false;
    }
}