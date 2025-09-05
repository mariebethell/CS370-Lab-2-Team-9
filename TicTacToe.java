/**
 * Team 9: Benjamin Jones, Logan Huyn, Marie Bethell, Sarah Mcneal
 */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * TicTacToe.java. - Implements a two player game of tic-tac-toe
 */
public class TicTacToe {
    // Instance variables
    final int boardSize = 3;
    enum Status {
        WIN,
        DRAW,
        CONTINUE
    }
    Status game_status;
    char board[][] = new char[boardSize][boardSize];
    boolean firstPlayer; // first player is also used to indicate current player
    boolean gameOver;

    // Class methods (printSymbol excluded because of redundancy)

    // Constructor
    public TicTacToe() {
        /**
         * Initialize board to empty squares (represented by underscores)
         */
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '_';
            }
        }
    }

    public void play() {
        /**
         * Manages game play.
         * Prompts user for input while game status is not a win or a draw.
         * Updates board with player moves.
         */
        while(gameStatus() == Status.CONTINUE) {
            // Print board
            printBoard();
            boolean inputValid = false;  // Bool to check if player entered valid move between 0 and 2
            boolean errorThrown = false; // Bool to handle if player input is not an integer

            int row = 0;
            int column = 0;

            // Display current player
            System.out.println("Player " + (firstPlayer ? "X" : "O") + "\'s turn.");

            // Get player input and validate
            while(!inputValid)
            {
                try {
                    Scanner keyboard = new Scanner(System.in);
                    errorThrown = false;

                    // Get row
                    System.out.println("Player " + (firstPlayer ? "X" : "O") + ": Enter row(0, 1, or 2): ");
                    row = keyboard.nextInt();

                    // Get column
                    System.out.println("Player " + (firstPlayer ? "X" : "O") + ": Enter column(0, 1, or 2): ");
                    column = keyboard.nextInt();

                    // Make sure row and column is between 0 and 2
                    if ((row < 0 || row > 2) || (column < 0 || column > 2)) {
                        errorThrown = true;
                        System.out.println("Row and column must be between 0 and 2");
                    }
                }
                catch(InputMismatchException e) {
                    // Handle exception if input is not an integer
                    System.out.println("Enter an integer");
                    errorThrown = true;
                }
                catch(Exception e) {
                    System.out.println("An error was thrown");
                    System.out.println(e);
                }
                
                // If input is between 0 and 2, determine if space is available
                if (!errorThrown) {
                    inputValid = validMove(row, column);
                }
            }
            
            // If move is valid and space is available update board with user move
            printStatus(row, column);
        }
        printBoard();
        if (game_status == Status.WIN) {
            // If last move was a winning move, display player that won
            firstPlayer = !firstPlayer; // Returning current player to player who just moved
            System.out.println("Player " + (firstPlayer ? "X" : "O") + " wins.");
        }
        else if (game_status == Status.DRAW) {
            // If last move was the final move, display draw
            System.out.println("It's a tie.");
        }
    }

    private void printStatus(int row, int column) {
        /**
         * Updates board with most recent move.
         * Switches which player is current player.
         */
        if (firstPlayer) {
            board[row][column] = 'X';
        }
        else {
            board[row][column] = 'O';
        }
        firstPlayer = !firstPlayer;
    }

    private Status gameStatus() {
        /**
         * Determines status of the game.
         * Win:
         *  All elements in a row or column are the same.
         *  All elements in a diagonal are the same.
         * Draw:
         *  All spaces in the board are filled.
         * Continue:
         *  No player has won and there are still available spaces.
         */
        game_status = Status.CONTINUE; // default status

        // Check if there are 3 X's or O's in a row
        for (int i = 0; i < boardSize; i++) {
            if ((board[i][0] != '_') && (board[i][0] == board[i][1] && board[i][1] == board[i][2])) {
                game_status = Status.WIN;
            }
        }

        // Check if there are 3 X's or O's in a column
        for (int i = 0; i < boardSize; i++) {
            if ((board[0][i] != '_') && (board[0][i] == board[1][i] && board[1][i] == board[2][i])) {
                game_status = Status.WIN;
            }
        }

        // Check if there are 3 X's or O's in a diagonal
        if ((board[0][0] != '_') && (board[0][0] == board[1][1] && board[1][1] == board[2][2])) {
            game_status = Status.WIN;
        }

        if ((board[2][0] != '_') && (board[2][0] == board[1][1] && board[1][1] == board[0][2])) {
            game_status = Status.WIN;
        }

        // Check for a draw (checking that there are no more available spaces)
        boolean draw = true;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == '_') {
                    draw = false;
                }
            }
        }

        if (draw) {
            game_status = Status.DRAW;
        }

        return game_status;
    }

    public boolean gameOver() {
        /**
         * Public method for determining whether the game has ended.
         */
        if (game_status == Status.WIN || game_status == Status.DRAW) {
            return true;
        }
        return false;
    }

    public void printBoard() {
        /**
         * Prints tic-tac-toe board with top and bottom border.
         * Added spaces and newlines for legibility.
         */
        System.out.println("---------------");
        System.out.print("\n\n");
        for (int i = 0; i < boardSize; i++) {
            System.out.print("   ");
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + "   ");
            }
            System.out.print("\n\n\n");
        }
        System.out.println("---------------");
    }

    public boolean validMove(int row, int column) {
        /**
         * Determine if a space is available.
         */
        if (board[row][column] == '_') {
            return true;
        }
        System.out.println("Space is not available.");
        return false;
    }

    // Main function
    public static void main(String[] args) {
        // Create new instance of TicTacToe game
        TicTacToe game = new TicTacToe();
        game.play(); // start game play
    }
}