package model;

import java.util.ArrayList;
import java.util.List;

public class ELEPHANT extends Chess{
	//OFFSETS CU CUA CON TUONG(VOI)
		public static final int[] ELEPHANT_MOVE_OFFSETS = {(UP+LEFT)*2,(UP+RIGHT)*2,(DOWN+LEFT)*2,(DOWN+RIGHT)*2};
		
	public ELEPHANT(int[] tableChess) {
		super(tableChess);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<Location> generateMoves(int i, int color, int sourcePiece) {
		List<Location> list = new ArrayList<Location>();

		for (int j = 0; j < ELEPHANT_MOVE_OFFSETS.length; j++) {
			int targetSquare = i + ELEPHANT_MOVE_OFFSETS[j];
			int jumpOVer = i +DI_CHEO[j];
	
			if((TableChess.BOARD_ZONES[color][targetSquare] == 1||TableChess.BOARD_ZONES[color][targetSquare] == 2)
					&& tableChess[jumpOVer] == TableChess.EMPTY
					&& TableChess.PIECE_COLOR[tableChess[targetSquare]] != color ) {
				Location location = new Location(sourcePiece, tableChess[targetSquare], i, targetSquare);
				list.add(location);
			}
		}
		return list;
	}
	

}
