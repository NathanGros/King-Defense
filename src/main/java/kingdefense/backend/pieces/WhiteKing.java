package kingdefense.backend.pieces;

import kingdefense.backend.board.Board;

public class WhiteKing extends WhitePiece {
    public WhiteKing(Integer x, Integer y) {
        super(x, y);
    }

    public String getPieceType() {
        return "WhiteKing";
    }
    
    public void activate(Board board) {
        System.out.println("White King activated");
    }
}
