// chess_game/src/com/example/chess/Main.java
package com.example.chess;

import java.util.Scanner;

public class Main { 
    public static void main(String[] args) {
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);
        boolean whiteTurn = true;

        System.out.println("Bem-vindo ao Chess em Console!");

        while (true) {
            board.print();

            if (board.isInCheck(whiteTurn)) {
                if (board.isCheckmate(whiteTurn)) {
                    if (whiteTurn) {
                        System.out.println("Brancas sofrem xeque-mate. Negras Vencem!");
                    } else {
                        System.out.println("Negras sofrem xeque-mate. Brancas Vencem!");
                    }
                    break;
                } else {
                    if (whiteTurn) {
                        System.out.println("Brancas estão em xeque!");
                    } else {
                        System.out.println("Negras estão em xeque!");
                    }
                }
            }

            if (whiteTurn) {
                System.out.println("Vez das Brancas.");
            } else {
                System.out.println("Vez das Negras.");
            }

            System.out.print("Digite o movimento (ex: e2 e4, ou 'sair' para encerrar): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("sair")) {
                System.out.println("Jogo encerrado.");
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Formato de movimento inválido. Use 'e2 e4'.");
                continue;
            }

            String from = parts[0];
            String to = parts[1];

            int fromRow = 8 - Character.getNumericValue(from.charAt(1));
            int fromCol = from.charAt(0) - 'a';
            int toRow = 8 - Character.getNumericValue(to.charAt(1));
            int toCol = to.charAt(0) - 'a';

            boolean moveOk = board.movePiece(fromRow, fromCol, toRow, toCol, whiteTurn);

            if (!moveOk) {
                System.out.println("Movimento inválido. Tente novamente.");
            } else {
                whiteTurn = !whiteTurn; 
            }
        }
        scanner.close();
    }
}
