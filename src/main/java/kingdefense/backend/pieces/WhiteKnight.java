package kingdefense.backend.pieces;

import java.util.ArrayList;
import java.util.Arrays;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;

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

    private boolean isValid(Board board, Integer x, Integer y) {
        if (board.getWhiteKing().getX() == x && board.getWhiteKing().getY() == y)
            return false;
        if (board.getBlackKing().getX() == x && board.getBlackKing().getY() == y)
            return false;
        if (board.isWhite(x, y))
            return false;
        if (board.isEmpty(x, y))
            return false;
        return true;
    }

    private Integer countEnemiesAt(Board board, Integer x, Integer y) {
        int nbEnemies = 0;
        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (BlackPiece blackPiece: board.getBlackPieces()) {
                    if (blackPiece.getX() == x + i && blackPiece.getY() == y + j)
                        nbEnemies++;
                }
            }
        }
        return nbEnemies;
    }

	@Override
	public void activate(Board board, Game game) {
        ArrayList<Integer> attainableX = new ArrayList<>(Arrays.asList(2, 2, -2, -2, 1, -1, 1, -1));
        ArrayList<Integer> attainableY = new ArrayList<>(Arrays.asList(1, -1, 1, -1, 2, 2, -2, -2));
        int bestSpotX = -1;
        int bestSpotY = -1;
        int bestNbEnemies = -1;
        for (int i = 0; i < attainableX.size(); i++) {
            int testSpotX = x + attainableX.get(i);
            int testSpotY = y + attainableY.get(i);
            if (!isValid(board, testSpotX, testSpotY))
                continue;
            int nbEnemies = countEnemiesAt(board, testSpotX, testSpotY);
            if (nbEnemies > 0 && nbEnemies > bestNbEnemies) {
                bestNbEnemies = nbEnemies;
                bestSpotX = testSpotX;
                bestSpotY = testSpotY;
            }
        }
        if (bestNbEnemies <= 0)
            return;
        for (int i = -1; i <=1; i++) {
            for (int j = -1; j <= 1; j++) {
                board.damageBlackAtPos(bestSpotX + i, bestSpotY + j, damage);
            }
        }
	}
}
