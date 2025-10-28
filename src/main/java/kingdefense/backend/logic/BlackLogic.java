package kingdefense.backend.logic;

import kingdefense.backend.board.*;
import kingdefense.backend.pieces.BlackPiece;
import kingdefense.backend.pieces.WhitePiece;

public class BlackLogic {
    public static void movePieces(Board board) {
        for (BlackPiece blackPiece: board.getBlackPieces()) {
            Integer pieceX = blackPiece.getX();
            Integer pieceY = blackPiece.getY();
            int choosenDirection = (int)(Math.random() * 4);
            switch (choosenDirection) {
                case 0:
                    pieceX++;
                    break;
                case 1:
                    pieceY++;
                    break;
                case 2:
                    pieceX--;
                    break;
                default:
                    pieceY--;
                    break;
            }
            // Out of board
            if (pieceX < 0 || pieceX > 7 || pieceY < 0 || pieceY > 7)
                return;
            // Black king
            if (pieceX == board.getBlackKing().getX() && pieceY == board.getBlackKing().getY())
                return;
            // Black piece
            for (BlackPiece blackOtherPiece: board.getBlackPieces()) {
                if (pieceX == blackOtherPiece.getX() && pieceY == blackOtherPiece.getY())
                    return;
            }
            // White piece
            for (WhitePiece whiteOtherPiece: board.getWhitePieces()) {
                if (pieceX == whiteOtherPiece.getX() && pieceY == whiteOtherPiece.getY())
                    return;
            }
            // Move Piece
            blackPiece.move(pieceX, pieceY);
        }
    }
}
