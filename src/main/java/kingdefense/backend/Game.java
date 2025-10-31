package kingdefense.backend;

import static com.raylib.Raylib.GetFrameTime;

import kingdefense.backend.board.*;
import kingdefense.backend.logic.*;
import kingdefense.backend.pieces.*;
import kingdefense.frontend.WindowManager;

public class Game {
    private Board board;
	private boolean isRunning;
    private WindowManager windowManager;
    private boolean isInWave;
    private boolean isBlackTurn = false;

    public Game() {
        board = new Board();
        isRunning = false;
        isInWave = false;
        windowManager = new WindowManager();
    }

    public Board getBoard() {
		return board;
	}

    public void startGame() {
        isRunning = true;
        board.addWhitePiece(new WhitePawn(1, 0));
        board.addWhitePiece(new WhitePawn(1, 2));
        board.addWhitePiece(new WhitePawn(0, 2));
        board.addWhitePiece(new WhitePawn(6, 2));
        board.addWhitePiece(new WhiteRook(6, 0));
        windowManager.launchWindow();
        gameLoop();
    }

    public void stopGame() {
        isRunning = false;
        windowManager.closeWindow();
        System.out.println("\nEnd of game\n");
    }

    public void startWave() {
        if (isInWave)
            return;
        isInWave = true;
        isBlackTurn = true;
        board.getBlackKing().addStockPiece(new BlackPawn());
        board.getBlackKing().addStockPiece(new BlackPawn());
        board.getBlackKing().addStockPiece(new BlackKnight());
    }

    public void stopWave() {
        isInWave = false;
    }

    public void makeOneTurn() {
        BlackLogic.play(this, board);
        WhiteLogic.activatePieces(board, this);
    }

    public void gameLoop() {
        float timerPlayerAction = 0.f;
        while(isRunning) {
            // board.printState();
            windowManager.windowInteract(this);
            if (board.getWhiteKing().getHealth() <= 0) {
                stopGame();
            }
            if (isInWave) {
                timerPlayerAction += GetFrameTime();
                if (timerPlayerAction >= 1.f) {
                    timerPlayerAction = 0.f;
                    if (isBlackTurn)
                        BlackLogic.play(this, board);
                    else
                        WhiteLogic.activatePieces(board, this);
                    isBlackTurn = !isBlackTurn;
                }
            }
        }
    }
}
