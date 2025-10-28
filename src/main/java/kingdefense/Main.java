package kingdefense;

import kingdefense.backend.board.Board;
import kingdefense.backend.pieces.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.addWhitePiece(new WhiteKing(7, 7));
        board.addBlackPiece(new BlackPawn(0, 1, 1, 1));
        board.printState();
    }
}
