package kingdefense.frontend.ui;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.backend.Game;
import kingdefense.backend.pieces.BlackPiece;

public class WaveBox {
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    StartWaveButton startWaveButton;

    public WaveBox() {
        x = GetScreenWidth() / 20;
        y = GetScreenHeight() / 6;
        width = GetScreenWidth() / 7;
        height = GetScreenHeight() - y * 4 / 3;
        startWaveButton = new StartWaveButton(x + 20, y + 20, width - 40, 40);
    }

    public void checkStartWave(Game game) {
        startWaveButton.activate(game);
    }

    private void drawWavePieces(ArrayList<BlackPiece> blackPieces) {
        for (int i = 0; i < 15 && i < blackPieces.size(); i++) {
            DrawText(blackPieces.get(i).getPieceType(), x + 20, y + 75 + 40 * i, 35, RAYWHITE);
        }
    }

    public void draw(Game game, Color menusBackgroundColor, Color startWaveButtonColor) {
        DrawRectangle(x, y, width, height, menusBackgroundColor);
        startWaveButton.draw(startWaveButtonColor);
        drawWavePieces(game.getBoard().getBlackKing().getStockPieces());
    }
}
