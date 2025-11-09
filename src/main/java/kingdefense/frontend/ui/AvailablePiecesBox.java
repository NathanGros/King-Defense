package kingdefense.frontend.ui;

import static com.raylib.Raylib.*;

import java.util.ArrayList;
import java.util.HashMap;

import kingdefense.backend.Game;
import kingdefense.backend.pieces.*;
import kingdefense.frontend.Colors;
import kingdefense.frontend.ModelsManager;
import kingdefense.frontend.PieceRenderer;
import kingdefense.frontend.ShadersManager;

public class AvailablePiecesBox {
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;
    private ArrayList<WhitePieceButton> whitePieceButtons;

    public AvailablePiecesBox(ModelsManager modelsManager, ShadersManager shadersManager, PieceRenderer pieceRenderer) {
        y = GetScreenHeight() / 6;
        height = GetScreenHeight() - y * 4 / 3;
        width = GetScreenWidth() / 6;
        x = GetScreenWidth() * 19 / 20 - width;
        this.whitePieceButtons = new ArrayList<>();
        initWhitePieceButtons();
        for (WhitePieceButton whitePieceButton: whitePieceButtons) {
            whitePieceButton.setModelRenderTexture(
                pieceRenderer.renderPiece(
                    modelsManager.getPieceModel(whitePieceButton.getName()),
                    width / 2,
                    height / 3,
                    shadersManager.getShaders(),
                    shadersManager.getShadowMap(),
                    1.f
                )
            );
        }
    }

    private void initWhitePieceButtons() {
        int pieceButtonWidth = width / 2;
        int pieceButtonHeight = height / 3;
        whitePieceButtons.add(new WhitePieceButton(
            x,
            y,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhiteKing"
        ));
        whitePieceButtons.add(new WhitePieceButton(
            x + pieceButtonWidth,
            y,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhiteQueen"
        ));
        whitePieceButtons.add(new WhitePieceButton(
            x,
            y + pieceButtonHeight,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhiteRook"
        ));
        whitePieceButtons.add(new WhitePieceButton(
            x + pieceButtonWidth,
            y + pieceButtonHeight,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhiteBishop"
        ));
        whitePieceButtons.add(new WhitePieceButton(
            x,
            y + 2 * pieceButtonHeight,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhiteKnight"
        ));
        whitePieceButtons.add(new WhitePieceButton(
            x + pieceButtonWidth,
            y + 2 * pieceButtonHeight,
            pieceButtonWidth,
            pieceButtonHeight,
            "WhitePawn"
        ));
    }

    public void unload() {
        for (WhitePieceButton whitePieceButton: whitePieceButtons) {
            whitePieceButton.unload();
        }
    }

    public void checkWhitePieceChange(Game game) {
        for (WhitePieceButton whitePieceButton: whitePieceButtons) {
            whitePieceButton.activate(game);
        }
    }

    public void draw(WhiteKing whiteKing, ArrayList<WhitePiece> availableWhitePieces) {
        HashMap<String, Integer> pieceCount = new HashMap<>();
        pieceCount.put("WhiteKing", 0);
        pieceCount.put("WhiteQueen", 0);
        pieceCount.put("WhiteRook", 0);
        pieceCount.put("WhiteBishop", 0);
        pieceCount.put("WhiteKnight", 0);
        pieceCount.put("WhitePawn", 0);
        if (whiteKing.isPlaced())
            pieceCount.put("WhiteKing", 0);
        else
            pieceCount.put("WhiteKing", 1);
        for (WhitePiece whitePiece: availableWhitePieces) {
            pieceCount.put(whitePiece.getPieceType(), pieceCount.get(whitePiece.getPieceType()) + 1);
        }
        DrawRectangle(x, y, width, height, Colors.buttonBackgroundColor);
        for (WhitePieceButton pieceButton: whitePieceButtons) {
            pieceButton.draw(
                false,
                pieceCount.get(pieceButton.getName())
            );
        }
    }
}
