package kingdefense.frontend;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.ArrayList;

import org.bytedeco.javacpp.IntPointer;

import kingdefense.backend.Game;
import kingdefense.backend.board.*;
import kingdefense.backend.pieces.*;
import kingdefense.frontend.ui.*;

public class DrawingManager {
    private Color backgroundColor;
    private Color menusBackgroundColor;
    private Color healthColor;
    private ModelsManager modelsManager;
	private ShadersManager shadersManager;

	public DrawingManager() {
        backgroundColor = new Color().r((byte) 51).g((byte) 51).b((byte) 51).a((byte) 255);
        menusBackgroundColor = new Color().r((byte) 37).g((byte) 37).b((byte) 37).a((byte) 255);
        healthColor = new Color().r((byte) 195).g((byte) 88).b((byte) 51).a((byte) 255);
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
            WHITE
        );
    }

    private void drawCoins(ArrayList<CoinTile> coins) {
        for (CoinTile coin: coins) {
            DrawModel(
                modelsManager.getCoinsModel(coin.getNbCoins()),
                new Vector3().x(coin.getX()).y(0.f).z(coin.getY()),
                1.f,
                WHITE
            );
        }
    }

	private void drawWhiteKing(WhiteKing whiteKing) {
        if (whiteKing.isPlaced())
            DrawModel(
                modelsManager.getPieceModel("WhiteKing"),
                new Vector3().x(whiteKing.getX()).y(0.f).z(whiteKing.getY()),
                1.f,
                WHITE
            );
	}

	private void drawBlackKing(BlackKing blackKing) {
        DrawModel(
            modelsManager.getPieceModel("BlackKing"),
            new Vector3().x(blackKing.getX()).y(0.f).z(blackKing.getY()),
            1.f,
            WHITE
        );
	}

	private void drawWhitePieces(ArrayList<WhitePiece> whitePieces) {
        for (WhitePiece whitePiece: whitePieces) {
            DrawModel(
                modelsManager.getPieceModel(whitePiece.getPieceType()),
                new Vector3().x(whitePiece.getX()).y(0.f).z(whitePiece.getY()),
                1.f,
                WHITE
            );
        }
	}

	private void drawBlackPieces(ArrayList<BlackPiece> blackPieces) {
        for (BlackPiece blackPiece: blackPieces) {
            DrawModel(
                modelsManager.getPieceModel(blackPiece.getPieceType()),
                new Vector3().x(blackPiece.getX()).y(0.f).z(blackPiece.getY()),
                1.f,
                WHITE
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
        DrawRectangle(healthBarStartX, healthBarStartY, healthBarWidth, healthBarHeight, menusBackgroundColor);
        DrawRectangle(healthBarStartX, healthBarStartY, healthWidth, healthBarHeight, healthColor);
    }

    private void drawEarnedCoins(Integer coins) {
        DrawText(coins.toString(), GetScreenWidth() * 18 / 20, GetScreenHeight() / 13, 30, YELLOW);
    }

    public void drawGame(Game game, CameraManager cameraManager, AvailablePiecesBox availablePiecesBox, WaveBox waveBox) {
        SetShaderValue(shadersManager.getShaders(), shadersManager.getShaders().locs().get(SHADER_LOC_VECTOR_VIEW), cameraManager.getCamera()._position(), SHADER_UNIFORM_VEC3);
        BeginDrawing();
            Matrix lightView;
            Matrix lightProj;
            BeginTextureMode(shadersManager.getShadowMap());
                ClearBackground(WHITE);
                BeginMode3D(shadersManager.getLightCamera());
                    lightView = rlGetMatrixModelview();
                    lightProj = rlGetMatrixProjection();
                    drawBoard(game.getBoard());
                EndMode3D();
            EndTextureMode();
            Matrix lightViewProj = MatrixMultiply(lightView, lightProj);

            ClearBackground(backgroundColor);
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
            waveBox.draw(game, menusBackgroundColor, healthColor);
            availablePiecesBox.draw(game.getBoard().getWhiteKing(), game.getAvailableWhitePieces(), menusBackgroundColor);
            drawEarnedCoins(game.getNbCoins());
        EndDrawing();
    }
}
