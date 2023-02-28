package com.codecool.fiveinarow;

import java.util.Arrays;

public class Util {
    final static int LETTER_a_IN_ASCII = 97;
    final static int LETTER_1_IN_ASCII = 49;

    public static boolean playerInputValid(String playerMove, int rowSize, int colSize) {
        char[] moveCoors = playerMove.toCharArray();

        boolean letterCheck = (moveCoors[0] >= 'a' && moveCoors[0] <= LETTER_a_IN_ASCII + rowSize);

        if (moveCoors.length == 2) {
            if (letterCheck) {
                return moveCoors[1] >= '1' && moveCoors[1] <= LETTER_1_IN_ASCII + colSize;
            }
        } else if (moveCoors.length == 3) {
            if (letterCheck) {
                if (moveCoors[1] >= '1' && moveCoors[1] <= '9') {
                    if (moveCoors[2] >= '0' && moveCoors[2] <= '9') {
                        int number = Integer.parseInt(playerMove.substring(1));
                        return number <= colSize;
                    }
                }
            }
        }
        return false;
    }


    public static int[] playerMoveToArray(String playerMove) {
        int[] playerMoveArray = new int[2];
        char[] moveCoors = playerMove.toCharArray();


        playerMoveArray[0] = moveCoors[0] - LETTER_a_IN_ASCII;
        if (playerMove.length() == 2) {
            playerMoveArray[1] = moveCoors[1] - LETTER_1_IN_ASCII;
        } else {
            playerMoveArray[1] = Integer.parseInt(playerMove.substring(1))-1;
        }
        return playerMoveArray;
    }

    public static void println(String msg) {
        System.out.println(msg);
    }


    public static void printTable(int[][] fields) {
        StringBuilder result = new StringBuilder();

        char[] abc = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        System.out.print("      ");
        for (int i = 0; i < fields.length; i++) {
            if(i<9){
                System.out.print(i + 1 + "     ");
            }else {
                System.out.print(i + 1 + "    ");
            }

        }
        System.out.println();
        result.append("   ").append("|-----".repeat(fields.length)).append("|").append("\n");

        for (int i = 0; i < fields.length; i++) {
            result.append(abc[i]).append("  |");

            for (int j = 0; j < fields[i].length; j++) {

                if (fields[i][j] == 0) {
                    result.append("  .  |");
                } else if (fields[i][j] == 1) {
                    result.append("  X  |");
                } else {
                    result.append("  O  |");
                }
            }
            result.append("\n");
            result.append("   ").append("|-----".repeat(fields.length)).append("|").append("\n");


        }
        Util.println(result.toString());
    }
}
