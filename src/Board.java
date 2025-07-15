class Board {
    Piece[][] grid = new Piece[8][8];

    public Board() {
        setup();
    }

    void setup() {
        grid[0][0] = new Rook(false, 0, 0);
        grid[0][1] = new Knight(false, 0, 1);
        grid[0][2] = new Bishop(false, 0, 2);
        grid[0][3] = new Queen(false, 0, 3);
        grid[0][4] = new King(false, 0, 4);
        grid[0][5] = new Bishop(false, 0, 5);
        grid[0][6] = new Knight(false, 0, 6);
        grid[0][7] = new Rook(false, 0, 7);

        for (int i = 0; i < 8; i++) {
            grid[1][i] = new Pawn(false, 1, i);
        }

        grid[7][0] = new Rook(true, 7, 0);
        grid[7][1] = new Knight(true, 7, 1);
        grid[7][2] = new Bishop(true, 7, 2);
        grid[7][3] = new Queen(true, 7, 3);
        grid[7][4] = new King(true, 7, 4);
        grid[7][5] = new Bishop(true, 7, 5);
        grid[7][6] = new Knight(true, 7, 6);
        grid[7][7] = new Rook(true, 7, 7);

        for (int i = 0; i < 8; i++) {
            grid[6][i] = new Pawn(true, 6, i);
        }
    }

    void print() {
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i + " ");
            for (int j = 0; j < 8; j++) {
                if (grid[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println(8 - i);
        }
        System.out.println("  a b c d e f g h");
        System.out.println();
    }

    boolean movePiece(int fromRow, int fromCol, int toRow, int toCol, boolean whiteTurn) {
        if (inBounds(fromRow, fromCol) == false || inBounds(toRow, toCol) == false) {
            return false;
        }

        Piece piece = grid[fromRow][fromCol];

        if (piece == null) {
            return false;
        }

        if (piece.isWhite != whiteTurn) {
            return false;
        }

        boolean podeMover = piece.canMove(toRow, toCol, grid);

        if (podeMover == false) {
            return false;
        }

        Piece capturada = grid[toRow][toCol];

        grid[toRow][toCol] = piece;
        grid[fromRow][fromCol] = null;

        int antigaLinha = piece.row;
        int antigaColuna = piece.col;

        piece.setPosition(toRow, toCol);

        if (isInCheck(whiteTurn)) {
            grid[fromRow][fromCol] = piece;
            grid[toRow][toCol] = capturada;
            piece.setPosition(antigaLinha, antigaColuna);
            return false;
        }
        return true;
    }

    boolean inBounds(int row, int col) {
        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            return true;
        } else {
            return false;
        }
    }

    boolean isInCheck(boolean white) {
        int kingRow = -1;
        int kingCol = -1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = grid[i][j];
                if (p instanceof King) {
                    if (p.isWhite == white) {
                        kingRow = i;
                        kingCol = j;
                    }
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = grid[i][j];
                if (p != null) {
                    if (p.isWhite != white) {
                        if (p.canMove(kingRow, kingCol, grid)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean isCheckmate(boolean white) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = grid[i][j];

                if (p != null) {
                    if (p.isWhite == white) {

                        for (int r = 0; r < 8; r++) {
                            for (int c = 0; c < 8; c++) {
                                if (p.canMove(r, c, grid)) {
                                    Piece capturada = grid[r][c];
                                    int linhaAntiga = p.row;
                                    int colunaAntiga = p.col;

                                    grid[r][c] = p;
                                    grid[i][j] = null;

                                    p.setPosition(r, c);

                                    boolean aindaEmXeque = isInCheck(white);

                                    grid[i][j] = p;
                                    grid[r][c] = capturada;

                                    p.setPosition(linhaAntiga, colunaAntiga);

                                    if (aindaEmXeque == false) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}