import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UI extends JFrame {
    private Board board;
    private JButton[][] buttons;
    private int selectedRow = -1, selectedCol = -1;
    private boolean whiteTurn = true;
    private JLabel statusLabel;

    public UI() {
        board = new Board();
        buttons = new JButton[8][8];
        setTitle("Xadrez em Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 740);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Editar Tabuleiro");
        JMenuItem addItem = new JMenuItem("Adicionar peça");
        JMenuItem removeItem = new JMenuItem("Remover peça");

        addItem.addActionListener(e -> addPiece());
        removeItem.addActionListener(e -> removePiece());

        menu.add(addItem);
        menu.add(removeItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        JPanel boardPanel = new JPanel(new GridLayout(9, 9));
        Font font = new Font("SansSerif", Font.BOLD, 20);

        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                if (row == 0 && col == 0) {
                    boardPanel.add(new JLabel());
                } else if (row == 0) {
                    JLabel label = new JLabel("" + (char) ('a' + col - 1), SwingConstants.CENTER);
                    label.setFont(font);
                    boardPanel.add(label);
                } else if (col == 0) {
                    JLabel label = new JLabel("" + (9 - row), SwingConstants.CENTER);
                    label.setFont(font);
                    boardPanel.add(label);
                } else {
                    JButton button = new JButton();
                    button.setFont(font);
                    button.setFocusPainted(false);
                    button.setOpaque(true);
                    buttons[row - 1][col - 1] = button;
                    int r = row - 1, c = col - 1;
                    button.addActionListener(e -> handleClick(r, c));
                    boardPanel.add(button);
                }
            }
        }

        statusLabel = new JLabel("Brancas jogam.", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        add(statusLabel, BorderLayout.SOUTH);
        add(boardPanel, BorderLayout.CENTER);

        updateBoard();
        setVisible(true);
    }

    private void handleClick(int row, int col) {
        if (selectedRow == -1) {
            Piece p = board.grid[row][col];
            if (p != null && p.isWhite == whiteTurn) {
                selectedRow = row;
                selectedCol = col;
                highlightSelected(row, col);
                statusLabel.setText((whiteTurn ? "Brancas" : "Negras") + " selecionaram " + p);
            }
        } else {
            if (board.movePiece(selectedRow, selectedCol, row, col, whiteTurn)) {
                if (board.isInCheck(!whiteTurn)) {
                    if (board.isCheckmate(!whiteTurn)) {
                        updateBoard();
                        JOptionPane.showMessageDialog(this, (!whiteTurn ? "Brancas" : "Negras") + " sofrem xeque-mate.");
                        System.exit(0);
                    } else {
                        statusLabel.setText((!whiteTurn ? "Brancas" : "Negras") + " estão em xeque.");
                    }
                } else {
                    statusLabel.setText((!whiteTurn ? "Brancas" : "Negras") + " jogam.");
                }
                whiteTurn = !whiteTurn;
            } else {
                statusLabel.setText("Movimento inválido.");
            }
            selectedRow = selectedCol = -1;
            updateBoard();
        }
    }

    private void updateBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton button = buttons[i][j];
                Piece p = board.grid[i][j];
                button.setText("");
                button.setIcon(getIcon(p));
                Color base = (i + j) % 2 == 0 ? new Color(70, 70, 70) : new Color(200, 200, 200);
                button.setBackground(base);
            }
        }
    }

    private void highlightSelected(int row, int col) {
        updateBoard();
        buttons[row][col].setBackground(Color.YELLOW);
    }

    private Icon getIcon(Piece piece) {
        if (piece == null) return null;

        String name = "";
        if (piece instanceof King) name = "king";
        else if (piece instanceof Queen) name = "queen";
        else if (piece instanceof Rook) name = "rook";
        else if (piece instanceof Bishop) name = "bishop";
        else if (piece instanceof Knight) name = "knight";
        else if (piece instanceof Pawn) name = "pawn";

        String prefix = piece.isWhite ? "w" : "b";
        String path = "/img/" + prefix + "_" + name + ".png";

        File imgFile = new File(path);
        if (!imgFile.exists()) {
            System.out.println("imagem nao encontrada: " + path);
        }

        java.net.URL imgURL = getClass().getResource(path);

        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image image = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } else {
            System.err.println("Imagem não encontrada: " + path);
            return null;
        }
    }
    private void addPiece() {
        JTextField rowField = new JTextField(2);
        JTextField colField = new JTextField(2);
        String[] pieces = {"Pawn", "Rook", "Knight", "Bishop", "Queen", "King"};
        JComboBox<String> pieceBox = new JComboBox<>(pieces);
        JCheckBox isWhiteBox = new JCheckBox("Peça Branca");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(new JLabel("Linha (0-7):"));
        panel.add(rowField);
        panel.add(new JLabel("Coluna (0-7):"));
        panel.add(colField);
        panel.add(new JLabel("Tipo de peça:"));
        panel.add(pieceBox);
        panel.add(isWhiteBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Adicionar peça", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int row = Integer.parseInt(rowField.getText());
                int col = Integer.parseInt(colField.getText());
                boolean isWhite = isWhiteBox.isSelected();
                Piece piece = switch (pieceBox.getSelectedItem().toString()) {
                    case "Pawn" -> new Pawn(isWhite, row, col);
                    case "Rook" -> new Rook(isWhite, row, col);
                    case "Knight" -> new Knight(isWhite, row, col);
                    case "Bishop" -> new Bishop(isWhite, row, col);
                    case "Queen" -> new Queen(isWhite, row, col);
                    case "King" -> new King(isWhite, row, col);
                    default -> null;
                };
                board.grid[row][col] = piece;
                updateBoard();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Entrada inválida.");
            }
        }
    }

    private void removePiece() {
        JTextField rowField = new JTextField(2);
        JTextField colField = new JTextField(2);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(new JLabel("Linha (0-7):"));
        panel.add(rowField);
        panel.add(new JLabel("Coluna (0-7):"));
        panel.add(colField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Remover peça", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int row = Integer.parseInt(rowField.getText());
                int col = Integer.parseInt(colField.getText());
                board.grid[row][col] = null;
                updateBoard();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Entrada inválida.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UI::new);
    }
}
