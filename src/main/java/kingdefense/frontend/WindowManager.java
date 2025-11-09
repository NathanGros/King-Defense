package kingdefense.frontend;

import static com.raylib.Raylib.*;

public class WindowManager {
    public WindowManager() {
    }

    public void launchWindow() {
        SetConfigFlags(FLAG_WINDOW_RESIZABLE);
        SetConfigFlags(FLAG_FULLSCREEN_MODE);
        // SetConfigFlags(FLAG_MSAA_4X_HINT);
        InitWindow(0, 0, "King Defense");
        SetTargetFPS(60);
    }

    public void closeWindow() {
        CloseWindow();
    }
}
