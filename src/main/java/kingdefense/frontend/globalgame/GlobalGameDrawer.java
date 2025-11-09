package kingdefense.frontend.globalgame;

import static com.raylib.Raylib.BeginDrawing;
import static com.raylib.Raylib.ClearBackground;
import static com.raylib.Raylib.EndDrawing;

import java.util.ArrayList;

import kingdefense.frontend.Colors;
import kingdefense.frontend.ui.TextButton;

public class GlobalGameDrawer {
    public GlobalGameDrawer() {
    }

    public void draw(ArrayList<TextButton> globalGameButtons) {
        BeginDrawing();
        ClearBackground(Colors.backgroundColor);
        for (TextButton button: globalGameButtons) {
            button.draw();
        }
        EndDrawing();
    }
}
