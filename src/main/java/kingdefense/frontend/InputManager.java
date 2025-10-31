package kingdefense.frontend;

import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.backend.Game;
import kingdefense.backend.board.CoinTile;

public class InputManager {
    public InputManager() {
    }

    public void checkMovement(CameraManager cameraManager) {
        if (IsKeyPressed(KEY_R)) {
            cameraManager.rotate();
        }
        if (GetMouseWheelMove() == -1) {
            cameraManager.zoomOut();
        }
        if (GetMouseWheelMove() == 1) {
            cameraManager.zoomIn();
        }
    }

    public void checkTurn(Game game) {
        if (IsKeyPressed(KEY_SPACE)) {
            game.startWave();
        }
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
        Vector2 mousePos = new Vector2().x(GetMouseX()).y(GetMouseY());
        ArrayList<CoinTile> removedCoinTiles = new ArrayList<>();
        for (CoinTile coinTile: game.getBoard().getCoins()) {
            ArrayList<Vector2> corners = new ArrayList<>();
            corners.add(GetWorldToScreen(new Vector3().x(coinTile.getX() - 0.5f).y(0.f).z(coinTile.getY() - 0.5f), camera));
            corners.add(GetWorldToScreen(new Vector3().x(coinTile.getX() - 0.5f).y(0.f).z(coinTile.getY() + 0.5f), camera));
            corners.add(GetWorldToScreen(new Vector3().x(coinTile.getX() + 0.5f).y(0.f).z(coinTile.getY() + 0.5f), camera));
            corners.add(GetWorldToScreen(new Vector3().x(coinTile.getX() + 0.5f).y(0.f).z(coinTile.getY() - 0.5f), camera));
            if (checkCollisionPointTile(mousePos, corners)) {
                game.addCoins(coinTile.getNbCoin());
                removedCoinTiles.add(coinTile);
            }
        }
        for (CoinTile coinTile: removedCoinTiles) {
            game.getBoard().removeCoins(coinTile);
        }
    }
}
