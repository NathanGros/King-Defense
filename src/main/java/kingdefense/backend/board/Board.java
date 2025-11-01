package kingdefense.backend.board;

import java.util.ArrayList;
import kingdefense.backend.pieces.*;

public class Board {
    private ArrayList<WhitePiece> whitePieces;
    private ArrayList<BlackPiece> blackPieces;
    private ArrayList<CoinTile> coins;
    private BlackKing blackKing;
    private WhiteKing whiteKing;

    public Board(Integer whiteHealth) {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        coins = new ArrayList<>();
        blackKing = new BlackKing();
        whiteKing = new WhiteKing(whiteHealth);
    }

    public ArrayList<WhitePiece> getWhitePieces() {
		return whitePieces;
	}
    public void addWhitePiece(WhitePiece whitePiece) {
        whitePieces.add(whitePiece);
    }
    public void removeWhitePiece(WhitePiece whitePiece) {
        whitePieces.remove(whitePiece);
    }
	public ArrayList<BlackPiece> getBlackPieces() {
		return blackPieces;
	}
    public void addBlackPiece(BlackPiece blackPiece) {
        blackPieces.add(blackPiece);
    }
    public void removeBlackPiece(BlackPiece blackPiece) {
        blackPieces.remove(blackPiece);
    }
    public void killBlackPiece(BlackPiece blackPiece) {
        for (CoinTile coin: coins) {
            if (coin.getX() == blackPiece.getX() && coin.getY() == blackPiece.getY()) {
                coin.addCoins(blackPiece.getCoinDropNb());
                blackPieces.remove(blackPiece);
                return;
            }
        }
        coins.add(new CoinTile(blackPiece.getX(), blackPiece.getY(), blackPiece.getCoinDropNb()));
        blackPieces.remove(blackPiece);
    }
    public ArrayList<CoinTile> getCoins() {
        return coins;
    }
    public void removeCoins(CoinTile coinTile) {
        coins.remove(coinTile);
    }
	public BlackKing getBlackKing() {
		return blackKing;
	}
	public void setBlackKing(BlackKing blackKing) {
		this.blackKing = blackKing;
	}
	public WhiteKing getWhiteKing() {
		return whiteKing;
	}
	public void setWhiteKing(WhiteKing whiteKing) {
		this.whiteKing = whiteKing;
	}

    public boolean isInBound(Integer x, Integer y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }

    public boolean isEmpty(Integer x, Integer y) {
        if (!isInBound(x, y))
            return false;
        if (blackKing.getX() == x && blackKing.getY() == y)
            return false;
        for (BlackPiece blackPiece: blackPieces) {
            if (blackPiece.getX() == x && blackPiece.getY() == y)
                return false;
        }
        for (WhitePiece whitePiece: whitePieces) {
            if (whitePiece.getX() == x && whitePiece.getY() == y)
                return false;
        }
        return true;
    }

    public boolean isWhite(Integer x, Integer y) {
        for (WhitePiece whitePiece: whitePieces) {
            if (whitePiece.getX() == x && whitePiece.getY() == y)
                return true;
        }
        return false;
    }

    public void damageBlackAtPos(Integer x, Integer y, Float damage) {
        ArrayList<BlackPiece> deadList = new ArrayList<>();
        for (BlackPiece blackPiece: blackPieces) {
            if (blackPiece.getX() == x && blackPiece.getY() == y) {
                blackPiece.damage(damage);
                if (blackPiece.getHealth() <= 0)
                    deadList.add(blackPiece);
            }
        }
        for (BlackPiece blackPiece: deadList) {
            killBlackPiece(blackPiece);
        }
    }

    public void damageBlackPiece(BlackPiece blackPiece, Float damage) {
        blackPiece.damage(damage);
        if (blackPiece.getHealth() <= 0)
            killBlackPiece(blackPiece);
    }

    public void poisonBlackAtPos(Integer x, Integer y, Float poisonDamage, Integer nbTurns) {
        for (BlackPiece blackPiece: blackPieces) {
            if (blackPiece.getX() == x && blackPiece.getY() == y) {
                blackPiece.poison(poisonDamage, nbTurns);
            }
        }
    }

    public void queenBoostWhiteAtPos(Integer x, Integer y, Float queenBoost) {
        for (WhitePiece whitePiece: whitePieces) {
            if (!whitePiece.getPieceType().equals("WhiteQueen") && whitePiece.getX() == x && whitePiece.getY() == y) {
                whitePiece.addQueenBoost(queenBoost);
            }
        }
    }
}
