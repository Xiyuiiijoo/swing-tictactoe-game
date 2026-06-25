package org.example;

import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;

    public StatisticsFrame(Player player) {
        this.playerService = new PlayerService();
        // Refresh dari DB biar data fresh
        this.currentPlayer = playerService.getPlayerById(player.getId());

        setTitle("My Statistics");
        setSize(350, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // === Title ===
        JLabel lblTitle = new JLabel("MY STATISTICS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(lblTitle, BorderLayout.NORTH);

        // === Data panel ===
        JPanel dataPanel = new JPanel(new GridLayout(5, 2, 10, 5));
        dataPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        dataPanel.add(new JLabel("Username:"));
        dataPanel.add(new JLabel(currentPlayer.getUsername()));

        dataPanel.add(new JLabel("Wins:"));
        dataPanel.add(new JLabel(String.valueOf(currentPlayer.getWins())));

        dataPanel.add(new JLabel("Losses:"));
        dataPanel.add(new JLabel(String.valueOf(currentPlayer.getLosses())));

        dataPanel.add(new JLabel("Draws:"));
        dataPanel.add(new JLabel(String.valueOf(currentPlayer.getDraws())));

        dataPanel.add(new JLabel("Score:"));
        dataPanel.add(new JLabel(String.valueOf(currentPlayer.getScore())));

        add(dataPanel, BorderLayout.CENTER);

        // === Tombol Close ===
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnClose);
        add(btnPanel, BorderLayout.SOUTH);
    }
}