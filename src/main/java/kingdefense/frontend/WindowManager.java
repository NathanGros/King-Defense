package kingdefense.frontend;

import static com.raylib.Raylib.*;

import kingdefense.backend.Game;

public class WindowManager {
    CameraManager cameraManager;
    DrawingManager drawingManager;
    InputManager inputManager;

    public WindowManager() {
        cameraManager = new CameraManager();
        drawingManager = new DrawingManager();
        inputManager = new InputManager();
    }

    public void launchWindow() {
        SetConfigFlags(FLAG_WINDOW_RESIZABLE);
        SetConfigFlags(FLAG_FULLSCREEN_MODE);
        SetConfigFlags(FLAG_MSAA_4X_HINT);
        InitWindow(0, 0, "King Defense");
        SetTargetFPS(60);
    }

    public void closeWindow() {
        CloseWindow();
    }

    public void windowInteract(Game game) {
        if (WindowShouldClose()) {
            game.stopGame();
            return;
        }
        inputManager.checkCameraMovement(cameraManager);
        if (!game.isInWave()) {
            inputManager.checkPutWhitePiece(game, cameraManager.getCamera());
            inputManager.checkRemoveWhitePiece(game, cameraManager.getCamera());
            inputManager.checkTriggerWave(game);
        }
        inputManager.collectCoins(game, cameraManager.getCamera());
        drawingManager.drawGame(game, cameraManager);
    }
}
