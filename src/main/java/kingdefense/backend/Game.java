package kingdefense.backend;

import kingdefense.backend.board.*;
import kingdefense.backend.logic.*;
import kingdefense.backend.pieces.*;
import kingdefense.frontend.WindowManager;

public class Game {
    private Board board;
	private Boolean isRunning;
    private WindowManager windowManager;

    public Game() {
        board = new Board();
        isRunning = false;
        windowManager = new WindowManager();
    }

    public Board getBoard() {
		return board;
	}

    public void stopGame() {
        isRunning = false;
        windowManager.closeWindow();
        System.out.println("\nEnd of game\n");
    }

    public void startGame() {
        isRunning = true;
        board.addWhitePiece(new WhitePawn(1, 0));
        board.getBlackKing().addStockPiece(new BlackPawn());
        board.getBlackKing().addStockPiece(new BlackPawn());
        board.getBlackKing().addStockPiece(new BlackKnight());
        windowManager.launchWindow();
        gameLoop();
    }

    public void makeOneTurn() {
        BlackLogic.play(board);
        WhiteLogic.activatePieces(board, this);
    }

    public void gameLoop() {
        while(isRunning) {
            // board.printState();
            windowManager.windowInteract(this);
            if (board.getWhiteKing().getHealth() <= 0) {
                stopGame();
            }
        }
    }
}
