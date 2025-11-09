package kingdefense.frontend.ui;

import static com.raylib.Raylib.*;

public abstract class Button {
    protected Integer x;
    protected Integer y;
    protected Integer width;
    protected Integer height;

    public Button(Integer x, Integer y, Integer width, Integer height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}

    public boolean isClicked() {
        if (!IsMouseButtonPressed(MOUSE_BUTTON_LEFT))
            return false;
        return CheckCollisionPointRec(GetMousePosition(), new Rectangle().x(x).y(y).width(width).height(height));
    }
}
