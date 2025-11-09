package kingdefense.frontend.ui;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import kingdefense.backend.Game;

public class BlackPieceButton extends Button {
    private String name;
    private RenderTexture modelRenderTexture;

	public BlackPieceButton(Integer x, Integer y, Integer width, Integer height, String name) {
		super(x, y, width, height);
        this.name = name;
	}

    public String getName() {
		return name;
	}
    public void setModelRenderTexture(RenderTexture modelRenderTexture) {
        this.modelRenderTexture = modelRenderTexture;
    }

    public void draw(boolean isHovered) {
        Texture modelTexture = modelRenderTexture.texture();
        Rectangle sourceRec = new Rectangle()
            .x(0)
            .y(0)
            .width(modelTexture.width())
            .height(-modelTexture.height()); // Flip vertically (render texture is upside down)
        Rectangle destRec = new Rectangle()
            .x(x)
            .y(y)
            .width(width)
            .height(height);
        DrawTexturePro(modelTexture, sourceRec, destRec, new Vector2().x(0).y(0), 0, WHITE);
    }

    public void unload() {
        UnloadRenderTexture(modelRenderTexture);
    }

	public void activate(Game game) {
		// TODO ennemy info
	}
    
}
