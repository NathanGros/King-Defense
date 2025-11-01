package kingdefense.backend.logic;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;
import kingdefense.backend.pieces.WhitePiece;

public class WhiteLogic {
    public static void activatePieces(Board board, Game game) {
        // Set Queen boosts
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            whitePiece.setQueenBoost(0.f);
        }
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            if (whitePiece.getPieceType().equals("WhiteQueen")) {
                whitePiece.activate(board, game);
            }
        }
        // Activate all pieces
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            if (!whitePiece.getPieceType().equals("WhiteQueen")) {
                whitePiece.activate(board, game);
            }
        }
    }
}
