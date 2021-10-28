import java.io.IOException;
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
        clearScreen();
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
        int deck = 4;
        int count = 1;
        while (deck != 0) {
            for (int i = 0; i < count; i++) {
                int x;
                int y;
                while (true) {
                    System.out.println(player + ", please, create your ships on the battlefield");
                    System.out.println("Creating " + deck + "-deck ship: ");
                    try {
                        System.out.print("Enter x: ");
                        x = Integer.parseInt(scanner.next());
                        System.out.print("Enter y: ");
                        y = Integer.parseInt(scanner.next());
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong input!");
                        continue;
                    }
                    if (x >= 0 && x < field.getField().length && y >= 0 && y < field.getField()[0].length) {
                        String placeError = "Impossible to place the ship at the entered position entered direction";
                        if (field.checkAvailablePosition(x, y)) {
                            field.placeShip(x, y, deck);
                            if (Field.correctInput) {
                                break;
                            } else {
                                System.out.println(placeError);
                            }
                        } else {
                            System.out.println(placeError);
                        }
                    } else {
                        System.out.println("Array bounds error");
                    }
                    System.out.println("Wrong x and y! Wait a second...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    clearScreen();
                    field.createField();
                }
                clearScreen();
                field.createField();
            }
            count++;
            deck--;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void makeShoot(String player, Field enemyField, Field field, int isAlive) {
        if (isAlive1 == 0 || isAlive2 == 0) {
            return;
        }
        clearScreen();
        enemyField.createField();
        int x;
        int y;
        while (true) {
            System.out.println(player + ", please, make a shoot.");
            System.out.println("Enter x and y enemy's coordinates: ");
            try {
                System.out.print("Enter x: ");
                x = Integer.parseInt(scanner.next());
                System.out.print("Enter y: ");
                y = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Wrong input!");
                continue;
            }
            if (x >= 0 && x < field.getField().length && y >= 0 && y < field.getField()[0].length) {
                if (enemyField.getField()[x][y].equals("X") || enemyField.getField()[x][y].equals("-")) {
                    System.out.println("Already shot!");
                    continue;
                }
                break;
            }
            System.out.println("Wrong enemy's x and y!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen();
            enemyField.createField();
        }
        if (field.getField()[x][y].equals("0")) {
            enemyField.getField()[x][y] = "X";
            if (isAlive == 1) {
                isAlive1--;
            } else {
                isAlive2--;
            }
            System.out.println("Hit! Your turn again");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            makeShoot(player, enemyField, field, isAlive);
        } else {
            enemyField.getField()[x][y] = "-";
            System.out.println("Miss! Enemy's turn");
            try {
                Thread.sleep(2000);
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void winnerDefine() {
        if (isAlive1 == 0 || isAlive2 == 0) {
            clearScreen();
            System.out.print("Winner: ");
            if (isAlive1 == 0) {
                System.out.print(player2);
            } else {
                System.out.print(player1);
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