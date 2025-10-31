package kingdefense.frontend;

import static com.raylib.Raylib.*;

import kingdefense.backend.Game;

public class InputManager {
    public InputManager() {
    }

    public void checkMovement(CameraManager cameraManager) {
        if (IsKeyPressed(KEY_R)) {
            cameraManager.rotate();
        }
        if (GetMouseWheelMove() == -1) {
            cameraManager.zoomOut();
        }
        if (GetMouseWheelMove() == 1) {
            cameraManager.zoomIn();
        }
    }

    public void checkTurn(Game game) {
        if (IsKeyPressed(KEY_SPACE)) {
            game.startWave();
        }
    }
}
