package MainGame;

import java.util.HashSet;

public class Game {   
    static final int boardWidth = 3;

    private char[][] board;
    private char currentPlayer;
    private char winner;
    private HashSet<Integer> availableMoves;

    private int moveCount;
    private boolean gameOver;

    Game() {
        board = new char[3][3];
        initialize();
        reset();
    }

    private void initialize () {
        moveCount = 0;
        gameOver = false;
        currentPlayer = 'X';
        winner = '-';
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }

        availableMoves = new HashSet<>();
        availableMoves.clear();

        for (int i = 0; i < 3*3; i++) {
            availableMoves.add(i);
        }
    }
    
    public void printBoard(){
        System.out.println("-------------");
        for(int i=0; i<3; i++){
            System.out.print("| ");
            for(int j=0; j<3; j++){
                System.out.print(board[i][j]+" | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    
    public void reset(){
        moveCount = 0;
        gameOver = false;
        currentPlayer = 'X';
        winner = '-';
        initialize();
    }

    public boolean move (int index) {
        return move(index/3, index%3);
    }

    public boolean move (int x, int y) {

        if (gameOver) {
            throw new IllegalStateException("TicTacToe is over. No moves can be played.");
        }

        if (board[x][y] == '-') {
            board[x][y] = currentPlayer;
        } 
        else {
            return false;
        }

        moveCount++;
        availableMoves.remove(x * 3 + y);

        checkForWinner(x,y);

        if (moveCount == 9) {
            //winner = '-';
            gameOver = true;
        }
        
        currentPlayer = (currentPlayer == 'X') ? '0' : 'X';
        return true;
    }

    public boolean isGameOver () {
        return gameOver;
    }

    char[][] toArray () {
        return board.clone();
    }

    public char getCurrentPlayer () {
        return currentPlayer;
    }

    public char getWinner () {
        if (!gameOver) {
            throw new IllegalStateException("TicTacToe is not over yet.");
        }
        return winner;
    }

    public HashSet<Integer> getAvailableMoves () {
        return availableMoves;
    }
    
    private void checkForWinner (int row, int column) {
    	// Check rows
    	for (int i = 1; i < boardWidth; i++) {
            if (board[row][i] != board[row][i-1]) {
                break;
            }
            if (i == 2) {
                winner = currentPlayer;
                gameOver = true;
            }
        }
    	
    	// Check columns
    	for (int i = 1; i < 3; i++) {
            if (board[i][column] != board[i-1][column]) {
                break;
            }
            if (i == 2) {
                winner = currentPlayer;
                gameOver = true;
            }
        }
    	
    	// Check diagonal from top left
        if (row == column) {
            for (int i = 1; i < 3; i++) {
                if (board[i][i] != board[i-1][i-1]) {
                    break;
                }
                if (i == 2) {
                    winner = currentPlayer;
                    gameOver = true;
                }
            }
        }
        
        // Check diagonal from top right
        if (2-row == column) {
            for (int i = 1; i < 3; i++) {
                if (board[2-i][i] != board[3-i][i-1]) {
                    break;
                }
                if (i == 2) {
                    winner = currentPlayer;
                    gameOver = true;
                }
            }
        }
    }

    public Game getDeepCopy () {
        Game board = new Game();

        for (int i = 0; i < board.board.length; i++) {
            board.board[i] = this.board[i].clone();
        }

        board.currentPlayer = this.currentPlayer;
        board.winner = this.winner;
        board.availableMoves = new HashSet<>();
        board.availableMoves.addAll(this.availableMoves);
        board.moveCount = this.moveCount;
        board.gameOver = this.gameOver;
        return board;
    }

    public String toString () {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {

                if (board[y][x] == '-') {
                    sb.append("-");
                } 
                else {
                    sb.append(board[y][x]);
                }
                sb.append(" ");
            }
            if (y != 2) {
                sb.append("\n");
            }
        }
        return new String(sb);
    }
}
