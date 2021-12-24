package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner SCANNER = new Scanner(System.in);
    static String PLAYER = "X";

    public static void main(String[] args) {
        final int boardSize = 9;
        String[][] board = new String[(int) Math.sqrt(boardSize)][(int) Math.sqrt(boardSize)];

        initBoard(board);
        printBoardTable(board);
        play(board);
        SCANNER.close();
    }

    public static void play(String[][] board) {
        while (!checkResult(board)) {
            updateBoard(board);
        }
    }

    public static void updateBoard(String[][] board) {
        final int ascii0 = 48;
        final int ascii9 = 57;
        boolean valid = false;
        do {
            char[] coordinates = getUserInput("Enter the coordinates: ").replace(" ", "").toCharArray();

            if (coordinates[0] < ascii0 || coordinates[0] > ascii9 || coordinates[1] < ascii0 || coordinates[1] > ascii9) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int row = Integer.parseInt(String.valueOf(coordinates[0])) - 1;
            int col = Integer.parseInt(String.valueOf(coordinates[1])) - 1;
            if (coordinates[0] == ascii0 || coordinates[1] == ascii0 || row >= board.length || col >= board.length) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            if (board[row][col].equals("O") || board[row][col].equals("X")) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            board[row][col] = PLAYER;
            switchPlayer();
            valid = true;
        } while (!valid);
        printBoardTable(board);
    }

    public static void switchPlayer() {
        PLAYER = PLAYER.equals("X") ? "O" : "X";
    }

    public static boolean checkResult(String[][] arr) {
        int xCount = 0;
        int oCount = 0;
        final String xWinCondition = "XXX";
        final String oWinCondition = "OOO";
        final StringBuilder row = new StringBuilder();
        final StringBuilder col = new StringBuilder();
        final StringBuilder diag = new StringBuilder();
        final StringBuilder diag2 = new StringBuilder();
        boolean xWin = false;
        boolean oWin = false;

        for (int i = 0; i < arr.length; i++) {
            row.setLength(0);
            col.setLength(0);
            diag.append(arr[i][i]);
            diag2.append(arr[i][arr.length - 1 - i]);
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j].equals("X")) {xCount++;}
                if (arr[i][j].equals("O")) {oCount++;}
                row.append(arr[i][j]);
                col.append(arr[j][i]);
            }
            if (!xWin) {
                xWin = col.toString().equals(xWinCondition) || row.toString().equals(xWinCondition) || diag.toString().equals(xWinCondition) || diag2.toString().equals(xWinCondition);
            }
            if (!oWin) {
                oWin = col.toString().equals(oWinCondition) || row.toString().equals(oWinCondition) || diag.toString().equals(oWinCondition) || diag2.toString().equals(oWinCondition);
            }
        }

        if (oWin && xWin || Math.abs(xCount - oCount) > 1) {System.out.println("Impossible");} else if (xWin) {
            System.out.println("X wins");
            return true;
        } else if (oWin) {
            System.out.println("O wins");
            return true;
        } else if (xCount + oCount == Math.pow(arr.length, 2)) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }


    /**
     * Use this method to print Game Board Table
     */
    private static void printBoardTable(String[][] board) {
        printHorizontalLine((int) Math.pow(board.length, 2));
        for (int i = 0; i < board.length; i++) {
            printRows(board, i);
        }
        printHorizontalLine((int) Math.pow(board.length, 2));
    }

    private static void printRows(String[][] board, int row) {
        System.out.print("| ");
        for (int i = 0; i < board[row].length; i++) {
            System.out.print(board[row][i] + " ");
        }
        System.out.println("|");
    }

    private static String getUserInput(String message) {
        System.out.print(message);
        return SCANNER.nextLine();
    }

    private static void initBoard(String[][] board) {
        for (int i = 0; i < board.length; i++) {
            initRow(board, i, "         ".toCharArray());
        }
    }

    // used in initBoard
    private static void initRow(String[][] board, int rowNum, char[] text) {
        for (int i = 0; i < board[rowNum].length; i++) {
            board[rowNum][i] = getSymbol(text, i);
        }
    }

    // used in initRow
    private static String getSymbol(char[] charArray, int index) {
        return String.valueOf(charArray[Index.INDEX++]);
    }

    // used in printBoardTable
    private static void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * this class used to get a static index variable to iterate over userInput char
     * array
     */
    static class Index {
        private static int INDEX = 0;
    }


}
