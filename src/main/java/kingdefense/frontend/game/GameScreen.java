package kingdefense.frontend.game;

import kingdefense.backend.Game;
import kingdefense.frontend.CameraManager;
import kingdefense.frontend.PieceRenderer;
import kingdefense.frontend.ui.AvailablePiecesBox;
import kingdefense.frontend.ui.WaveBox;

public class GameScreen {
    private GameDrawer gameDrawer;
    private CameraManager cameraManager;
	private PieceRenderer pieceRenderer;
    private GameInputManager gameInputManager;
    private AvailablePiecesBox availablePiecesBox;
    private WaveBox waveBox;

    public GameScreen() {
        cameraManager = new CameraManager();
        gameDrawer = new GameDrawer();
        gameInputManager = new GameInputManager();
    }

    private void initUiElements() {
        availablePiecesBox = new AvailablePiecesBox(gameDrawer.getModelsManager(), gameDrawer.getShadersManager(), pieceRenderer);
        waveBox = new WaveBox(gameDrawer.getModelsManager(), gameDrawer.getShadersManager(), pieceRenderer);
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
        gameInputManager.checkWaveBoxScroll(waveBox);
        if (!game.isInWave()) {
            gameInputManager.checkNotWaveInputs(game, cameraManager.getCamera(), availablePiecesBox, waveBox);
        }
        gameInputManager.checkAllTimeInputs(game, cameraManager.getCamera());
    }

    public void drawScreen(Game game) {
        gameDrawer.drawGame(game, cameraManager, availablePiecesBox, waveBox);
    }
}
