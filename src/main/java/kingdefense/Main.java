package kingdefense;

import kingdefense.backend.board.Board;
import kingdefense.backend.logic.BlackLogic;
import kingdefense.backend.logic.WhiteLogic;
import kingdefense.backend.pieces.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.addWhitePiece(new WhiteKing(2, 3, 1));
        board.addBlackPiece(new BlackPawn(0, 1, 1, 1));
        board.printState();
        BlackLogic.movePieces(board);
        board.printState();
        WhiteLogic.activatePieces(board);
        board.printState();
        BlackLogic.movePieces(board);
        board.printState();
        WhiteLogic.activatePieces(board);
        board.printState();
        BlackLogic.movePieces(board);
        board.printState();
        WhiteLogic.activatePieces(board);
        board.printState();
        BlackLogic.movePieces(board);
        board.printState();
        WhiteLogic.activatePieces(board);
        board.printState();
        BlackLogic.movePieces(board);
        board.printState();
        WhiteLogic.activatePieces(board);
        board.printState();
    }
}
