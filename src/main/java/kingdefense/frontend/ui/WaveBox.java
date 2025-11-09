package kingdefense.frontend.ui;

import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.backend.Game;
import kingdefense.backend.pieces.BlackPiece;
import kingdefense.frontend.Colors;
import kingdefense.frontend.ModelsManager;
import kingdefense.frontend.PieceRenderer;
import kingdefense.frontend.ShadersManager;

public class WaveBox {
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private Integer waveGridStartY;
    private Integer waveGridNbX;
    private Integer waveGridNbY;
    private Integer scrollOffset;
    StartWaveButton startWaveButton;
    ArrayList<BlackPieceButton> blackPieceButtons;
    private ShadersManager shadersManager;
    private ModelsManager modelsManager;
    private PieceRenderer pieceRenderer;

    public WaveBox(ModelsManager modelsManager, ShadersManager shadersManager, PieceRenderer pieceRenderer) {
        x = GetScreenWidth() / 20;
        y = GetScreenHeight() / 6;
        width = GetScreenWidth() / 7;
        height = GetScreenHeight() - y * 4 / 3;
        startWaveButton = new StartWaveButton(x + 20, y + 20, width - 40, 40);
        waveGridStartY = 80;
        waveGridNbX = 5;
        waveGridNbY = 10;
        scrollOffset = 0;
        blackPieceButtons = new ArrayList<>();
        this.modelsManager = modelsManager;
        this.shadersManager = shadersManager;
        this.pieceRenderer = pieceRenderer;
    }

    public void checkStartWave(Game game) {
        startWaveButton.activate(game);
    }

    public void unloadAll() {
        for (BlackPieceButton blackPieceButton: blackPieceButtons) {
            blackPieceButton.unload();
        }
    }

    private void updateWavePiecesPosition() {
        if (blackPieceButtons.size() - waveGridNbX * scrollOffset <= waveGridNbX * waveGridNbY - waveGridNbX)
            scrollOffset--;
        if (blackPieceButtons.size() <= waveGridNbX * waveGridNbY)
            scrollOffset = 0;
        int pieceButtonWidth = width / waveGridNbX;
        int pieceButtonHeight = (height - waveGridStartY) / waveGridNbY;
        for (int i = 0; i < blackPieceButtons.size(); i++) {
            BlackPieceButton blackPieceButton = blackPieceButtons.get(i);
            blackPieceButton.setX(x + (i % waveGridNbX ) * pieceButtonWidth);
            blackPieceButton.setY(y + waveGridStartY - scrollOffset * pieceButtonHeight + (i / waveGridNbX) * pieceButtonHeight);
        }
    }

    public void removeFirst() {
        blackPieceButtons.getFirst().unload();
        blackPieceButtons.removeFirst();
        updateWavePiecesPosition();
    }

    public void addBlackPiece(BlackPiece blackPiece) {
        int position = blackPieceButtons.size();
        int pieceButtonWidth = width / waveGridNbX;
        int pieceButtonHeight = (height - waveGridStartY) / waveGridNbY;
        BlackPieceButton blackPieceButton = new BlackPieceButton(
            x + (position % waveGridNbX ) * pieceButtonWidth,
            y + waveGridStartY - scrollOffset * pieceButtonHeight + (position / waveGridNbX) * pieceButtonHeight,
            pieceButtonWidth,
            pieceButtonHeight,
            blackPiece.getPieceType()
        );
        blackPieceButton.setModelRenderTexture(
            pieceRenderer.renderPiece(
                modelsManager.getPieceModel(blackPieceButton.getName()),
                pieceButtonWidth,
                pieceButtonHeight,
                shadersManager.getShaders(),
                shadersManager.getShadowMap(),
                0.5f
            )
        );
        blackPieceButtons.add(blackPieceButton);
    }

    public void scrollUp() {
        scrollOffset--;
        if (scrollOffset < 0)
            scrollOffset = 0;
        updateWavePiecesPosition();
    }

    public void scrollDown() {
        if (blackPieceButtons.size() <= waveGridNbX * waveGridNbY)
            scrollOffset = 0;
        else {
            if (blackPieceButtons.size() - waveGridNbX * scrollOffset > waveGridNbX * waveGridNbY)
                scrollOffset++;
        }
        updateWavePiecesPosition();
    }

    public boolean isInBounds(Vector2 pos) {
        return pos.x() > x && pos.x() < x + width && pos.y() > y && pos.y() < y + height;
    }

    private void drawWavePieces(ArrayList<BlackPiece> blackPieces) {
        for (int i = 0; i < blackPieceButtons.size(); i++) {
            if (i - scrollOffset * waveGridNbX < 0)
                continue;
            if (i - scrollOffset * waveGridNbX >= waveGridNbX * waveGridNbY)
                continue;
            blackPieceButtons.get(i).draw(false);
        }
    }

    public void draw(Game game) {
        DrawRectangle(x, y, width, height, Colors.buttonBackgroundColor);
        startWaveButton.draw();
        drawWavePieces(game.getBoard().getBlackKing().getStockPieces());
    }
}
