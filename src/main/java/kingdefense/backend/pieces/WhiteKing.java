package kingdefense.backend.pieces;

import java.util.ArrayList;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;

public class WhiteKing extends WhitePiece {
    private Integer health;

    public WhiteKing(Integer x, Integer y, Integer health) {
        super(x, y);
        this.health = health;
    }

	@Override
    public String getPieceType() {
        return "WhiteKing";
    }

    public boolean isAdjacent(BlackPiece blackPiece) {
        Integer x = blackPiece.getX();
        Integer y = blackPiece.getY();
        if (this.x == x + 1 && this.y == y)
            return true;
        if (this.x == x - 1 && this.y == y)
            return true;
        if (this.x == x && this.y == y + 1)
            return true;
        if (this.x == x && this.y == y - 1)
            return true;
        return false;
    }
    
	@Override
    public void activate(Board board, Game game) {
        ArrayList<BlackPiece> toRemove = new ArrayList<>();
        for (BlackPiece blackPiece: board.getBlackPieces()) {
            if (isAdjacent(blackPiece)) {
                health -= blackPiece.getAttack();
                toRemove.add(blackPiece);
            }
        }
        for (BlackPiece blackPiece: toRemove) {
            board.removeBlackPiece(blackPiece);
        }
        if (health <= 0) {
            health = 0;
            game.stopGame();
        }
    }
}
