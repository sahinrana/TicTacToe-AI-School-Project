package MainGame;

import java.util.Scanner;

public class MainGame {

    private Game board;
    private Scanner sc = new Scanner(System.in);

    private MainGame() {
        board = new Game();
    }

    private void play () {
        System.out.println("Tic-Tac-Toe\nStarting a new game.");

        while (true) {
            board.printBoard();
            System.out.println(board.getCurrentPlayer() + "'s turn.");
            playMove();

            if (board.isGameOver()) {
                printWinner();
                
                if(!newTry()){
                    break;
                }
            }
        }
    }

    private void playMove () {
        if (board.getCurrentPlayer() == 'X') {
            getPlayerMove();
        } 
        else {
            System.out.println("Player X, please wait for computer's turn.");
            MiniMax.run(board.getCurrentPlayer(), board, Double.POSITIVE_INFINITY);
        }
    }

    private void getPlayerMove () {
        System.out.print("Please, enter a row and a column: ");

        int row = sc.nextInt() - 1;
        int column = sc.nextInt() - 1; 

        if (row < 0 || row >= 3 || column < 0 || column >= 3) {
            System.out.println("\nInvalid move. The row and column must be between 1 and 3.");
        } 
        else if (!board.move(row, column)) {
            System.out.println("\nInvalid move. The selected field must be blank.");
        }
    }

    private void printWinner () {
        char winner = board.getWinner();

        board.printBoard();

        if (winner == '-') {
            System.out.println("The TicTacToe is a Draw.");
        } 
        else if (winner == 'X'){
            System.out.println("Congratulations! You (X) are the winner!");
        }
        else if (winner == '0') {
            System.out.println("Computer (O) wins!");
        } 
    }
    
    private boolean tryAgain(){
        while (true) {
            System.out.print("Would you like to start a new game? (Y/N): ");
            String response = sc.next();
            
            if (response.equalsIgnoreCase("y")) {
                return true;
            } 
            else if (response.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("\nInvalid input.");
        }
    }
    
    private boolean newTry(){
        if(tryAgain()){
            board.reset();
            System.out.println("\nStarted a new game.");
            System.out.println("X's turn.");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MainGame ticTacToe = new MainGame();
        ticTacToe.play();
    }

}
