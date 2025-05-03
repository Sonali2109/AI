import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueens {

    public static boolean placeQueens(int i, int[] cols, int[] leftDiagonal, int[] rightDiagonal, List<Integer> cur, char[][] board) {
        int n = cols.length;

        if (i == n) {
            return true; // All queens are placed
        }

        for (int j = 0; j < n; j++) {
            if (cols[j] == 1 || rightDiagonal[i + j] == 1 || leftDiagonal[i - j + n - 1] == 1) {
                continue; // Skip if the column or diagonals are already occupied
            }

            // Place the queen
            cols[j] = 1;
            rightDiagonal[i + j] = 1;
            leftDiagonal[i - j + n - 1] = 1;
            cur.add(j);

            // Recur to place the next queen
            if (placeQueens(i + 1, cols, leftDiagonal, rightDiagonal, cur, board)) {
                return true;
            }

            // Backtrack
            cur.remove(cur.size() - 1);
            cols[j] = 0;
            rightDiagonal[i + j] = 0;
            leftDiagonal[i - j + n - 1] = 0;
        }

        return false; // No valid position found
    }

    public static char[][] nQueen(int n) {
        int[] cols = new int[n];
        int[] leftDiagonal = new int[n * 2];
        int[] rightDiagonal = new int[n * 2];
        List<Integer> cur = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize the board with '.'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        if (placeQueens(0, cols, leftDiagonal, rightDiagonal, cur, board)) {
            for (int i = 0; i < n; i++) {
                board[i][cur.get(i)] = 'Q'; // Place queens on the board
            }
            return board;
        } else {
            return null; // No solution exists
        }
    }

    public static void printBoard(char[][] board) {
        if (board != null) {
            for (char[] row : board) {
                for (char cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution exists.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of queens: ");
        int n = scanner.nextInt();
        char[][] board = nQueen(n);
        printBoard(board);
        scanner.close();
    }
}
