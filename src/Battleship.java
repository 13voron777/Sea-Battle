import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Battleship {
    static Scanner scanner = new Scanner(System.in);
    static String player1 = Player.PLAYER1.name;
    static String player2 = Player.PLAYER2.name;
    static Field field1 = new Field(new String[10][10]);
    static Field field2 = new Field(new String[10][10]);
    static Field enemyField1 = new Field(new String[10][10]);
    static Field enemyField2 = new Field(new String[10][10]);
    static int isAlive1 = 20;
    static int isAlive2 = 20;
    static boolean endGame = false;

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

    public static void createShips(String player, Field field, int num) {
        System.out.print(player + ", please enter your name: ");
        if (num == 1) {
            player1 = scanner.next();
            player = player1;
        } else {
            player2 = scanner.next();
            player = player2;
        }
        clearScreen();
        field.createField();
        System.out.println(player + ", please, create your ships on the battlefield");
        int deck = 4;
        int count = 1;
        while (deck != 0) {
            for (int i = 0; i < count; i++) {
                System.out.println("Creating " + deck + "-deck ship: ");
                try {
                    int x;
                    int y;
                    while (true) {
                        System.out.print("Enter x and y coordinates: ");
                        x = scanner.nextInt();
                        y = scanner.nextInt();
                        if (x >= 0 && x < field.getField().length &&
                                y >= 0 && y < field.getField().length) {
                            try {
                                field.shoot(x, y, deck);
                            } catch (ArrayIndexOutOfBoundsException e) {
                                continue;
                            }
                            break;
                        }
                        System.out.println("Wrong x and y!");
                    }
                    clearScreen();
                    field.createField();
                } catch (InputMismatchException e) {
                    System.out.println("Wrong input!");
                }
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

    public static void makeShoot(String player, Field enemyField, Field field, int isAlive) {
        enemyField.createField();
        System.out.println(player + ", please, make a shoot.");
        try {
            int x;
            int y;
            while (true) {
                System.out.print("Enter x and y enemy's coordinates: ");
                x = scanner.nextInt();
                y = scanner.nextInt();
                if (x >= 0 && x < field.getField().length &&
                        y >= 0 && y < field.getField().length) {
                    break;
                }
                System.out.println("Wrong enemy's x and y!");
            }
            if (field.getField()[x][y].equals("0")) {
                enemyField.getField()[x][y] = "X";
                System.out.println("Hit! Your turn again");
                makeShoot(player, enemyField, field, isAlive);
                if (isAlive == 1) {
                    isAlive1--;
                } else {
                    isAlive2--;
                }
            } else {
                enemyField.getField()[x][y] = "-";
                System.out.println("Miss! Enemy's turn");
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input!");
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