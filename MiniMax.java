package MainGame;

class MiniMax {

    private static double maxPly;

    private MiniMax() {}

    static void run (char player, Game board, double maxPly) {
        if (maxPly < 1) {
            throw new IllegalArgumentException("Maximum depth must be greater than 0.");
        }

        MiniMax.maxPly = maxPly;
        miniMax(player, board, 0);
    }

    private static int miniMax (char player, Game board, int currentPly) {
        if (currentPly++ == maxPly || board.isGameOver()) {
            return score(player, board);
        }

        if (board.getCurrentPlayer() == player) {
            return getMax(player, board, currentPly);
        } 
        else {
            return getMin(player, board, currentPly);
        }
    }

    private static int getMax (char player, Game board, int currentPly) {
        double bestScore = Double.NEGATIVE_INFINITY;
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Game modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove);

            int score = miniMax(player, modifiedBoard, currentPly);

            if (score >= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }
        }
        board.move(indexOfBestMove);
        return (int)bestScore;
    }

    private static int getMin (char player, Game board, int currentPly) {
        double bestScore = Double.POSITIVE_INFINITY;
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Game modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove);

            int score = miniMax(player, modifiedBoard, currentPly);

            if (score <= bestScore) {
                bestScore = score;
                indexOfBestMove = theMove;
            }
        }
        board.move(indexOfBestMove);
        return (int)bestScore;
    }

    private static int score (char player, Game board) {
        char opponent = (player == 'X') ? '0' : 'X';

        if (board.isGameOver() && board.getWinner() == player) {
            return 10;
        } 
        else if (board.isGameOver() && board.getWinner() == opponent) {
            return -10;
        } 
        else {
            return 0;
        }
    }
}
