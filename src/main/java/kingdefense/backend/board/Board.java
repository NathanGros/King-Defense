package kingdefense.backend.board;

import java.util.ArrayList;
import kingdefense.backend.pieces.*;

public class Board {
    private ArrayList<WhitePiece> whitePieces;
    private ArrayList<BlackPiece> blackPieces;
    private BlackKing blackKing;

    public Board(Integer blackKingX, Integer BlackKingY) {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        blackKing = new BlackKing(blackKingX, BlackKingY);
    }
    public Board() {
        this(0, 0);
    }

    public void addWhitePiece(WhitePiece whitePiece) {
        whitePieces.add(whitePiece);
    }

    public void addBlackPiece(BlackPiece blackPiece) {
        blackPieces.add(blackPiece);
    }

    public void printState() {
        System.out.println(blackKing);
        for (BlackPiece blackPiece: blackPieces) {
            System.out.println(blackPiece);
        }
        for (WhitePiece whitePiece: whitePieces) {
            System.out.println(whitePiece);
        }
    }
}
