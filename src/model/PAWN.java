package model;

import java.util.ArrayList;
import java.util.List;

public class PAWN extends Chess {

	public PAWN(int[] tableChess) {
		super(tableChess);
	}

	public static final int[][] PAWN_MOVE_OFFSETS =
			// cách đi cho con đỏ
			{ { UP, LEFT, RIGHT },
					// cách đi cho con đen
					{ DOWN, LEFT, RIGHT } };

	// sourcePiece la chi so cua con chess co gia tri tu 0 -> 15
	@Override
	protected List<Location> generateMoves(int i, int color, int sourcePiece) {
		List<Location> list = new ArrayList<Location>();
		for (int j = 0; j < PAWN_MOVE_OFFSETS[color].length; j++) {
			int targetSquare = i + PAWN_MOVE_OFFSETS[color][j];
			if (tableChess[targetSquare] != TableChess.OUT &&  TableChess.PIECE_COLOR[tableChess[targetSquare]] != color ) {
				Location location = new Location(sourcePiece, tableChess[targetSquare], i, targetSquare);
				list.add(location);

			}
			//neu chess chua qua sông thì nó chỉ đi UP hoăc DOWN  
			if (TableChess.BOARD_ZONES[color][i] != 0)
				break;

		}
		return list;
	}

}
