package kingdefense.frontend;

import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.backend.Game;

public class WindowManager {
    CameraManager cameraManager;
    DrawingManager drawingManager;
    InputManager inputManager;
    ArrayList<PieceButton> pieceButtons;

    public WindowManager() {
        cameraManager = new CameraManager();
        drawingManager = new DrawingManager();
        inputManager = new InputManager();
        pieceButtons = new ArrayList<>();
    }

    public void launchWindow() {
        SetConfigFlags(FLAG_WINDOW_RESIZABLE);
        SetConfigFlags(FLAG_FULLSCREEN_MODE);
        SetConfigFlags(FLAG_MSAA_4X_HINT);
        InitWindow(0, 0, "King Defense");
        SetTargetFPS(60);
        int availableStartY = GetScreenHeight() / 6;
        int availableHeight = GetScreenHeight() - availableStartY * 4 / 3;
        int availableWidth = GetScreenWidth() / 6;
        int availableStartX = GetScreenWidth() * 19 / 20 - availableWidth;
        int pieceButtonWidth = availableWidth / 2;
        int pieceButtonHeight = availableHeight / 3;
        pieceButtons.add(new PieceButton(
            availableStartX,
            availableStartY,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhiteKing"
        ));
        pieceButtons.add(new PieceButton(
            availableStartX + pieceButtonWidth,
            availableStartY,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhiteQueen"
        ));
        pieceButtons.add(new PieceButton(
            availableStartX,
            availableStartY + pieceButtonHeight,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhiteRook"
        ));
        pieceButtons.add(new PieceButton(
            availableStartX + pieceButtonWidth,
            availableStartY + pieceButtonHeight,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhiteBishop"
        ));
        pieceButtons.add(new PieceButton(
            availableStartX,
            availableStartY + 2 * pieceButtonHeight,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhiteKnight"
        ));
        pieceButtons.add(new PieceButton(
            availableStartX + pieceButtonWidth,
            availableStartY + 2 * pieceButtonHeight,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhitePawn"
        ));
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
            inputManager.checkWhitePieceChange(game, pieceButtons);
            inputManager.checkTriggerWave(game);
        }
        inputManager.collectCoins(game, cameraManager.getCamera());
        drawingManager.drawGame(game, cameraManager, pieceButtons);
    }
}
