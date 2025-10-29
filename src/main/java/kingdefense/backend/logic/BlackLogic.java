package kingdefense.backend.logic;

import java.util.ArrayList;

import kingdefense.backend.board.*;
import kingdefense.backend.pieces.BlackPiece;
import kingdefense.backend.pieces.BlackPieceComparator;

public class BlackLogic {
    public static void play(Board board) {
        movePieces(board);
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
                moveList.add(blackPiece);
            }
            if (moveList.isEmpty()) break;
            moveList.sort(new BlackPieceComparator());
            moveList.getFirst().move();
        }
    }
}
