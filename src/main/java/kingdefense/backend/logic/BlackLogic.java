package kingdefense.backend.logic;

import java.util.ArrayList;

import kingdefense.backend.Game;
import kingdefense.backend.board.*;
import kingdefense.backend.pieces.*;
import kingdefense.frontend.ui.WaveBox;

public class BlackLogic {
    public static void play(Game game, Board board, WaveBox frontWaveBox) {
        putNewPiece(board, frontWaveBox);
        movePieces(board);
        applyPoison(board);
        checkWaveEnd(game, board);
    }

	// While not (everything has moved OR no piece can move)
    //    For each piece that hasnt moved
    //    calculate shortest path
    //    sort them by decreasing piece power and increasing shortest path 
    //    move first piece of list
    public static void movePieces(Board board) {
        for (BlackPiece blackPiece: board.getBlackPieces()) {
            blackPiece.setHasMoved(false);
        }
        while (true) {
            ArrayList<BlackPiece> moveList = new ArrayList<>();
            for (BlackPiece blackPiece: board.getBlackPieces()) {
                if (blackPiece.hasMoved())
                    continue;
                if (!blackPiece.canMove(board))
                    continue;
                blackPiece.computeShortestPath(board);
                if (blackPiece.getShortestPathLength() != -1)
                    moveList.add(blackPiece);
            }
            if (moveList.isEmpty()) break;
            moveList.sort(new BlackPieceComparator());
            BlackPiece pieceToMove = moveList.getFirst();
            WhiteKing whiteKing = board.getWhiteKing();
            if (pieceToMove.getTargetTile().isSamePlace(whiteKing.getTile())) {
                whiteKing.damage(board, pieceToMove);
            }
            else
                pieceToMove.move();
        }
    }

    public static void putNewPiece(Board board, WaveBox frontWaveBox) {
        // No more pieces to place
        if (board.getBlackKing().getStockPieces().isEmpty()) {
            return;
        }
        // The place is blocked
        for (BlackPiece blackPiece: board.getBlackPieces()) {
            if (blackPiece.getTile().isSamePlace(board.getBlackKing().getTile()))
                return;
        }
        // Place next piece at kings position
        BlackPiece nextPiece = board.getBlackKing().popStockPiece(frontWaveBox);
        nextPiece.setTile(new Tile(board.getBlackKing().getTile()));
        board.addBlackPiece(nextPiece);
    }

    public static void applyPoison(Board board) {
        ArrayList<BlackPiece> deadList = new ArrayList<>();
        for (BlackPiece blackPiece: board.getBlackPieces()) {
            for (int i = 0; i < blackPiece.getPoisonDamageList().size(); i++) {
                blackPiece.damage(blackPiece.getPoisonDamageList().get(i));
                blackPiece.getPoisonTurnsLeftList().set(i, blackPiece.getPoisonTurnsLeftList().get(i) - 1);
            }
            for (int i = 0; i < blackPiece.getPoisonTurnsLeftList().size(); i++) {
                if (blackPiece.getPoisonTurnsLeftList().get(i) <= 0) {
                    blackPiece.getPoisonTurnsLeftList().remove(i);
                    blackPiece.getPoisonDamageList().remove(i);
                }
            }
            if (blackPiece.getHealth() <= 0)
                deadList.add(blackPiece);
        }
        for (BlackPiece blackPiece: deadList) {
            board.killBlackPiece(blackPiece);
        }
    }

    private static void checkWaveEnd(Game game, Board board) {
        if (board.getBlackPieces().isEmpty() && board.getBlackKing().getStockPieces().isEmpty()) {
            game.stopWave();
        }
	}
}
