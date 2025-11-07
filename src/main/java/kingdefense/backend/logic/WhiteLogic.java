package kingdefense.backend.logic;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;
import kingdefense.backend.pieces.*;

public class WhiteLogic {
    public static void activatePieces(Board board, Game game) {
        // Reset Queen boosts and nerfs
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            whitePiece.setQueenBoost(0.f);
            whitePiece.setQueenNerf(0.f);
        }
        // Set Queen boosts
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            if (whitePiece.getPieceType().equals("WhiteQueen")) {
                whitePiece.activate(board, game);
            }
        }
        // Set Queen nerfs
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            whitePiece.setQueenNerfs(board);
        }
        // Activate all pieces
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            if (!whitePiece.getPieceType().equals("WhiteQueen")) {
                whitePiece.activate(board, game);
            }
        }
    }
}
