import java.util.Scanner;

public class Field {
    private Scanner scanner = new Scanner(System.in);
    private String[][] field;
    static boolean correctInput;

    public Field(String[][] field) {
        this.field = field;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = " ";
            }
        }
    }

    public String[][] getField() {
        return field;
    }

    public void createField() {
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

    public void placeShip(int x, int y, int deck) {
        correctInput = true;
        if (deck != 1) {
            while (true) {
                System.out.println("Please, choose direction of the ship.");
                System.out.print("Horizontal (h) or Vertical (v): ");
                String dir = scanner.next();
                if (dir.equals("h")) {
                    if ((x + deck) > field.length) {
                        correctInput = false;
                        return;
                    }
                    for (int i = 0; i < deck; i++) {
                        if (field[x + i][y].equals("0") || !checkAvailablePosition(x + i, y)) {
                            correctInput = false;
                            return;
                        }
                    }
                    for (int j = 0; j < deck; j++) {
                        field[x + j][y] = "0";
                    }
                    break;
                } else if (dir.equals("v")) {
                    if ((y + deck) > field[0].length) {
                        correctInput = false;
                        return;
                    }
                    for (int i = 0; i < deck; i++) {
                        if (field[x][y + i].equals("0") || !checkAvailablePosition(x, y + i)) {
                            correctInput = false;
                            return;
                        }
                    }
                    for (int j = 0; j < deck; j++) {
                        field[x][y + j] = "0";
                    }
                    break;
                } else {
                    System.out.println("Wrong direction input!");
                }
            }
        } else {
            field[x][y] = "0";
        }
    }

    public boolean checkAvailablePosition(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try {
                    if (field[i][j].equals("0")) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return true;
    }
}