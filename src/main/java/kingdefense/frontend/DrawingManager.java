package kingdefense.frontend;

import static com.raylib.Colors.*;
import static com.raylib.Raylib.*;

import java.util.ArrayList;

import kingdefense.backend.board.Board;
import kingdefense.backend.pieces.*;

public class DrawingManager {
    private Color whiteColor = new Color().r((byte) 240).g((byte) 240).b((byte) 240).a((byte) 255);
    private Color blackColor = new Color().r((byte) 50).g((byte) 50).b((byte) 50).a((byte) 255);
    public DrawingManager() {}

    public void drawBoard(Board board) {
        drawChessBoard();
        drawWhitePieces(board.getWhitePieces());
        drawBlackPieces(board.getBlackPieces());
        drawWhiteKing(board.getWhiteKing());
        drawBlackKing(board.getBlackKing());
    }

    private void drawChessBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color color;
                if ((i + j) % 2 == 0)
                    color = WHITE;
                else
                    color = BLACK;
                DrawCube(new Vector3().x(i).y(0).z(j), 1.f, 0.2f, 1.f, color);
            }
        }
    }

    private void drawPiece(Float x, Float y, Float z, Float height, Color color) {
        DrawCylinder(new Vector3().x(x).y(y).z(z), 0.4f, 0.5f, height, 10, color);
        DrawCylinderWires(new Vector3().x(x).y(y).z(z), 0.4f, 0.5f, height, 10, BLACK);
    }

	private void drawWhiteKing(WhiteKing whiteKing) {
        drawPiece((float) whiteKing.getX(), 0.0f, (float) whiteKing.getY(), 3.0f, whiteColor);
	}

	private void drawBlackKing(BlackKing blackKing) {
        drawPiece((float) blackKing.getX(), 0.0f, (float) blackKing.getY(), 3.0f, blackColor);
	}

	private void drawWhitePieces(ArrayList<WhitePiece> whitePieces) {
        for (WhitePiece whitePiece: whitePieces) {
            drawPiece((float) whitePiece.getX(), 0.0f, (float) whitePiece.getY(), 1.0f, whiteColor);
        }
	}

	private void drawBlackPieces(ArrayList<BlackPiece> blackPieces) {
        for (BlackPiece blackPiece: blackPieces) {
            drawPiece((float) blackPiece.getX(), 0.0f, (float) blackPiece.getY(), 1.0f, blackColor);
        }
	}
}
