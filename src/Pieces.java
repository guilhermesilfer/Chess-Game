abstract class Piece {
    boolean isWhite;
    int row;
    int col;

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
    Knight(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    public String toString() {
        if (isWhite) {
            return "N";
        } else {
            return "n";
        }
    }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        int dr = Math.abs(toRow - row);
        int dc = Math.abs(toCol - col);

        if ((dr == 2 && dc == 1) || (dr == 1 && dc == 2)) {
            Piece destino = board[toRow][toCol];
            if (destino == null) {
                return true;
            } else {
                if (destino.isWhite != isWhite) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Rook extends Piece {
    Rook(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    public String toString() {
        if (isWhite) {
            return "R";
        } else {
            return "r";
        }
    }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
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

        Piece destino = board[toRow][toCol];
        if (destino == null) {
            return true;
        } else {
            if (destino.isWhite != isWhite) {
                return true;
            }
        }
        return false;
    }
}

class Bishop extends Piece {
    Bishop(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    public String toString() {
        if (isWhite) {
            return "B";
        } else {
            return "b";
        }
    }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        int drAbs = Math.abs(toRow - row);
        int dcAbs = Math.abs(toCol - col);

        if (drAbs != dcAbs) {
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

        Piece destino = board[toRow][toCol];
        if (destino == null) {
            return true;
        } else {
            if (destino.isWhite != isWhite) {
                return true;
            }
        }
        return false;
    }
}

class Queen extends Piece {
    Queen(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    public String toString() {
        if (isWhite) {
            return "Q";
        } else {
            return "q";
        }
    }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        Rook torre = new Rook(isWhite, row, col);
        Bishop bispo = new Bishop(isWhite, row, col);

        if (torre.canMove(toRow, toCol, board)) {
            return true;
        }

        if (bispo.canMove(toRow, toCol, board)) {
            return true;
        }
        return false;
    }
}

class King extends Piece {
    King(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    public String toString() {
        if (isWhite) {
            return "K";
        } else {
            return "k";
        }
    }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        int dr = Math.abs(toRow - row);
        int dc = Math.abs(toCol - col);

        if (dr <= 1 && dc <= 1) {
            Piece destino = board[toRow][toCol];
            if (destino == null) {
                return true;
            } else {
                if (destino.isWhite != isWhite) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Pawn extends Piece {
    Pawn(boolean isWhite, int row, int col) {
        super(isWhite, row, col);
    }

    public String toString() {
        if (isWhite) {
            return "P";
        } else {
            return "p";
        }
    }

    boolean canMove(int toRow, int toCol, Piece[][] board) {
        int direcao;
        if (isWhite) {
            direcao = -1;
        } else {
            direcao = 1;
        }

        if (col == toCol) {
            if (board[toRow][toCol] == null) {
                if (toRow - row == direcao) {
                    return true;
                }

                if (isWhite == true && row == 6 && toRow - row == 2 * direcao && board[row + direcao][col] == null) {
                    return true;
                }

                if (isWhite == false && row == 1 && toRow - row == 2 * direcao && board[row + direcao][col] == null) {
                    return true;
                }
            }
        } else {
            if (Math.abs(toCol - col) == 1 && toRow - row == direcao) {
                Piece alvo = board[toRow][toCol];
                if (alvo != null) {
                    if (alvo.isWhite != isWhite) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
