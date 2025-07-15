import java.util.*;

public class Chess {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        boolean whiteTurn = true;

        while (true) {
            board.print();

            if (board.isInCheck(whiteTurn)) {
                if (board.isCheckmate(whiteTurn)) {
                    if (whiteTurn) {
                        System.out.println("Brancas sofrem xeque-mate.");
                    } else {
                        System.out.println("Negras sofrem xeque-mate.");
                    }
                    break;
                } else {
                    if (whiteTurn) {
                        System.out.println("Brancas estão em xeque.");
                    } else {
                        System.out.println("Negras estão em xeque.");
                    }
                }
            }

            if (whiteTurn) {
                System.out.println("Brancas jogam.");
            } else {
                System.out.println("Negras jogam.");
            }

            System.out.print("Digite o movimento (ex: e2 e4): ");
            String from = scanner.next();
            String to = scanner.next();

            int fromRow = 8 - Character.getNumericValue(from.charAt(1));
            int fromCol = from.charAt(0) - 'a';
            int toRow = 8 - Character.getNumericValue(to.charAt(1));
            int toCol = to.charAt(0) - 'a';

            boolean moveOk = board.movePiece(fromRow, fromCol, toRow, toCol, whiteTurn);

            if (moveOk == false) {
                System.out.println("Movimento inválido.");
            } else {
                whiteTurn = !whiteTurn;
            }
        }
    }
}
