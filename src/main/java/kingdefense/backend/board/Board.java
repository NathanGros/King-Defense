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
            if (coin.isSamePlace(blackPiece.getTile())) {
                coin.addCoins(blackPiece.getCoinDropNb());
                blackPieces.remove(blackPiece);
                return;
            }
        }
        coins.add(new CoinTile(blackPiece.getTile(), blackPiece.getCoinDropNb()));
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

    public boolean isInBound(Tile tile) {
        return (tile.getX() >= 0 && tile.getX() < 8 && tile.getY() >= 0 && tile.getY() < 8);
    }

    public boolean isEmpty(Tile tile) {
        if (!isInBound(tile))
            return false;
        if (blackKing.getTile().isSamePlace(tile))
            return false;
        for (BlackPiece blackPiece: blackPieces) {
            if (blackPiece.getTile().isSamePlace(tile))
                return false;
        }
        for (WhitePiece whitePiece: whitePieces) {
            if (whitePiece.getTile().isSamePlace(tile))
                return false;
        }
        return true;
    }

    public boolean isWhite(Tile tile) {
        for (WhitePiece whitePiece: whitePieces) {
            if (whitePiece.getTile().isSamePlace(tile))
                return true;
        }
        return false;
    }

    public void damageBlackAtPos(Tile tile, Float damage) {
        ArrayList<BlackPiece> deadList = new ArrayList<>();
        for (BlackPiece blackPiece: blackPieces) {
            if (blackPiece.getTile().isSamePlace(tile)) {
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

    public void shieldDamageBlackAtPos(Tile tile, Float damage) {
        ArrayList<BlackPiece> deadList = new ArrayList<>();
        for (BlackPiece blackPiece: blackPieces) {
            if (blackPiece.getTile().isSamePlace(tile)) {
                blackPiece.damageShield(damage);
                if (blackPiece.getHealth() <= 0)
                    deadList.add(blackPiece);
            }
        }
        for (BlackPiece blackPiece: deadList) {
            killBlackPiece(blackPiece);
        }
    }

    public void poisonBlackAtPos(Tile tile, Float poisonDamage, Integer nbTurns) {
        for (BlackPiece blackPiece: blackPieces) {
            if (blackPiece.getTile().isSamePlace(tile)) {
                blackPiece.poison(poisonDamage, nbTurns);
            }
        }
    }

    public void queenBoostWhiteAtPos(Tile tile, Float queenBoost) {
        for (WhitePiece whitePiece: whitePieces) {
            if (!whitePiece.getPieceType().equals("WhiteQueen") && whitePiece.getTile().isSamePlace(tile)) {
                whitePiece.addQueenBoost(queenBoost);
            }
        }
    }
}
