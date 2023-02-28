package com.codecool.fiveinarow;

import java.util.Arrays;
import java.util.Scanner;

public class Game implements GameInterface {

    private int[][] board;
    private int player = 1;

    public Game(int nRows, int nCols) {
        board = new int[nRows][nCols];
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public int[] getMove(int player) {
        int[] playerMoveArray = new int[2];

        Scanner scanner = new Scanner(System.in);
        Util.println("Current Player: "+player);
        Util.println("Please set a move: ");
        String playerMove = scanner.nextLine().toLowerCase();

        if (Util.playerInputValid(playerMove, board.length, board[0].length))   {
            playerMoveArray = Util.playerMoveToArray(playerMove);
            if (board[playerMoveArray[0]][playerMoveArray[1]] != 0){
                getMove(player);
            } else {
                return playerMoveArray;
            }
        } else {
            getMove(player);
        }
        return null;
    }

    public int[] getAiMove(int player) {
        return null;
    }

    public void mark(int player, int row, int col) {
        if (board[row][col] == 0) {
            board[row][col] = player;
        }

    }

    public boolean hasWon(int player, int howMany) {
        int playerMark = 0;

        return getPlayerMarkRow(player, playerMark, howMany) || getPlayerMarkCol(player, playerMark, howMany) || getPlayerMarkDiagonal(player, playerMark, howMany);
    }

    private boolean getPlayerMarkRow(int player, int playerMark, int howMany) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == player) {
                    playerMark++;
                    if (playerMark == howMany) {
                        return true;
                    }
                } else {
                    playerMark = 0;
                }

            }
        }
        return false;
    }

    private boolean getPlayerMarkCol(int player, int playerMark, int howMany) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[j][i] == player) {
                    playerMark++;
                    if (playerMark == howMany) {
                        return true;
                    }
                } else {
                    playerMark = 0;
                }

            }
        }
        return false;
    }

    private boolean getPlayerMarkDiagonal(int player, int playerMark, int howMany) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][i] == player) {
                playerMark++;
                if (playerMark == howMany) {
                    return true;
                }
            } else {
                playerMark = 0;
            }


        }

        for (int i = board.length - 1; i > 0; i--) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == player) {
                    playerMark++;
                    if (playerMark == howMany) {
                        return true;
                    }
                } else {
                    playerMark = 0;
                }
            }
        }

        return false;
    }


    public boolean isFull() {
        int empty = 0;
        for (int[] x : board) {
            for (int y : x) {
                if (y == 0) {
                    empty++;
                }
            }
        }
        return empty == 0;
    }

    public void printBoard() {
        Util.printTable(board);
    }

    public void printResult(int player) {
        switch (player){
            case 0 -> Util.println("It's a tie!");
            case 1 -> Util.println("X won!");
            case 2 -> Util.println("O won!");
        }

    }

    public void enableAi(int player) {
    }

    public void play(int howMany) {
        boolean stop = true;
        while (stop){
            printBoard();

            int[] move = getMove(player);
            System.out.println(Arrays.toString(move));
            mark(player, move[0], move[1]);
            if(hasWon(player,howMany)){
                printResult(player);
                stop = false;
            } else if(isFull()){
                player = 0;
                printResult(player);
                stop = false;
            }

            if(player == 1){
                player = 2;
            } else{
                player = 1;
            }
        }

    }
}
