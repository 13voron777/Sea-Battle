import java.io.IOException;
import java.util.Scanner;

public class Battleship {
    static Scanner scanner = new Scanner(System.in);
    static String player1 = "Player 1";
    static String player2 = "Player 2";
    static String[][] field1 = new String[10][10];
    static String[][] field2 = new String[10][10];
    static String[][] enemyField1 = new String[10][10];
    static String[][] enemyField2 = new String[10][10];
    static int isAlive1 = 20;
    static int isAlive2 = 20;
    static boolean endGame = false;

    static {
        for (int i = 0; i < field1.length; i++) {
            for (int j = 0; j < field1[i].length; j++) {
                field1[i][j] = " ";
                field2[i][j] = " ";
                enemyField1[i][j] = " ";
                enemyField2[i][j] = " ";
            }
        }
    }

    public static void main(String[] args) {
        clearScreen();
        createShips(player1, field1, 1);
        createShips(player2, field2, 2);
        while (true) {
            makeShoot(player1, enemyField1, field2, 2);
            winnerDefine();
            if (endGame) {
                break;
            }
            makeShoot(player2, enemyField2, field1, 1);
            winnerDefine();
            if (endGame) {
                break;
            }
        }
    }

    public static void createField(String[][] field) {
        System.out.println("-----------------------");
        System.out.println("| |0|1|2|3|4|5|6|7|8|9|");
        for (int i = 0; i < 10; i++) {
            System.out.print("|" + i + "|");
            for (int j = 0; j < 10; j++) {
                System.out.print(field[j][i] + "|");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    public static void createShips(String player, String[][] field, int num) {
        System.out.print(player + ", please enter your name: ");
        if (num == 1) {
            player1 = scanner.next();
        } else {
            player2 = scanner.next();
        }
        clearScreen();
        createField(field);
        System.out.println(player + ", please, create your ships on the battlefield");
        int deck = 4;
        int count = 1;
        while (deck != 0) {
            for (int i = 0; i < count; i++) {
                System.out.println("Creating " + deck + "-deck ship: ");
                System.out.print("Enter x and y coordinates: ");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (deck != 1) {
                    System.out.println("Please, choose direction of the ship.");
                    System.out.print("Horisontal (h) or Vertical (v): ");
                    String dir = scanner.next();
                    if (dir.equals("h")) {
                        for (int j = 0; j < deck; j++) {
                            field[x + j][y] = "0";
                        }
                    } else {
                        for (int j = 0; j < deck; j++) {
                            field[x][y + j] = "0";
                        }
                    }
                } else {
                    field[x][y] = "0";
                }
                clearScreen();
                createField(field);
            }
            count++;
            deck--;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clearScreen();
    }

    public static void makeShoot(String player, String[][] enemyField, String[][] field, int isAlive) {
        createField(enemyField);
        System.out.println(player + ", please, make a shoot.");
        System.out.print("Enter x and y enemy coordinates: ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        if (field[x][y].equals("0")) {
            enemyField[x][y] = "X";
            System.out.println("Hit! Your turn again");
            makeShoot(player, enemyField, field, isAlive);
            if (isAlive == 1) {
                isAlive1--;
            } else {
                isAlive2--;
            }
        } else {
            enemyField[x][y] = "-";
            System.out.println("Miss! Enemy's turn");
        }
    }

    public static void winnerDefine() {
        if (isAlive1 == 0 || isAlive2 == 0) {
            System.out.print("Winner: ");
            if (isAlive1 == 0) {
                System.out.println(player2);
            } else {
                System.out.println(player1);
            }
            System.out.println("! Congratulations!");
            endGame = true;
        }
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}