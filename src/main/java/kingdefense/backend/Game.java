package kingdefense.backend;

import static com.raylib.Raylib.GetFrameTime;

import java.util.ArrayList;

import kingdefense.backend.board.*;
import kingdefense.backend.logic.*;
import kingdefense.backend.pieces.*;
import kingdefense.frontend.WindowManager;

public class Game {
    private Board board;
    private WindowManager windowManager;
	private boolean isRunning;
    private boolean isInWave;
	private boolean isBlackTurn;
    private Integer nbCoins;
    private ArrayList<WhitePiece> availableWhitePieces;
    private String selectedWhitePiece;

	public Game() {
        board = new Board();
        windowManager = new WindowManager();
        isRunning = false;
        isInWave = false;
        isBlackTurn = false;
        nbCoins = 0;
        availableWhitePieces = new ArrayList<>();
        selectedWhitePiece = "WhiteBishop";
    }

    public Board getBoard() {
		return board;
	}
    public boolean isInWave() {
		return isInWave;
	}
    public Integer getNbCoins() {
		return nbCoins;
	}
	public void setNbCoins(Integer nbCoins) {
		this.nbCoins = nbCoins;
	}
	public void addCoins(Integer nbCoins) {
		this.nbCoins += nbCoins;
	}
    public void addAvailableWhitePiece(WhitePiece whitePiece) {
        availableWhitePieces.add(whitePiece);
    }
    public ArrayList<WhitePiece> getAvailableWhitePieces() {
        return availableWhitePieces;
    }

    public void startGame() {
        isRunning = true;
        addAvailableWhitePiece(new WhitePawn());
        addAvailableWhitePiece(new WhitePawn());
        addAvailableWhitePiece(new WhitePawn());
        addAvailableWhitePiece(new WhiteRook());
        addAvailableWhitePiece(new WhiteBishop());
        addAvailableWhitePiece(new WhiteBishop());
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

    public void putNewWhitePiece(Integer x, Integer y) {
        if (availableWhitePieces.size() == 0)
            return;
        if (!board.isEmpty(x, y))
            return;
        if (board.getBlackKing().getX() == x && board.getBlackKing().getY() == y)
            return;
        if (board.getWhiteKing().getX() == x && board.getWhiteKing().getY() == y)
            return;
        for (WhitePiece whitePiece: availableWhitePieces) {
            if (whitePiece.getPieceType() == selectedWhitePiece) {
                WhitePiece newWhitePiece = whitePiece;
                availableWhitePieces.remove(newWhitePiece);
                newWhitePiece.setX(x);
                newWhitePiece.setY(y);
                board.addWhitePiece(newWhitePiece);
                break;
            }
        }
    }

    public void removeWhitePiece(Integer x, Integer y) {
        ArrayList<WhitePiece> removeList = new ArrayList<>();
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            if (whitePiece.getX() == x && whitePiece.getY() == y) {
                availableWhitePieces.add(whitePiece);
                removeList.add(whitePiece);
            }
        }
        for (WhitePiece whitePiece: removeList) {
            board.removeWhitePiece(whitePiece);
        }
    }

    public void gameLoop() {
        float timerPlayerAction = 0.f;
        while(isRunning) {
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
