import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <h2>TicTacToe.java. - Impements a two player game of tic-tac-toe
 */
public class TicTacToe {
    // Instance variables
    final int boardSize = 3;
    enum Status {
        WIN,
        DRAW,
        CONTINUE
    }
    Status game_staus;
    char board[][] = new char[boardSize][boardSize];
    boolean firstPlayer;
    boolean gameOver;

    // Class methods
    // Constructor
    public TicTacToe() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '_';
            }
        }
    }
    public void play() {
        while(gameStatus() == Status.CONTINUE) {
            // Print board
            printBoard();
            boolean okay = false;
            boolean errorThrown = false;

            int row = 0;
            int column = 0;

            // Get player input
            System.out.println("Player " + (firstPlayer ? "X" : "O") + "\'s turn.");
            
            while(!okay)
            {
                Scanner keyboard = new Scanner(System.in);

                try {
                    System.out.println("Player " + (firstPlayer ? "X" : "O") + ": Enter row(0, 1, or 2): ");
                    row = keyboard.nextInt();

                    System.out.println("Player " + (firstPlayer ? "X" : "O") + ": Enter column(0, 1, or 2): ");
                    column = keyboard.nextInt();

                    if ((row < 0 || row > 2) || (column < 0 || column > 2)) {
                        throw new IllegalArgumentException("Rows and columns must be between 0 and 2");
                    }
                }
                catch(InputMismatchException e) {
                    System.out.println("Enter an integer");
                    errorThrown = true;
                }
                catch(IllegalArgumentException e) {
                    System.out.println("Invalid move: " + e.getMessage());
                    errorThrown = true;
                }

                // Validate move
                if (!errorThrown) {
                    okay = validMove(row, column);
                }
            }
            
            // Update with user move
            printStatus(row, column);
        }
    }

    public void printStatus(int row, int column) {
        // Updates the board
        if (firstPlayer) {
            board[row][column] = 'X';
        }
        else {
            board[row][column] = 'O';
        }
        firstPlayer = !firstPlayer;
    }

    public Status gameStatus() {
        return Status.CONTINUE; // placeholder
    } 

    public void printBoard() {
        // Prints the board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean validMove(int row, int column) {
        if (board[row][column] == '_') {
            return true;
        }
        System.out.println("Space is not available.");
        return false;
    }

    // Main function
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}   