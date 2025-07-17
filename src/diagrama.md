classDiagram
    class UI {
        -Board board
        +updateBoard()
        +handleClick()
        +highlightSelected()
        +addPiece()
        +removePiece()
    }

    class Board {
        -Piece[][] grid
        +setup()
        +print()
        +movePiece()
        +inBound()
        +isInCheck()
        +isCheckMate
    }

    class Piece {
        -boolean isWhite
        -int row
        -int col
        +setPosition()
        +canMove()
    }

    class Pawn
    class Rook
    class Knight
    class Bishop
    class Queen
    class King

    Piece <|-- Pawn
    Piece <|-- Rook
    Piece <|-- Knight
    Piece <|-- Bishop
    Piece <|-- Queen
    Piece <|-- King

    UI --> Board
    Board --> Piece
