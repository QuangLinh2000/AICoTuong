package model;

import java.util.ArrayList;
import java.util.List;

public class ADVISOR extends Chess{

	public ADVISOR(int[] tableChess) {
		super(tableChess);
	}

	@Override
	protected List<Location> generateMoves(int i, int color, int sourcePiece) {
		List<Location> list = new ArrayList<Location>();
		for (int j = 0; j < DI_THANG.length; j++) {
			

			int targetSquare = i+ DI_CHEO[j];
			
			if(TableChess.BOARD_ZONES[color][targetSquare] == 2 
					&& TableChess.PIECE_COLOR[tableChess[targetSquare]] != color) {
				Location location = new Location(sourcePiece, tableChess[targetSquare], i, targetSquare);
				list.add(location);
			}
		}
		return list;
	}

}
