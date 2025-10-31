package kingdefense.backend.board;

import java.util.ArrayList;
import kingdefense.backend.pieces.*;

public class Board {
    private ArrayList<WhitePiece> whitePieces;
    private ArrayList<BlackPiece> blackPieces;
    private BlackKing blackKing;
    private WhiteKing whiteKing;

    public Board(BlackKing blackKing, WhiteKing whiteKing) {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        this.blackKing = blackKing;
        this.whiteKing = whiteKing;
    }
    public Board() {
        this(new BlackKing(0, 0, 1), new WhiteKing(7, 7, 20));
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
            blackPieces.remove(blackPiece);
        }
    }

    public void damageBlackPiece(BlackPiece blackPiece, Float damage) {
        blackPiece.damage(damage);
        if (blackPiece.getHealth() <= 0)
            blackPieces.remove(blackPiece);
    }

    public void poisonBlackAtPos(Integer x, Integer y, Float poisonDamage, Integer nbTurns) {
        for (BlackPiece blackPiece: blackPieces) {
            if (blackPiece.getX() == x && blackPiece.getY() == y) {
                blackPiece.poison(poisonDamage, nbTurns);
            }
        }
    }

    public void printState() {
        System.out.println("---------- Board ----------");
        System.out.println(blackKing);
        for (BlackPiece blackPiece: blackPieces) {
            System.out.println(blackPiece);
        }
        System.out.println(whiteKing);
        for (WhitePiece whitePiece: whitePieces) {
            System.out.println(whitePiece);
        }
        System.out.println();
    }
}
