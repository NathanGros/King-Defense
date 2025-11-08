package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;
import kingdefense.backend.board.Tile;

public class WhiteKnight extends WhitePiece {
    private Float damage;

    public WhiteKnight(Integer x, Integer y, Float damage) {
        super(x, y);
        this.damage = damage;
    }
    public WhiteKnight(Integer x, Integer y) {
        this(x, y, 0.5f);
    }
    public WhiteKnight() {
        this(0, 0);
    }

	@Override
	public String getPieceType() {
        return "WhiteKnight";
	}

    private boolean isValid(Board board, Tile tile) {
        if (board.getWhiteKing().getTile().isSamePlace(tile))
            return false;
        if (board.getBlackKing().getTile().isSamePlace(tile))
            return false;
        if (board.isWhite(tile))
            return false;
        if (board.isEmpty(tile))
            return false;
        return true;
    }

    private Integer countEnemiesAt(Board board, Tile tile) {
        int nbEnemies = 0;
        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (BlackPiece blackPiece: board.getBlackPieces()) {
                    if (blackPiece.getTile().isSamePlace(tile.addWithTile(new Tile(i, j))))
                        nbEnemies++;
                }
            }
        }
        return nbEnemies;
    }

	@Override
	public void activate(Board board, Game game) {
        ArrayList<Tile> attainableTiles = new ArrayList<>();
        attainableTiles.add(tile.addWithTile(new Tile(2, 1)));
        attainableTiles.add(tile.addWithTile(new Tile(2, -1)));
        attainableTiles.add(tile.addWithTile(new Tile(-2, 1)));
        attainableTiles.add(tile.addWithTile(new Tile(-2, -1)));
        attainableTiles.add(tile.addWithTile(new Tile(1, 2)));
        attainableTiles.add(tile.addWithTile(new Tile(-1, 2)));
        attainableTiles.add(tile.addWithTile(new Tile(1, -2)));
        attainableTiles.add(tile.addWithTile(new Tile(-1, -2)));
        Tile bestTile= new Tile(-1, -1);
        int bestNbEnemies = -1;
        for (int i = 0; i < attainableTiles.size(); i++) {
            Tile testTile = tile.addWithTile(attainableTiles.get(i));
            if (!isValid(board, testTile))
                continue;
            int nbEnemies = countEnemiesAt(board, testTile);
            if (nbEnemies > 0 && nbEnemies > bestNbEnemies) {
                bestNbEnemies = nbEnemies;
                bestTile.set(testTile);
            }
        }
        if (bestNbEnemies <= 0)
            return;
        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <= 1; j++) {
                board.damageBlackAtPos(bestTile.addWithTile(new Tile(i, j)), damage * (1.f + queenBoost - queenNerf));
            }
        }
	}
}
