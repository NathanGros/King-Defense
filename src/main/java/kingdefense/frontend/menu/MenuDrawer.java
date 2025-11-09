package kingdefense.frontend.menu;

import static com.raylib.Raylib.BeginDrawing;
import static com.raylib.Raylib.ClearBackground;
import static com.raylib.Raylib.EndDrawing;

import java.util.ArrayList;

import kingdefense.frontend.Colors;
import kingdefense.frontend.ui.TextButton;

public class MenuDrawer {
    public MenuDrawer() {
    }

    public void draw(ArrayList<TextButton> menuButtons) {
        BeginDrawing();
        ClearBackground(Colors.backgroundColor);
        for (TextButton button: menuButtons) {
            button.draw();
        }
        EndDrawing();
    }
}
