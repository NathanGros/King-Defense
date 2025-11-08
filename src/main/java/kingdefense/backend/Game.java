package kingdefense.backend;

import static com.raylib.Raylib.GetFrameTime;

import java.util.ArrayList;
import java.util.Arrays;

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
        board = new Board(50);
        windowManager = new WindowManager();
        isRunning = false;
        isInWave = false;
        isBlackTurn = false;
        nbCoins = 0;
        availableWhitePieces = new ArrayList<>();
        selectedWhitePiece = "WhiteKing";
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
	public String getSelectedWhitePiece() {
		return selectedWhitePiece;
	}
	public void setSelectedWhitePiece(String selectedWhitePiece) {
		this.selectedWhitePiece = selectedWhitePiece;
	}

    public void startGame() {
        isRunning = true;
        addAvailableWhitePiece(new WhitePawn());
        addAvailableWhitePiece(new WhitePawn());
        addAvailableWhitePiece(new WhitePawn());
        addAvailableWhitePiece(new WhitePawn());
        addAvailableWhitePiece(new WhiteKnight());
        addAvailableWhitePiece(new WhiteKnight());
        addAvailableWhitePiece(new WhiteBishop());
        addAvailableWhitePiece(new WhiteBishop());
        addAvailableWhitePiece(new WhiteRook());
        addAvailableWhitePiece(new WhiteRook());
        addAvailableWhitePiece(new WhiteQueen());
        windowManager.launchWindow();
        fillWaveStock();
        gameLoop();
    }

    public void stopGame() {
        isRunning = false;
        windowManager.closeWindow();
        System.out.println("\nEnd of game\n");
    }

    public ArrayList<PathFindingTile> getValidNeighbors(PathFindingTile tile, ArrayList<PathFindingTile> visited, ArrayList<PathFindingTile> visiting) {
        int x = tile.getTileX();
        int y = tile.getTileY();
        ArrayList<Integer> attainableX = new ArrayList<>(Arrays.asList(1, -1, 0, 0));
        ArrayList<Integer> attainableY = new ArrayList<>(Arrays.asList(0, 0, 1, -1));
        ArrayList<PathFindingTile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableX.size(); i++) {
            int neighborX = x + attainableX.get(i);
            int neighborY = y + attainableY.get(i);
            PathFindingTile neighbor = new PathFindingTile(neighborX, neighborY);
            boolean isValid = true;
            if (!board.isInBound(neighborX, neighborY))
                isValid = false;
            // Check if neighbor is not a white piece
            for (WhitePiece whitePiece: board.getWhitePieces()) {
                if (neighborX == whitePiece.getX() && neighborY == whitePiece.getY())
                    isValid = false;
            }
            // Check if neighbor is not already visited
            for (PathFindingTile visitedTile: visited) {
                if (neighbor.isSamePlace(visitedTile))
                    isValid = false;
            }
            // Check if neighbor is not already in visiting queue (BFS property)
            for (PathFindingTile visitingTile: visiting) {
                if (neighbor.isSamePlace(visitingTile))
                    isValid = false;
            }
            if (isValid)
                neighbors.add(neighbor);
        }
        return neighbors;
    }

    private boolean isKingReachable() {
        PathFindingTile whiteKingTile = new PathFindingTile(board.getWhiteKing().getX(), board.getWhiteKing().getY());
        PathFindingTile blackKingTile = new PathFindingTile(board.getBlackKing().getX(), board.getBlackKing().getY());
        ArrayList<PathFindingTile> visiting = new ArrayList<>();
        ArrayList<PathFindingTile> visited = new ArrayList<>();
        visiting.add(blackKingTile);
        while (!visiting.isEmpty()) {
            System.out.println(visiting.size());
            // End BFS
            if (visiting.getFirst().isSamePlace(whiteKingTile)) {
                return true;
            }
            ArrayList<PathFindingTile> neighbors = getValidNeighbors(visiting.getFirst(), visited, visiting);
            visiting.addAll(neighbors);
            // Mark Tile as visited
            visited.add(visiting.getFirst());
            visiting.removeFirst();
        }
        return false;
    }


    public void startWave() {
        if (isInWave)
            return;
        if (!board.getWhiteKing().isPlaced())
            return;
        if (!isKingReachable()) {
            return;
        }
        isInWave = true;
        isBlackTurn = true;
    }

    public void stopWave() {
        isInWave = false;
        fillWaveStock();
    }

    public void fillWaveStock() {
        board.getBlackKing().addStockPiece(new BlackPawn(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackPawn(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackKnight(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackBishop(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackRook(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackPawn(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackRook(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackQueen(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackKnight(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackPawn(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackBishop(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackRook(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackBishop(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackKnight(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackKnight(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackKnight(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackRook(), windowManager.getWaveBox());
        board.getBlackKing().addStockPiece(new BlackQueen(), windowManager.getWaveBox());
    }

    public void putNewWhitePiece(Integer x, Integer y) {
        if (!board.isEmpty(x, y))
            return;
        if (selectedWhitePiece == "WhiteKing" && !board.getWhiteKing().isPlaced()) {
            board.getWhiteKing().setX(x);
            board.getWhiteKing().setY(y);
            board.getWhiteKing().place();
            return;
        }
        if (board.getWhiteKing().isPlaced() && board.getWhiteKing().getX() == x && board.getWhiteKing().getY() == y)
            return;
        if (availableWhitePieces.size() == 0)
            return;
        for (WhitePiece whitePiece: availableWhitePieces) {
            if (whitePiece.getPieceType().equals(selectedWhitePiece)) {
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
        if (board.getWhiteKing().getX() == x && board.getWhiteKing().getY() == y) {
            board.getWhiteKing().unplace();
            return;
        }
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
        float timerPlayerAction = 1.f;
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
                        BlackLogic.play(this, board, windowManager.getWaveBox());
                    else
                        WhiteLogic.activatePieces(board, this);
                    isBlackTurn = !isBlackTurn;
                }
            }
            else {
                timerPlayerAction = 1.f;
            }
        }
    }
}
