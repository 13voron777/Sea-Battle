import java.io.IOException;
import java.util.Scanner;

public class Battleship {
    static Scanner scanner = new Scanner(System.in);
    static String player1 = "Player 1";
    static String player2 = "Player 2";
    static String[][] field1 = new String[10][10];
    static String[][] field2 = new String[10][10];

    static {
        for (int i = 0; i < field1.length; i++) {
            for (int j = 0; j < field1[i].length; j++) {
                field1[i][j] = " ";
                field2[i][j] = " ";
            }
        }
    }

    public static void main(String[] args) {
        System.out.print("Player 1, please enter your name: ");
        player1 = scanner.next();
        clearScreen();
        //System.out.print("Player 2, please enter your name: ");
        //player2 = scanner.next();

        createField(field1);
        createShips(player1, field1, 4);
        createField(field1);
        createShips(player1, field1, 3);
        createField(field1);
        createShips(player1, field1, 3);
        createField(field1);
        createShips(player1, field1, 2);
        createField(field1);
        createShips(player1, field1, 2);
        createField(field1);
        createShips(player1, field1, 2);
        createField(field1);
        createShips(player1, field1, 1);
        createField(field1);
        createShips(player1, field1, 1);
        createField(field1);
        createShips(player1, field1, 1);
        createField(field1);
        createShips(player1, field1, 1);
        createField(field1);
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

    public static void createShips(String player, String[][] field, int deck) {
        System.out.println(player + ", please, create your ships on the battlefield");
        System.out.println("Creating " + deck + "-deck ship: ");
        System.out.print("Enter x and y coordinates: ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        field[x][y] = "0";
        if (deck != 1) {
            System.out.println("Please, choose direction of the ship.");
            System.out.print("Horisontal (h) or Vertical (v): ");
            String dir = scanner.next();
            if (dir.equals("h")) {
                switch (deck) {
                    case 2:
                        field[x + 1][y] = "0";
                        break;
                    case 3:
                        field[x + 1][y] = "0";
                        field[x + 2][y] = "0";
                        break;
                    case 4:
                        field[x + 1][y] = "0";
                        field[x + 2][y] = "0";
                        field[x + 3][y] = "0";
                        break;
                }
            } else {
                switch (deck) {
                    case 2:
                        field[x][y + 1] = "0";
                        break;
                    case 3:
                        field[x][y + 1] = "0";
                        field[x][y + 2] = "0";
                        break;
                    case 4:
                        field[x][y + 1] = "0";
                        field[x][y + 2] = "0";
                        field[x][y + 3] = "0";
                        break;
                }
            }
        }
        clearScreen();
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}