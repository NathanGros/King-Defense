package kingdefense.frontend.game;

import static com.raylib.Raylib.*;

import java.util.ArrayList;

import org.bytedeco.javacpp.IntPointer;

import kingdefense.backend.Game;
import kingdefense.backend.board.Board;
import kingdefense.backend.board.CoinTile;
import kingdefense.backend.pieces.*;
import kingdefense.frontend.CameraManager;
import kingdefense.frontend.Colors;
import kingdefense.frontend.ModelsManager;
import kingdefense.frontend.ShadersManager;
import kingdefense.frontend.ui.AvailablePiecesBox;
import kingdefense.frontend.ui.TextButton;
import kingdefense.frontend.ui.WaveBox;

public class GameDrawer {
    private ModelsManager modelsManager;
	private ShadersManager shadersManager;

	public GameDrawer() {
        modelsManager = new ModelsManager();
        shadersManager = new ShadersManager();
    }

    public ModelsManager getModelsManager() {
		return modelsManager;
	}
	public ShadersManager getShadersManager() {
		return shadersManager;
	}

    public void loadModels() {
        shadersManager.initShaders();
        modelsManager.loadModels(shadersManager.getShaders());
	}

    public void unloadModels() {
        modelsManager.unloadModels();
        shadersManager.unloadShaders();
    }

    private void drawChessBoard() {
        DrawModel(
            modelsManager.getChessBoardModel(),
            new Vector3().x(3.5f).y(0.f).z(3.5f),
            8.f,
            Colors.white
        );
    }

    private void drawCoins(ArrayList<CoinTile> coins) {
        for (CoinTile coin: coins) {
            DrawModel(
                modelsManager.getCoinsModel(coin.getNbCoins()),
                new Vector3().x(coin.getX()).y(0.f).z(coin.getY()),
                1.f,
                Colors.white
            );
        }
    }

	private void drawWhiteKing(WhiteKing whiteKing) {
        if (whiteKing.isPlaced())
            DrawModel(
                modelsManager.getPieceModel("WhiteKing"),
                new Vector3().x(whiteKing.getTile().getX()).y(0.f).z(whiteKing.getTile().getY()),
                1.f,
                Colors.white
            );
	}

	private void drawBlackKing(BlackKing blackKing) {
        DrawModel(
            modelsManager.getPieceModel("BlackKing"),
            new Vector3().x(blackKing.getTile().getX()).y(0.f).z(blackKing.getTile().getY()),
            1.f,
            Colors.white
        );
	}

	private void drawWhitePieces(ArrayList<WhitePiece> whitePieces) {
        for (WhitePiece whitePiece: whitePieces) {
            DrawModel(
                modelsManager.getPieceModel(whitePiece.getPieceType()),
                new Vector3().x(whitePiece.getTile().getX()).y(0.f).z(whitePiece.getTile().getY()),
                1.f,
                Colors.white
            );
        }
	}

	private void drawBlackPieces(ArrayList<BlackPiece> blackPieces) {
        for (BlackPiece blackPiece: blackPieces) {
            DrawModel(
                modelsManager.getPieceModel(blackPiece.getPieceType()),
                new Vector3().x(blackPiece.getTile().getX()).y(0.f).z(blackPiece.getTile().getY()),
                1.f,
                Colors.white
            );
        }
	}

    private void drawBoard(Board board) {
        drawChessBoard();
        drawCoins(board.getCoins());
        drawWhitePieces(board.getWhitePieces());
        drawBlackPieces(board.getBlackPieces());
        drawWhiteKing(board.getWhiteKing());
        drawBlackKing(board.getBlackKing());
    }

    private void drawHealth(WhiteKing whiteKing) {
        int healthBarStartX = GetScreenWidth() / 4 + 100;
        int healthBarStartY = GetScreenHeight() / 13;
        int healthBarWidth = GetScreenWidth() - 2 * healthBarStartX;
        int healthBarHeight = GetScreenHeight() / 50;
        int healthWidth = healthBarWidth * whiteKing.getHealth() / whiteKing.getMaxHealth();
        DrawRectangle(healthBarStartX, healthBarStartY, healthBarWidth, healthBarHeight, Colors.buttonBackgroundColor);
        DrawRectangle(healthBarStartX, healthBarStartY, healthWidth, healthBarHeight, Colors.red);
    }

    private void drawEarnedCoins(Integer coins) {
        DrawText(coins.toString(), GetScreenWidth() * 18 / 20, GetScreenHeight() / 13, 30, Colors.yellow);
    }

    public void drawGame(Game game, CameraManager cameraManager, AvailablePiecesBox availablePiecesBox, WaveBox waveBox, TextButton exitButton) {
        SetShaderValue(shadersManager.getShaders(), shadersManager.getShaders().locs().get(SHADER_LOC_VECTOR_VIEW), cameraManager.getCamera()._position(), SHADER_UNIFORM_VEC3);
        BeginDrawing();
            // Draw board
            Matrix lightView;
            Matrix lightProj;
            BeginTextureMode(shadersManager.getShadowMap());
                ClearBackground(Colors.white);
                BeginMode3D(shadersManager.getLightCamera());
                    lightView = rlGetMatrixModelview();
                    lightProj = rlGetMatrixProjection();
                    drawBoard(game.getBoard());
                EndMode3D();
            EndTextureMode();
            Matrix lightViewProj = MatrixMultiply(lightView, lightProj);

            ClearBackground(Colors.backgroundColor);
            SetShaderValueMatrix(shadersManager.getShaders(), shadersManager.getLightVPLoc(), lightViewProj);
            rlEnableShader(shadersManager.getShaders().id());
            IntPointer slot = new IntPointer(1); // Can be anything 0 to 15, but 0 will probably be taken up
            slot.put(0, 10);
            rlActiveTextureSlot(10);
            rlEnableTexture(shadersManager.getShadowMap().depth().id());
            rlSetUniform(shadersManager.getShadowMapLoc(), slot, SHADER_UNIFORM_INT, 1);

            BeginMode3D(cameraManager.getCamera());
                drawBoard(game.getBoard());
            EndMode3D();

            // Draw UI
            drawHealth(game.getBoard().getWhiteKing());
            waveBox.draw(game);
            availablePiecesBox.draw(game.getBoard().getWhiteKing(), game.getAvailableWhitePieces());
            drawEarnedCoins(game.getNbCoins());
            exitButton.draw();
        EndDrawing();
    }
}
