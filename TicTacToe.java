import java.util.Scanner;

/**
 * TicTacToe
 * -------------------------
 * A command-line based Tic Tac Toe game using a 5x5 grid.
 * Two players take turns entering row and column positions.
 * The game checks for a winner after each move and supports replay.
 *
 * Player 1 : Asritha (X)
 * Player 2 : User-defined (O)
 */
public class TicTacToe {

    // Board size constant
    static final int SIZE = 5;

    // 5x5 game board
    static char[][] board = new char[SIZE][SIZE];

    /**
     * Main method – entry point of the game.
     * Handles player input, turn switching, and game flow.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Display game instructions
        printInstructions();

        // Player names
        String playerX = "Asritha"; // Fixed player name
        System.out.print("Enter Player 2 name (O): ");
        String playerO = sc.nextLine();

        boolean playAgain;

        do {
            initializeBoard(); // Reset board for new game
            char currentPlayer = 'X';
            boolean gameOver = false;

            while (!gameOver) {
                printBoard();

                // Determine current player's name
                String currentName =
                        (currentPlayer == 'X') ? playerX : playerO;

                System.out.println("\n" + currentName +
                        "'s turn (" + currentPlayer + ")");

                int row, col;

                // Keep asking until a valid move is entered
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

                // Check win condition
                if (checkWinner(currentPlayer)) {
                    printBoard();
                    System.out.println("\n " + currentName + " wins!");
                    gameOver = true;
                }
                // Check draw condition
                else if (isBoardFull()) {
                    printBoard();
                    System.out.println("\n It's a draw!");
                    gameOver = true;
                }
                // Switch player turn
                else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }

            System.out.print("\nDo you want to play again? (yes/no): ");
            sc.nextLine(); // Clear input buffer
            playAgain = sc.nextLine().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thanks for playing!");
        sc.close();
    }

    /**
     * Initializes the board with empty spaces.
     */
    static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Prints the current state of the game board.
     */
    static void printBoard() {
        System.out.println("\nCurrent Board:");
        System.out.print("   ");
        for (int i = 0; i < SIZE; i++) System.out.print(i + "   ");
        System.out.println();

        // Top border
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

    /**
     * Validates whether the move is within bounds and the cell is empty.
     */
    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE &&
               col >= 0 && col < SIZE &&
               board[row][col] == ' ';
    }

    /**
     * Checks whether the given player has won the game.
     */
    static boolean checkWinner(char player) {

        // Check all rows
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

        // Check all columns
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

        // Check main diagonal
        boolean win = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] != player) {
                win = false;
                break;
            }
        }
        if (win) return true;

        // Check anti-diagonal
        win = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][SIZE - 1 - i] != player) {
                win = false;
                break;
            }
        }
        return win;
    }

    /**
     * Checks whether the board is full (draw condition).
     */
    static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    /**
     * Displays game instructions before starting.
     */
    static void printInstructions() {
        
        System.out.println("TIC TAC TOE 5 x 5");
   
        System.out.println("Player 1 : Asritha (X)");
        System.out.println("Player 2 : User-defined (O)");
        System.out.println("Enter row and column between 0 and 4.");
        System.out.println("First to get 5 in a row wins!");
       
    }
}
