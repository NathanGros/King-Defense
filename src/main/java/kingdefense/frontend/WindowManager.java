package kingdefense.frontend;

import static com.raylib.Raylib.*;

import kingdefense.backend.Game;
import kingdefense.frontend.ui.*;

public class WindowManager {
    private CameraManager cameraManager;
    private DrawingManager drawingManager;
    private InputManager inputManager;
    private AvailablePiecesBox availablePiecesBox;
    private WaveBox waveBox;
	private PieceRenderer pieceRenderer;

    public WindowManager() {
        cameraManager = new CameraManager();
        drawingManager = new DrawingManager();
        inputManager = new InputManager();
    }

    private void initUiElements() {
        availablePiecesBox = new AvailablePiecesBox(drawingManager.getModelsManager(), drawingManager.getShadersManager(), pieceRenderer);
        waveBox = new WaveBox(drawingManager.getModelsManager(), drawingManager.getShadersManager(), pieceRenderer);
    }

    public void launchWindow() {
        SetConfigFlags(FLAG_WINDOW_RESIZABLE);
        SetConfigFlags(FLAG_FULLSCREEN_MODE);
        // SetConfigFlags(FLAG_MSAA_4X_HINT);
        InitWindow(0, 0, "King Defense");
        SetTargetFPS(60);
        drawingManager.loadModels();
        pieceRenderer = new PieceRenderer();
        initUiElements();
    }

    public void closeWindow() {
        drawingManager.unloadModels();
        availablePiecesBox.unload();
        waveBox.unloadAll();
        CloseWindow();
    }

    public WaveBox getWaveBox() {
		return waveBox;
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
