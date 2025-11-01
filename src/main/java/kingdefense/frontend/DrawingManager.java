package kingdefense.frontend;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.ArrayList;
import java.util.HashMap;

import kingdefense.backend.Game;
import kingdefense.backend.board.*;
import kingdefense.backend.pieces.*;

public class DrawingManager {
    private Color backgroundColor = new Color().r((byte) 51).g((byte) 51).b((byte) 51).a((byte) 255);
    private Color menusBackgroundColor = new Color().r((byte) 37).g((byte) 37).b((byte) 37).a((byte) 255);
    private Color boardColor = new Color().r((byte) 133).g((byte) 88).b((byte) 50).a((byte) 255);
    private Color blackTileColor = new Color().r((byte) 146).g((byte) 99).b((byte) 54).a((byte) 255);
    private Color whiteTileColor = new Color().r((byte) 204).g((byte) 166).b((byte) 110).a((byte) 255);
    private Color healthColor = new Color().r((byte) 195).g((byte) 88).b((byte) 51).a((byte) 255);
    private Color blackPieceColor = new Color().r((byte) 72).g((byte) 65).b((byte) 59).a((byte) 255);
    private Color whitePieceColor = new Color().r((byte) 222).g((byte) 193).b((byte) 146).a((byte) 255);
    public DrawingManager() {}

    private void drawChessBoard() {
        DrawCube(new Vector3().x(3.5f).y(0).z(3.5f), 9.f, 0.15f, 9.f, boardColor);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color color;
                if ((i + j) % 2 == 0)
                    color = whiteTileColor;
                else
                    color = blackTileColor;
                DrawCube(new Vector3().x(i).y(0).z(j), 1.f, 0.2f, 1.f, color);
            }
        }
    }

    private void drawCoins(ArrayList<CoinTile> coins) {
        for (CoinTile coin: coins) {
            for (int i = 0; i < coin.getNbCoin(); i++) {
                DrawCube(new Vector3().x(coin.getX()).y(0.2f + 0.3f * i).z(coin.getY()), 0.3f, 0.2f, 0.3f, YELLOW);
                DrawCubeWires(new Vector3().x(coin.getX()).y(0.2f + 0.3f * i).z(coin.getY()), 0.3f, 0.2f, 0.3f, BLACK);
            }
        }
    }

    private void drawPiece(Float x, Float y, Float z, Float height, Color color) {
        DrawCylinder(new Vector3().x(x).y(y).z(z), 0.4f, 0.5f, height, 10, color);
        DrawCylinderWires(new Vector3().x(x).y(y).z(z), 0.4f, 0.5f, height, 10, BLACK);
    }

	private void drawWhiteKing(WhiteKing whiteKing) {
        drawPiece((float) whiteKing.getX(), 0.0f, (float) whiteKing.getY(), 3.0f, whitePieceColor);
	}

	private void drawBlackKing(BlackKing blackKing) {
        drawPiece((float) blackKing.getX(), 0.0f, (float) blackKing.getY(), 3.0f, blackPieceColor);
	}

	private void drawWhitePieces(ArrayList<WhitePiece> whitePieces) {
        for (WhitePiece whitePiece: whitePieces) {
            drawPiece((float) whitePiece.getX(), 0.0f, (float) whitePiece.getY(), 1.0f, whitePieceColor);
        }
	}

	private void drawBlackPieces(ArrayList<BlackPiece> blackPieces) {
        for (BlackPiece blackPiece: blackPieces) {
            drawPiece((float) blackPiece.getX(), 0.0f, (float) blackPiece.getY(), 1.0f, blackPieceColor);
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

    private void drawIncomingSidebar(Board board) {
        int incomingStartX = GetScreenWidth() / 20;
        int incomingStartY = GetScreenHeight() / 6;
        int incomingWidth = GetScreenWidth() / 7;
        int incomingHeight = GetScreenHeight() - incomingStartY * 4 / 3;
        DrawRectangle(incomingStartX, incomingStartY, incomingWidth, incomingHeight, menusBackgroundColor);
    }

    private void drawAvailableSidebar(ArrayList<WhitePiece> availableWhitePieces, ArrayList<PieceButton> pieceButtons) {
        int availableStartY = GetScreenHeight() / 6;
        int availableHeight = GetScreenHeight() - availableStartY * 4 / 3;
        int availableWidth = GetScreenWidth() / 6;
        int availableStartX = GetScreenWidth() * 19 / 20 - availableWidth;
        int pieceTileWidth = availableWidth / 2;
        int pieceTileHeight = availableHeight / 3;
        HashMap<String, Integer> pieceCount = new HashMap<>();
        pieceCount.put("WhiteKing", 0);
        pieceCount.put("WhiteQueen", 0);
        pieceCount.put("WhiteRook", 0);
        pieceCount.put("WhiteBishop", 0);
        pieceCount.put("WhiteKnight", 0);
        pieceCount.put("WhitePawn", 0);
        for (WhitePiece whitePiece: availableWhitePieces) {
            pieceCount.put(whitePiece.getPieceType(), pieceCount.get(whitePiece.getPieceType()) + 1);
        }
        DrawRectangle(availableStartX, availableStartY, availableWidth, availableHeight, menusBackgroundColor);
        for (PieceButton pieceButton: pieceButtons) {
            pieceButton.drawPiece(pieceCount.get(pieceButton.getName()));
        }
    }

    public void drawGame(Game game, CameraManager cameraManager, ArrayList<PieceButton> pieceButtons) {
        BeginDrawing();
        ClearBackground(backgroundColor);
        BeginMode3D(cameraManager.getCamera());
        drawBoard(game.getBoard());
        EndMode3D();
        // Draw UI
        drawHealth(game.getBoard().getWhiteKing());
        drawIncomingSidebar(game.getBoard());
        drawAvailableSidebar(game.getAvailableWhitePieces(), pieceButtons);
        drawEarnedCoins(game.getNbCoins());
        EndDrawing();
    }
}
