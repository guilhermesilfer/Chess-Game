import java.util.*;

public class Chess {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        boolean whiteTurn = true;

        while (true) {
            board.print();
            System.out.println((whiteTurn ? "Brancas" : "Negras") + " jogam.");
            System.out.print("Digite o movimento (ex: e2 e4): ");
            String from = scanner.next();
            String to = scanner.next();

            int fromRow = 8 - Character.getNumericValue(from.charAt(1));
            int fromCol = from.charAt(0) - 'a';
            int toRow = 8 - Character.getNumericValue(to.charAt(1));
            int toCol = to.charAt(0) - 'a';

            if (!board.movePiece(fromRow, fromCol, toRow, toCol, whiteTurn)) {
                System.out.println("Movimento inválido.");
            } else {
                whiteTurn = !whiteTurn;
            }
        }
    }
}