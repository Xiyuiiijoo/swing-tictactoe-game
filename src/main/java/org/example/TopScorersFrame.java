package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TopScorersFrame extends JFrame {
    private JTable table;
    private PlayerService playerService;

    public TopScorersFrame() {
        playerService = new PlayerService();

        setTitle("Top 5 Scorers");
        setSize(500, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title
        JLabel lblTitle = new JLabel("TOP 5 SCORERS", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        add(lblTitle, BorderLayout.NORTH);

        // Table
        String[] columns = {"Rank", "Username", "Wins", "Losses", "Draws", "Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        List<Player> topPlayers = playerService.getTopFiveScorers();
        int rank = 1;
        for (Player p : topPlayers) {
            model.addRow(new Object[]{
                    rank++,
                    p.getUsername(),
                    p.getWins(),
                    p.getLosses(),
                    p.getDraws(),
                    p.getScore()
            });
        }

        table = new JTable(model);
        table.setRowHeight(25);
        table.setEnabled(false); // read-only
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Tombol Close
        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(e -> dispose());
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnClose);
        add(btnPanel, BorderLayout.SOUTH);
    }
}