package kingdefense.backend;

import kingdefense.backend.board.*;
import kingdefense.backend.logic.*;
import kingdefense.backend.pieces.*;

public class Game {
    private Board board;
    private Boolean isRunning;

    public Game() {
        board = new Board();
        isRunning = false;
    }

    public void stopGame() {
        isRunning = false;
    }

    public void startGame() {
        isRunning = true;
        board.addWhitePiece(new WhiteKing(2, 3, 2));
        board.addBlackPiece(new BlackPawn(0, 1, 1, 1));
        board.addBlackPiece(new BlackPawn(4, 7, 1, 1));
        gameLoop();
    }

    public void gameLoop() {
        while(isRunning) {
            board.printState();
            BlackLogic.movePieces(board);
            board.printState();
            WhiteLogic.activatePieces(board, this);
        }
        board.printState();
        System.out.println("\nEnd of game\n");
    }
}
