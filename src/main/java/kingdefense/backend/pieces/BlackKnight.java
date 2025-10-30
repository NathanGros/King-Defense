package kingdefense.backend.pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class BlackKnight extends BlackPiece {
    public BlackKnight(Integer x, Integer y, Integer health, Integer attack) {
        super(x, y, health, attack);
        this.priority = 1;
        this.accessibleNb = 12;
        this.accessibleX = new ArrayList<>(Arrays.asList(2, 2, -2, -2, 1, -1, 1, -1, 1, -1, 0, 0));
        this.accessibleY = new ArrayList<>(Arrays.asList(1, -1, 1, -1, 2, 2, -2, -2, 0, 0, 1, -1));
    }
    public BlackKnight(Integer health, Integer attack) {
        this(0, 0, health, attack);
    }
    public BlackKnight() {
        this(0, 0, 3, 3);
    }

	@Override
	public String getPieceType() {
        return "BlackKnight";
	}
}
