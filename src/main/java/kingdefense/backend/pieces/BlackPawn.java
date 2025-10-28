package kingdefense.backend.pieces;

public class BlackPawn extends BlackPiece {
    public BlackPawn(Integer x, Integer y, Integer health, Integer attack) {
        super(x, y, health, attack);
    }

    public String getPieceType() {
        return "BlackPawn";
    }
}
