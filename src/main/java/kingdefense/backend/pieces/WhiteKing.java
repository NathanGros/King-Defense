package kingdefense.backend.pieces;

public class WhiteKing extends WhitePiece {
    public WhiteKing(Integer x, Integer y) {
        super(x, y);
    }

    @Override
    public String getPieceType() {
        return "WhiteKing";
    }
    
    public void activate() {
        System.out.println("White King activated");
    }
}
