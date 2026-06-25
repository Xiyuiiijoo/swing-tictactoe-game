package org.example;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;

    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();

        // Window setup
        setTitle("Tic Tac Toe - Player: " + player.getUsername());
        setSize(400, 480);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Status label
        lblStatus = new JLabel("Giliranmu (X). Klik salah satu kotak!",
                SwingConstants.CENTER);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblStatus.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblStatus, BorderLayout.NORTH);

        // Board 3x3
        JPanel boardPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Segoe UI", Font.BOLD, 40));
            buttons[i].setFocusPainted(false);
            final int index = i;
            buttons[i].addActionListener(e -> handlePlayerMove(index));
            boardPanel.add(buttons[i]);
        }
        add(boardPanel, BorderLayout.CENTER);

        // Tombol Back
        JButton btnBack = new JButton("Back to Main Menu");
        btnBack.addActionListener(e -> {
            new MainMenuFrame(currentPlayer).setVisible(true);
            dispose();
        });
        add(btnBack, BorderLayout.SOUTH);
    }

    private void handlePlayerMove(int index) {
        // 1. Player jalan
        if (!gameLogic.makeMove(index, 'X')) {
            return; // cell sudah terisi, abaikan klik
        }
        buttons[index].setText("X");
        buttons[index].setEnabled(false);

        // 2. Cek menang
        if (gameLogic.checkWinner('X')) {
            finishGame("WIN");
            return;
        }

        // 3. Cek draw
        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }

        // 4. Computer jalan
        int compIdx = gameLogic.computerMove();
        if (compIdx != -1) {
            buttons[compIdx].setText("O");
            buttons[compIdx].setEnabled(false);
        }

        // 5. Cek computer menang
        if (gameLogic.checkWinner('O')) {
            finishGame("LOSE");
            return;
        }

        // 6. Cek draw setelah computer move
        if (gameLogic.isDraw()) {
            finishGame("DRAW");
            return;
        }
    }


    private void finishGame(String result) {
        // Disable semua tombol (akhir game)
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
        }


//  Update statistik setelah game selesai
        playerService.updateStatistics(currentPlayer, result);

        // Refresh data player dari database
        currentPlayer = playerService.getPlayerById(currentPlayer.getId());

        // Popup hasil
        JOptionPane optionPane = new JOptionPane(
                "Game result: " + result,
                JOptionPane.INFORMATION_MESSAGE
        );
        JDialog dialog = optionPane.createDialog(this, "Game Over");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);

        // Balik ke main menu
        new MainMenuFrame(currentPlayer).setVisible(true);
        dispose();

    }
}