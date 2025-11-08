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

    public ArrayList<Tile> getValidNeighbors(Tile tile, ArrayList<Tile> visited, ArrayList<Tile> visiting) {
        ArrayList<Tile> attainableTiles = new ArrayList<>();
        attainableTiles.add(new Tile(1, 0));
        attainableTiles.add(new Tile(-1, 0));
        attainableTiles.add(new Tile(0, 1));
        attainableTiles.add(new Tile(0, -1));
        ArrayList<Tile> neighbors = new ArrayList<>();
        for (int i = 0; i < attainableTiles.size(); i++) {
            Tile neighbor = new Tile(tile.addWithTile(attainableTiles.get(i)));
            boolean isValid = true;
            if (!board.isInBound(neighbor))
                isValid = false;
            // Check if neighbor is not a white piece
            for (WhitePiece whitePiece: board.getWhitePieces()) {
                if (neighbor.isSamePlace(whitePiece.getTile()))
                    isValid = false;
            }
            // Check if neighbor is not already visited
            for (Tile visitedTile: visited) {
                if (neighbor.isSamePlace(visitedTile))
                    isValid = false;
            }
            // Check if neighbor is not already in visiting queue (BFS property)
            for (Tile visitingTile: visiting) {
                if (neighbor.isSamePlace(visitingTile))
                    isValid = false;
            }
            if (isValid)
                neighbors.add(neighbor);
        }
        return neighbors;
    }

    private boolean isKingReachable() {
        Tile whiteKingTile = new Tile(board.getWhiteKing().getTile());
        Tile blackKingTile = new Tile(board.getBlackKing().getTile());
        ArrayList<Tile> visiting = new ArrayList<>();
        ArrayList<Tile> visited = new ArrayList<>();
        visiting.add(blackKingTile);
        while (!visiting.isEmpty()) {
            System.out.println(visiting.size());
            // End BFS
            if (visiting.getFirst().isSamePlace(whiteKingTile)) {
                return true;
            }
            ArrayList<Tile> neighbors = getValidNeighbors(visiting.getFirst(), visited, visiting);
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
        if (!isKingReachable())
            return;
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

    public void putNewWhitePiece(Tile tile) {
        if (!board.isEmpty(tile))
            return;
        if (selectedWhitePiece == "WhiteKing" && !board.getWhiteKing().isPlaced()) {
            board.getWhiteKing().setTile(new Tile(tile));
            board.getWhiteKing().place();
            return;
        }
        if (board.getWhiteKing().isPlaced() && board.getWhiteKing().getTile().isSamePlace(tile))
            return;
        if (availableWhitePieces.size() == 0)
            return;
        for (WhitePiece whitePiece: availableWhitePieces) {
            if (whitePiece.getPieceType().equals(selectedWhitePiece)) {
                WhitePiece newWhitePiece = whitePiece;
                availableWhitePieces.remove(newWhitePiece);
                newWhitePiece.setTile(new Tile(tile));
                board.addWhitePiece(newWhitePiece);
                break;
            }
        }
    }

    public void removeWhitePiece(Tile tile) {
        if (board.getWhiteKing().getTile().isSamePlace(tile)) {
            board.getWhiteKing().unplace();
            return;
        }
        ArrayList<WhitePiece> removeList = new ArrayList<>();
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            if (whitePiece.getTile().isSamePlace(tile)) {
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
