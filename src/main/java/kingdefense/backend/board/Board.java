package kingdefense.backend.board;

import java.util.ArrayList;
import kingdefense.backend.pieces.*;

public class Board {
    private ArrayList<WhitePiece> whitePieces;
    private ArrayList<BlackPiece> blackPieces;
    private BlackKing blackKing;

    public Board(BlackKing blackKing) {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        this.blackKing = blackKing;
    }
    public Board() {
        this(new BlackKing(0, 0, 1));
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

    public boolean isEmpty(Integer x, Integer y) {
        if (x < 0 || x > 7 || y < 0 || y > 7)
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

    public void printState() {
        System.out.println("---------- Board ----------");
        System.out.println(blackKing);
        for (BlackPiece blackPiece: blackPieces) {
            System.out.println(blackPiece);
        }
        for (WhitePiece whitePiece: whitePieces) {
            System.out.println(whitePiece);
        }
        System.out.println();
    }
}
