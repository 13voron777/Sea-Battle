import java.util.Scanner;

public class Field {
    private Scanner scanner = new Scanner(System.in);
    private String[][] field;

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
                System.out.print(this.field[j][i] + "|");
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    public void shoot(int x, int y, int deck) {
        if (deck != 1) {
            try {
                while (true) {
                    System.out.println("Please, choose direction of the ship.");
                    System.out.print("Horisontal (h) or Vertical (v): ");
                    String dir = scanner.next();
                    if (dir.equals("h")) {
                        for (int j = 0; j < deck; j++) {
                            this.field[x + j][y] = "0";
                        }
                        break;
                    } else if (dir.equals("v")) {
                        for (int j = 0; j < deck; j++) {
                            this.field[x][y + j] = "0";
                        }
                        break;
                    } else {
                        System.out.println("Wrong direction input!");
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Array bounds error");
            }
        } else {
            this.field[x][y] = "0";
        }
    }
}