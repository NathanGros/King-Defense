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
        board.addBlackPiece(new BlackPawn(1, 1, 1, 1));
        board.addBlackPiece(new BlackPawn(3, 1, 1, 1));
        gameLoop();
    }

    public void gameLoop() {
        board.printState();
        while(isRunning) {
            BlackLogic.play(board);
            System.out.println("after black turn:");
            board.printState();
            WhiteLogic.activatePieces(board, this);
            System.out.println("after white turn:");
            board.printState();
        }
        System.out.println("\nEnd of game\n");
    }
}
