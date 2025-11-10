package kingdefense.frontend.game;

import java.util.ArrayList;

import static com.raylib.Raylib.*;

import kingdefense.backend.Game;
import kingdefense.backend.board.CoinTile;
import kingdefense.backend.board.Tile;
import kingdefense.frontend.ui.AvailablePiecesBox;
import kingdefense.frontend.ui.TextButton;
import kingdefense.frontend.ui.WaveBox;

public class GameInputManager {
    public GameInputManager() {
    }

    // GPT generated, no clue what's going on but it works
    public boolean checkCollisionPointTile(Vector2 point, ArrayList<Vector2> corners) {
        if (corners == null || corners.size() < 3)
            return false;
        boolean sign = false;
        int count = corners.size();
        for (int i = 0; i < count; i++) {
            Vector2 a = corners.get(i);
            Vector2 b = corners.get((i + 1) % count);
            // Cross product to check which side of the edge the point is on
            float cross = (point.x() - a.x()) * (b.y() - a.y()) -
                          (point.y() - a.y()) * (b.x() - a.x());
            // On first iteration, record the sign (positive/negative)
            if (i == 0)
                sign = cross > 0;
            else if ((cross > 0) != sign)
                return false; // Point is on a different side => outside polygon
        }
        return true;
    }

    public void collectCoins(Game game, Camera3D camera) {
        ArrayList<CoinTile> removedCoinTiles = new ArrayList<>();
        for (CoinTile coinTile: game.getBoard().getCoins()) {
            ArrayList<Vector2> corners = new ArrayList<>();
            corners.add(GetWorldToScreen(new Vector3().x(coinTile.getX() - 0.5f).y(0.f).z(coinTile.getY() - 0.5f), camera));
            corners.add(GetWorldToScreen(new Vector3().x(coinTile.getX() - 0.5f).y(0.f).z(coinTile.getY() + 0.5f), camera));
            corners.add(GetWorldToScreen(new Vector3().x(coinTile.getX() + 0.5f).y(0.f).z(coinTile.getY() + 0.5f), camera));
            corners.add(GetWorldToScreen(new Vector3().x(coinTile.getX() + 0.5f).y(0.f).z(coinTile.getY() - 0.5f), camera));
            if (checkCollisionPointTile(GetMousePosition(), corners)) {
                game.addCoins(coinTile.getNbCoins());
                removedCoinTiles.add(coinTile);
            }
        }
        for (CoinTile coinTile: removedCoinTiles) {
            game.getBoard().removeCoins(coinTile);
        }
    }

    public void checkPutWhitePiece(Game game, Camera3D camera) {
        if (!IsMouseButtonPressed(MOUSE_BUTTON_LEFT))
            return;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ArrayList<Vector2> corners = new ArrayList<>();
                corners.add(GetWorldToScreen(new Vector3().x(i - 0.5f).y(0.f).z(j - 0.5f), camera));
                corners.add(GetWorldToScreen(new Vector3().x(i - 0.5f).y(0.f).z(j + 0.5f), camera));
                corners.add(GetWorldToScreen(new Vector3().x(i + 0.5f).y(0.f).z(j + 0.5f), camera));
                corners.add(GetWorldToScreen(new Vector3().x(i + 0.5f).y(0.f).z(j - 0.5f), camera));
                if (checkCollisionPointTile(GetMousePosition(), corners)) {
                    game.putNewWhitePiece(new Tile(i, j));
                }
            }
        }
    }

    public void checkRemoveWhitePiece(Game game, Camera3D camera) {
        if (!IsMouseButtonPressed(MOUSE_BUTTON_RIGHT))
            return;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ArrayList<Vector2> corners = new ArrayList<>();
                corners.add(GetWorldToScreen(new Vector3().x(i - 0.5f).y(0.f).z(j - 0.5f), camera));
                corners.add(GetWorldToScreen(new Vector3().x(i - 0.5f).y(0.f).z(j + 0.5f), camera));
                corners.add(GetWorldToScreen(new Vector3().x(i + 0.5f).y(0.f).z(j + 0.5f), camera));
                corners.add(GetWorldToScreen(new Vector3().x(i + 0.5f).y(0.f).z(j - 0.5f), camera));
                if (checkCollisionPointTile(GetMousePosition(), corners)) {
                    game.removeWhitePiece(new Tile(i, j));
                }
            }
        }
    }

    public void checkWaveBoxScroll(WaveBox waveBox) {
        if (!waveBox.isInBounds(GetMousePosition()))
            return;
        float wheelMove = GetMouseWheelMoveV().y();
        if (wheelMove == 0.0)
            return;
        if (GetMouseWheelMoveV().y() < 0)
            waveBox.scrollDown();
        else
            waveBox.scrollUp();
    }

    private void checkStopGame(Game game, TextButton exitButton) {
        if (exitButton.isClicked())
            game.stopGame();
    }

    public void checkNotWaveInputs(Game game, Camera3D camera, AvailablePiecesBox availablePiecesBox, WaveBox waveBox) {
        checkPutWhitePiece(game, camera);
        checkRemoveWhitePiece(game, camera);
        availablePiecesBox.checkWhitePieceChange(game);
        waveBox.checkStartWave(game);
    }

    public void checkAllTimeInputs(Game game, Camera3D camera, WaveBox waveBox, TextButton exitButton) {
        checkWaveBoxScroll(waveBox);
        collectCoins(game, camera);
        checkStopGame(game, exitButton);
    }
}
