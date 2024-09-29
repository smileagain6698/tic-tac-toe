import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TicTacToe extends JFrame {

    private char currentPlayer = 'X';
    private JButton[][] buttons = new JButton[3][3];
    private JPanel panel = new JPanel();
    private JLabel statusLabel = new JLabel("Player X's turn");
    private JLabel scoreLabel = new JLabel("Score - X: 0, O: 0");
    private JButton resetButton = new JButton("Reset Game");
    private int scoreX = 0;
    private int scoreO = 0;

    public TicTacToe() {
        // Initialize the frame
        setTitle("Tic Tac Toe Challenge");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the panel and layout
        panel.setLayout(new GridLayout(3, 3));
        initializeBoard();

        // Add components to the frame
        add(panel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.NORTH);
        add(scoreLabel, BorderLayout.SOUTH);
        add(resetButton, BorderLayout.EAST);

        // Add action listener to the reset button
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard();
            }
        });

        setVisible(true);
    }

    // Initialize the game board with buttons
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                panel.add(buttons[i][j]);

                final int row = i;
                final int col = j;

                // Add action listener to handle button clicks
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttons[row][col].getText().equals("") && !isBoardFull()) {
                            buttons[row][col].setText(String.valueOf(currentPlayer));
                            if (isWinner()) {
                                statusLabel.setText("Player " + currentPlayer + " wins!");
                                updateScore();
                                disableButtons();
                            } else if (isBoardFull()) {
                                statusLabel.setText("The game is a draw!");
                            } else {
                                switchPlayer();
                                statusLabel.setText("Player " + currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }
    }

    // Switch the current player from X to O or O to X
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // Check if the current player has won
    private boolean isWinner() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check the rows for a win
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][1].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    // Check the columns for a win
    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[1][i].getText().equals(String.valueOf(currentPlayer)) &&
                    buttons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        return false;
    }

    // Check the diagonals for a win
    private boolean checkDiagonals() {
        return (buttons[0][0].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[2][2].getText().equals(String.valueOf(currentPlayer))) ||
                (buttons[0][2].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[1][1].getText().equals(String.valueOf(currentPlayer)) &&
                        buttons[2][0].getText().equals(String.valueOf(currentPlayer)));
    }

    // Check if the board is full (draw)
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Disable all buttons after a win or draw
    private void disableButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    // Reset the board to the initial state
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's turn");
    }

    // Update the score based on the winner
    private void updateScore() {
        if (currentPlayer == 'X') {
            scoreX++;
        } else {
            scoreO++;
        }
        scoreLabel.setText("Score - X: " + scoreX + ", O: " + scoreO);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
