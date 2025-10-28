package kingdefense.backend.logic;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;
import kingdefense.backend.pieces.WhitePiece;

public class WhiteLogic {
    public static void activatePieces(Board board, Game game) {
        for (WhitePiece whitePiece: board.getWhitePieces()) {
            whitePiece.activate(board, game);
        }
    }
}
