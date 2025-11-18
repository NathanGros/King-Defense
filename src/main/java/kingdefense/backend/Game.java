package kingdefense.backend;

import static com.raylib.Raylib.GetFrameTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import kingdefense.backend.board.*;
import kingdefense.backend.logic.*;
import kingdefense.backend.pieces.*;
import kingdefense.frontend.game.GameScreen;

public class Game {
    private GameScreen gameScreen;
	private boolean isGameRunning;
    private Shop shop;
	private boolean isShopRunning;
    private Board board;
    private Integer waveNb;
    private ArrayList<Float> thresholds = new ArrayList<>(Arrays.asList(0.1f, 0.0f, 0.0f, 0.0f, 0.0f));
    private boolean isInWave;
	private boolean isBlackTurn;
    private Integer nbCoins;
    private ArrayList<WhitePiece> availableWhitePieces;
    private String selectedWhitePiece;

	public Game() {
        gameScreen = new GameScreen();
        isGameRunning = true;
        isShopRunning = false;
        board = new Board(50);
        waveNb = 1;
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
        isGameRunning = true;
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
        gameScreen.launchGameScreen();
        fillWaveStock();
        shop = new Shop(this);
        gameLoop();
    }

    public void stopGame() {
        isGameRunning = false;
        gameScreen.stopGameScreen();
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
        waveNb++;
        fillWaveStock();
    }

    private void updateThresholds() {
        Float sum = 0.f;
        for (Float val: thresholds) {
            sum += val;
        }
        Integer pieceType;
        if (waveNb < 3) {
            return;
        }
        else if (waveNb < 5) {
            pieceType = new Random().nextInt(2);
        }
        else if (waveNb < 8) {
            pieceType = new Random().nextInt(3);
        }
        else if (waveNb < 10) {
            pieceType = new Random().nextInt(4);
        }
        else {
            pieceType = new Random().nextInt(5);
        }
        thresholds.set(pieceType, thresholds.get(pieceType) + sum * 0.5f);
    }

    private void fillWaveStock() {
        updateThresholds();
        float sum = 0.f;
        for (Float val: thresholds) {
            sum += val;
        }
        for (int i = 0; i < waveNb; i++) {
            float probabilityResult = new Random().nextFloat() * sum;
            float probabilityThreshold = thresholds.get(0);
            if (probabilityResult <= probabilityThreshold) {
                board.getBlackKing().addStockPiece(new BlackPawn(), gameScreen.getWaveBox());
                continue;
            }
            probabilityThreshold += thresholds.get(1);
            if (probabilityResult <= probabilityThreshold) {
                board.getBlackKing().addStockPiece(new BlackKnight(), gameScreen.getWaveBox());
                continue;
            }
            probabilityThreshold += thresholds.get(2);
            if (probabilityResult <= probabilityThreshold) {
                board.getBlackKing().addStockPiece(new BlackBishop(), gameScreen.getWaveBox());
                continue;
            }
            probabilityThreshold += thresholds.get(3);
            if (probabilityResult <= probabilityThreshold) {
                board.getBlackKing().addStockPiece(new BlackRook(), gameScreen.getWaveBox());
                continue;
            }
            board.getBlackKing().addStockPiece(new BlackQueen(), gameScreen.getWaveBox());
        }
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

    public void startShop() {
        isShopRunning = true;
    }

    public void gameLoop() {
        float timerPlayerAction = 1.f;
        while (isGameRunning) {
            gameScreen.drawScreen(this);
            gameScreen.interactScreen(this);
            if (board.getWhiteKing().getHealth() <= 0) {
                stopGame();
                continue;
            }
            if (isInWave) {
                timerPlayerAction += GetFrameTime();
                if (timerPlayerAction >= 1.f) {
                    timerPlayerAction = 0.f;
                    if (isBlackTurn)
                        BlackLogic.play(this, board, gameScreen.getWaveBox());
                    else
                        WhiteLogic.activatePieces(board, this);
                    isBlackTurn = !isBlackTurn;
                }
            }
            else {
                timerPlayerAction = 1.f;
            }
            if (isShopRunning) {
                shop.startShop();
                isShopRunning = false;
            }
        }
    }
}
