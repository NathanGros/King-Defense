package kingdefense.frontend;

import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.backend.Game;
import kingdefense.frontend.ui.*;

public class WindowManager {
    CameraManager cameraManager;
    DrawingManager drawingManager;
    InputManager inputManager;
    AvailablePiecesBox availablePiecesBox;
    WaveBox waveBox;

    public WindowManager() {
        cameraManager = new CameraManager();
        drawingManager = new DrawingManager();
        inputManager = new InputManager();
    }

    private void initUiElements() {
        availablePiecesBox = new AvailablePiecesBox();
        waveBox = new WaveBox();
    }

    public void launchWindow() {
        SetConfigFlags(FLAG_WINDOW_RESIZABLE);
        SetConfigFlags(FLAG_FULLSCREEN_MODE);
        SetConfigFlags(FLAG_MSAA_4X_HINT);
        InitWindow(0, 0, "King Defense");
        SetTargetFPS(60);
        initUiElements();
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
            inputManager.checkInputs(game, cameraManager.getCamera(), availablePiecesBox, waveBox);
        }
        inputManager.collectCoins(game, cameraManager.getCamera());
        drawingManager.drawGame(game, cameraManager, availablePiecesBox, waveBox);
    }
}
