package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
    private char[] board;

    public GameLogic() {
        board = new char[9];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
        }
    }

    public boolean makeMove(int index, char symbol) {
        if (index < 0 || index >= 9) {
            return false;
        }
        if (board[index] != ' ') {
            return false;
        }
        board[index] = symbol;
        return true;
    }

    public boolean checkWinner(char symbol) {
        int[][] patterns = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},   // baris
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},   // kolom
                {0, 4, 8}, {2, 4, 6}                // diagonal
        };
        for (int i = 0; i < patterns.length; i++) {
            int a = patterns[i][0];
            int b = patterns[i][1];
            int c = patterns[i][2];
            if (board[a] == symbol && board[b] == symbol && board[c] == symbol) {
                return true;
            }
        }
        return false;
    }

    public boolean isDraw() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public int computerMove() {
        // Cari cell kosong, pilih random salah satu
        List<Integer> emptyCells = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                emptyCells.add(i);
            }
        }
        if (emptyCells.isEmpty()) {
            return -1;
        }
        Random rand = new Random();
        int idx = emptyCells.get(rand.nextInt(emptyCells.size()));
        board[idx] = 'O';
        return idx;
    }

    public char[] getBoard() {
        return board;
    }
}