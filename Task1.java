import java.util.Arrays;
import java.util.Scanner;

public class Task1 {

    static Scanner scan = new Scanner(System.in, "866");
    static Character[][] field = {{'.', '.', '.'}, {'.', '.', '.'}, {'.', '.', '.'}};

    // функция, распечатывающая матрицу
    static void printField(Character[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print("  " + matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    // функция обрабатывающая ошибку ввода пользователем (если введено не число)
    static int[] inputArrayInt() {
        while (true) {
            try {
                String str = scan.nextLine();
                return Arrays.stream(str.replaceAll(" ", "").
                        split("-")).mapToInt(Integer::parseInt).toArray();
            } catch (Exception e) {
                System.out.print("Введено неверное число или не число! Введите еще раз...\n");
            }
        }
    }


    // функция, проверяющая выигрыш
    static boolean checkRowsWin(Character[][] matrix) {
        for (Character[] rows : matrix) {
            if (Arrays.stream(rows).allMatch(t -> t == 'X')) {
                System.out.println("Победитель - Игрок 1");
                return true;
            } else if (Arrays.stream(rows).allMatch(t -> t == '0')) {
                System.out.println("Победитель - Игрок 2");
                return true;
            }
        }
        return false;
    }

    static boolean checkColumnsWin(Character[][] matrix) {
        Character[][] newArr = new Character[matrix[0].length][matrix.length];
        for (int i = 0; i < newArr.length; i++) {
            for (int j = 0; j < newArr[i].length; j++) {
                newArr[i][j] = matrix[j][i];
            }
        }
        return checkRowsWin(newArr);
    }

    static boolean checkDiagonalWin(Character[][] matrix) {
        Character[][] diagonalArr = new Character[2][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            diagonalArr[0][i] = matrix[i][i];
            diagonalArr[1][i] = matrix[matrix.length - 1 - i][i];
        }
        return checkRowsWin(diagonalArr);
    }


    public static void main(String[] args) {
        int matrixElements = field.length * field[0].length;
        String printElement;
        char fieldElement;
        int playerNumber;

        for (int i = 1; i < matrixElements + 1; i++) {
            if (i % 2 == 1) {
                printElement = "крестики";
                fieldElement = 'X';
                playerNumber = 1;
            } else {
                printElement = "нолики";
                fieldElement = '0';
                playerNumber = 2;
            }
            printField(field);
            System.out.printf("\n Ход № %s, игрок № %s - ходят %s \n\n", i, playerNumber, printElement);

            System.out.println("Введите ячейку в формате строка-столбец через тире, начиная с нуля (например: 0-2): ");
            boolean flag = false;

            while (!flag) {
                int[] arrayAddress = inputArrayInt();
                if (arrayAddress[0] >= 0 && arrayAddress[0] < field.length &&
                        arrayAddress[1] >= 0 && arrayAddress[1] < field[0].length) {
                    if (field[arrayAddress[0]][arrayAddress[1]] == '.') {
                        field[arrayAddress[0]][arrayAddress[1]] = fieldElement;
                        flag = true;
                    } else {
                        System.out.println("Данное поле уже занято. Попробуйте ввести другую ячейку");
                    }
                } else {
                    System.out.println("Введены числа, превышающие размер поля. Введите еще раз ...");
                }
            }
            if (checkRowsWin(field) || checkColumnsWin(field) || checkDiagonalWin(field)) {
                System.out.println();
                printField(field);
                System.out.println("Игра закончена");
                break;
            }
        }

        if (!(checkRowsWin(field) || checkColumnsWin(field) || checkDiagonalWin(field))) {
            System.out.println();
            printField(field);
            System.out.println("\n НИЧЬЯ! Победителей нет! ");
        }
    }
}
// Добавил комменты


