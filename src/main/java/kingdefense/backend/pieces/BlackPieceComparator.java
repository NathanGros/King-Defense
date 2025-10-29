package kingdefense.backend.pieces;

import java.util.Comparator;

public class BlackPieceComparator implements Comparator<BlackPiece> {
	@Override
	public int compare(BlackPiece piece1, BlackPiece piece2) {
        int priorityDiff = piece2.getPriority() - piece1.getPriority();
        if (priorityDiff != 0)
            return priorityDiff;
        return piece1.getShortestPathLength() - piece2.getShortestPathLength();
	}
}
