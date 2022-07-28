package tictactoe;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String[][] table = grid();
    public static void main(String[] args) {
        createTable(table);
        boolean isGameOver = false;
        int turn = 1;
        while (!isGameOver) {
            System.out.print("Enter the coordinates: ");
            correctFormat();
            boolean withinRange = inRange();
            if (!withinRange) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            boolean isOccupied = occupied();
            if (!isOccupied) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            update(turn);
            createTable(table);
            isGameOver = analyze(table);
            turn++;
        }
        if (winner.equals("")) {
            System.out.println("Draw");
        } else {
            System.out.println(winner + " wins");
        }
    }
    static int c1;
    static int c2;
    static String stringC1 = "";
    static String stringC2 = "";

   public static void correctFormat() {
            do {
                stringC1 = scanner.next();
                stringC2 = scanner.nextLine();
                stringC2 = stringC2.replaceAll(" ", "");
                try {
                    c1 = Integer.parseInt(stringC1);
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    System.out.print("Enter coordinates: ");
                    continue;
                }

                try {
                    c2 = Integer.parseInt(stringC2);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    System.out.print("Enter coordinates: ");
                }
            } while (true);
        }
        public static boolean inRange() {
            boolean withinRange1 = c1 <= 3 && c1 >= 1;
            boolean withinRange2 = c2 <= 3 && c2 >= 1;
            return withinRange1 && withinRange2;
        }
        public static boolean occupied() {
            return table[c1 - 1][c2 - 1].equals("_");
        }
    public static void update(int x) {
        if (x % 2 != 0) {
            table[c1 - 1][c2 - 1] = "X";
        } else {
            table[c1 - 1][c2 - 1] = "O";
        }
    }

    public static void createTable(String[][] table) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    static String winner = "";
    public static boolean analyze(String[][] table) {
        int counterX = 0;
        int counterO = 0;
        boolean isWinner = false;
        boolean xWin = false;
        boolean oWin = false;

        for (String[] strings : table) {
            for (String string : strings) {
                if (string.equals("X")) {
                    counterX++;
                } else if (string.equals("O")) {
                    counterO++;
                }
            }
        }


        boolean verticalWin = false;
        boolean horizontalWin = false;
        boolean diagonalWin = false;

        for (String[] strings : table) {
            if (horizontal(strings)) {
                winner = strings[0];
                isWinner = true;
                horizontalWin = true;
                if (winner.equals("X")) {
                    xWin = true;
                } else {
                    oWin = true;
                }
            }
        }

        for (int i = 0; i < table.length; i++) {
            if (vertical(table, i)) {
                winner = table[0][i];
                isWinner = true;
                verticalWin = true;
                if (winner.equals("X")) {
                    xWin = true;
                } else {
                    oWin = true;
                }
            }
        }

        if (firstDiagonal(table)) {
            winner = table[0][0];
            isWinner = true;
            diagonalWin = true;
            if (winner.equals("X")) {
                xWin = true;
            } else {
                oWin = true;
            }
        }
        if (secondDiagonal(table)) {
            winner = table[0][table[0].length - 1];
            isWinner = true;
            diagonalWin = true;
            if (winner.equals("X")) {
                xWin = true;
            } else {
                oWin = true;
            }
        }

        return counterO + counterX == 9 || verticalWin || horizontalWin || diagonalWin;
    }

    public static String[][] grid() {
        String[][] matrix = new String[3][3];
        for (String[] strings : matrix) {
            Arrays.fill(strings, "_");
        }
        return matrix;
    }

    public static boolean horizontal(String[] array) {
        String template = array[0];
        boolean inARow = false;
        if (template.equals("X") || template.equals("O")) {
            int counter = 0;
            for (String s : array) {
                if (s.equals(template)) {
                    counter++;
                }
                if (counter == array.length) {
                    inARow = true;
                    break;
                }
            }
        }
        return inARow;
    }

    public static boolean vertical(String[][] array, int index) {
        int counter = 0;
        boolean inAColumn = false;
        int i = 0;
        String template = array[i][index];
        if (template.equals("X") || template.equals("O")) {
            for (; i < array.length; i++) {
                if (array[i][index].equals(template)) {
                    counter++;
                }
                if (counter == array.length) {
                    inAColumn = true;
                    break;
                }
            }
        }
        return inAColumn;
    }

    public static boolean firstDiagonal(String[][] array) {
        boolean diagonalRow = false;
        String template = array[0][0];
        if (template.equals("X") || template.equals("O")) {
            int counter = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i][i].equals(template)) {
                    counter++;
                }
                if (counter == array.length) {
                    diagonalRow = true;
                    break;
                }
            }
        }
        return diagonalRow;
    }

    public static boolean secondDiagonal(String[][] array) {
        boolean diagonalRow = false;
        int index = array[0].length - 1;
        String template = array[0][index];
        if (template.equals("X") || template.equals("O")) {
            int counter = 0;
            String toCheck = array[0][index];
            for (String[] strings : array) {
                if (strings[index].equals(toCheck)) {
                    counter++;
                }
                if (counter == array.length) {
                    diagonalRow = true;
                    break;
                }
                index--;
            }
        }
        return diagonalRow;
    }


}
