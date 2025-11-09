package kingdefense.frontend.menu;

import static com.raylib.Raylib.BeginDrawing;
import static com.raylib.Raylib.ClearBackground;
import static com.raylib.Raylib.EndDrawing;
import static com.raylib.Raylib.Color;

import java.util.ArrayList;

import kingdefense.frontend.ui.TextButton;

public class MenuDrawer {
    private Color backgroundColor = new Color().r((byte) 51).g((byte) 51).b((byte) 51).a((byte) 255);
    private Color buttonBackgroundColor = new Color().r((byte) 37).g((byte) 37).b((byte) 37).a((byte) 255);

    public MenuDrawer() {
        backgroundColor = new Color().r((byte) 51).g((byte) 51).b((byte) 51).a((byte) 255);
        buttonBackgroundColor = new Color().r((byte) 37).g((byte) 37).b((byte) 37).a((byte) 255);
    }

    public void draw(ArrayList<TextButton> menuButtons) {
        BeginDrawing();
        ClearBackground(backgroundColor);
        for (TextButton button: menuButtons) {
            button.draw(buttonBackgroundColor);
        }
        EndDrawing();
    }
}
