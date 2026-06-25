package org.example;

import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;

    public MainMenuFrame(Player player) {
        this.currentPlayer = player;

        // Window setup
        setTitle("Main Menu - Tic Tac Toe");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel lblWelcome = new JLabel(
                "Welcome, " + player.getUsername() + "!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(lblWelcome, BorderLayout.NORTH);

        // Tombol-tombol
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        btnStartGame  = new JButton("Start Game");
        btnStatistics = new JButton("My Statistics");
        btnTopScorers = new JButton("Top 5 Scorers");
        btnExit       = new JButton("Exit");

        panel.add(btnStartGame);
        panel.add(btnStatistics);
        panel.add(btnTopScorers);
        panel.add(btnExit);
        add(panel, BorderLayout.CENTER);

        // Event handlers 
        btnStartGame.addActionListener(e -> {
            new GameFrame(currentPlayer).setVisible(true);
            dispose();
        });

        btnStatistics.addActionListener(e -> {
            new StatisticsFrame(currentPlayer).setVisible(true);
        });

        btnTopScorers.addActionListener(e -> {
            new TopScorersFrame().setVisible(true);
        });

        btnExit.addActionListener(e -> System.exit(0));
    }
}