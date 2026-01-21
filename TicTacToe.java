import java.util.Scanner;

public class TicTacToe {

    static final int SIZE = 5;
    static char[][] board = new char[SIZE][SIZE];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Instructions
        printInstructions();

        // Player names
        String playerX = "Asritha"; // fixed name
        System.out.print("Enter Player 2 name (O): ");
        String playerO = sc.nextLine();

        boolean playAgain;

        do {
            initializeBoard();
            char currentPlayer = 'X';
            boolean gameOver = false;

            while (!gameOver) {
                printBoard();

                String currentName =
                        (currentPlayer == 'X') ? playerX : playerO;

                System.out.println("\n" + currentName +
                        "'s turn (" + currentPlayer + ")");

                int row, col;

                while (true) {
                    System.out.print("Enter row and column (0-4): ");
                    row = sc.nextInt();
                    col = sc.nextInt();

                    if (isValidMove(row, col)) {
                        board[row][col] = currentPlayer;
                        break;
                    } else {
                        System.out.println("Invalid move! Try again.");
                    }
                }

                if (checkWinner(currentPlayer)) {
                    printBoard();
                    System.out.println("\n🎉 " + currentName + " wins!");
                    gameOver = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("\n🤝 It's a draw!");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            sc.nextLine(); // clear buffer
            playAgain = sc.nextLine().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thanks for playing!");
        sc.close();
    }

    // Initialize board
    static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Print board with top border
    static void printBoard() {
        System.out.println("\nCurrent Board:");
        System.out.print("   ");
        for (int i = 0; i < SIZE; i++) System.out.print(i + "   ");
        System.out.println();

        System.out.print("  ");
        for (int j = 0; j < SIZE; j++) System.out.print("+---");
        System.out.println("+");

        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");

            System.out.print("  ");
            for (int j = 0; j < SIZE; j++) System.out.print("+---");
            System.out.println("+");
        }
    }

    // Validate move
    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE &&
               col >= 0 && col < SIZE &&
               board[row][col] == ' ';
    }

    // Check winner
    static boolean checkWinner(char player) {

        // Rows
        for (int i = 0; i < SIZE; i++) {
            boolean win = true;
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != player) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Columns
        for (int j = 0; j < SIZE; j++) {
            boolean win = true;
            for (int i = 0; i < SIZE; i++) {
                if (board[i][j] != player) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Main diagonal
        boolean win = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] != player) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Anti-diagonal
        win = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][SIZE - 1 - i] != player) {
                win = false;
                break;
            }
        }
        return win;
    }

    // Check draw
    static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    // Instructions
    static void printInstructions() {
        
        System.out.println("      TIC TAC TOE 5 x 5");
       
    }
}
