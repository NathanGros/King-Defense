package kingdefense.backend.pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class BlackPawn extends BlackPiece {
    public BlackPawn(Integer x, Integer y, Integer health, Integer attack) {
        super(x, y, health, attack);
        this.priority = 1;
        this.accessibleNb = 4;
        this.accessibleX = new ArrayList<>(Arrays.asList(1, -1, 0, 0));
        this.accessibleY = new ArrayList<>(Arrays.asList(0, 0, 1, -1));
    }
    public BlackPawn(Integer health, Integer attack) {
        this(0, 0, health, attack);
    }
    public BlackPawn() {
        this(0, 0, 1, 1);
    }

    @Override
    public String getPieceType() {
        return "BlackPawn";
    }
}
