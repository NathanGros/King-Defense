package kingdefense.backend.pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class BlackPawn extends BlackPiece {
    public BlackPawn(Integer x, Integer y, Float health, Integer attack) {
        super(x, y, health, attack);
        this.priority = 1;
        this.attainableNb = 4;
        this.attainableX = new ArrayList<>(Arrays.asList(1, -1, 0, 0));
        this.attainableY = new ArrayList<>(Arrays.asList(0, 0, 1, -1));
    }
    public BlackPawn(Float health, Integer attack) {
        this(0, 0, health, attack);
    }
    public BlackPawn() {
        this(0, 0, 1.f, 1);
    }

    @Override
    public String getPieceType() {
        return "BlackPawn";
    }
}
