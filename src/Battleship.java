import java.util.Scanner;

public class Battleship {
    static Scanner scanner = new Scanner(System.in);
    static String player1;
    static String player2;

    public static void createField() {
        System.out.println("-----------------------");
        System.out.println("| |0|1|2|3|4|5|6|7|8|9|");
        for (int i = 0; i < 10; i++) {
            System.out.print("|" + i + "|");
            for (int j = 0; j < 10; j++) {
                System.out.print(" |");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    public static void main(String[] args) {
        System.out.print("Player 1, please enter your name: ");
        player1 = scanner.next();
        System.out.print("Player 2, please enter your name: ");
        player2 = scanner.next();


        createField();
    }
}