package kingdefense.frontend;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

public class PieceButton {
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private String name;

	public PieceButton(Integer x, Integer y, Integer width, Integer height, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
    }

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

    public void drawPiece(Integer nbAvailable) {
        int textHeight = 40;
        DrawText(name.substring(5), x + width / 2 - textHeight * 2 / 3, y + height - 4 * textHeight, textHeight / 2, RAYWHITE);
        DrawText("x" + nbAvailable.toString(), x + width / 2 - textHeight / 2, y + height - 2 * textHeight, textHeight, RAYWHITE);
    }

    public boolean isClicked() {
        if (!IsMouseButtonPressed(MOUSE_BUTTON_LEFT))
            return false;
        return CheckCollisionPointRec(GetMousePosition(), new Rectangle().x(x).y(y).width(width).height(height));
    }
}
