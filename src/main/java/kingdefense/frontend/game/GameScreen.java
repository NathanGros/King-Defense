package kingdefense.frontend.game;

import static com.raylib.Raylib.GetScreenWidth;
import static com.raylib.Raylib.GetScreenHeight;

import kingdefense.backend.Game;
import kingdefense.frontend.CameraManager;
import kingdefense.frontend.PieceRenderer;
import kingdefense.frontend.ui.AvailablePiecesBox;
import kingdefense.frontend.ui.TextButton;
import kingdefense.frontend.ui.WaveBox;

public class GameScreen {
    private GameDrawer gameDrawer;
    private CameraManager cameraManager;
	private PieceRenderer pieceRenderer;
    private GameInputManager gameInputManager;
    private AvailablePiecesBox availablePiecesBox;
    private WaveBox waveBox;
    private TextButton exitButton;
    private TextButton shopButton;

    public GameScreen() {
        cameraManager = new CameraManager();
        gameDrawer = new GameDrawer();
        gameInputManager = new GameInputManager();
    }

    private void initUiElements() {
        availablePiecesBox = new AvailablePiecesBox(gameDrawer.getModelsManager(), gameDrawer.getShadersManager(), pieceRenderer);
        waveBox = new WaveBox(gameDrawer.getModelsManager(), gameDrawer.getShadersManager(), pieceRenderer);
        exitButton = new TextButton(
            GetScreenWidth() / 20,
            GetScreenHeight() / 24,
            GetScreenWidth() / 7,
            GetScreenHeight() / 12,
            "Back"
        );
        shopButton = new TextButton(
            GetScreenWidth() * 19 / 20 - GetScreenHeight() / 12,
            GetScreenHeight() / 24,
            GetScreenHeight() / 12,
            GetScreenHeight() / 12,
            "Shop"
        );
    }

    public void launchGameScreen() {
        gameDrawer.loadModels();
        pieceRenderer = new PieceRenderer();
        initUiElements();
    }

    public void stopGameScreen() {
        gameDrawer.unloadModels();
        availablePiecesBox.unload();
        waveBox.unloadAll();
    }

    public WaveBox getWaveBox() {
		return waveBox;
	}

    public void interactScreen(Game game) {
        if (!game.isInWave()) {
            gameInputManager.checkNotWaveInputs(game, cameraManager.getCamera(), availablePiecesBox, waveBox, shopButton);
        }
        gameInputManager.checkAllTimeInputs(game, cameraManager.getCamera(), waveBox, exitButton);
    }

    public void drawScreen(Game game) {
        gameDrawer.drawGame(game, cameraManager, availablePiecesBox, waveBox, exitButton, shopButton);
    }
}
