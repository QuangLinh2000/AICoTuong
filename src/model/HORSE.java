package model;

import java.util.ArrayList;
import java.util.List;

public class HORSE extends Chess{
	//OFFSETS cua con Ma            
		public static final int[][] HORSE_MOVE_OFFSETS = {
				{LEFT+LEFT+UP,LEFT+LEFT+DOWN},
				{RIGHT+RIGHT+UP,RIGHT+RIGHT+DOWN},
				{UP+UP+LEFT,UP+UP+RIGHT},
				{DOWN+DOWN+LEFT,DOWN+DOWN+RIGHT}
				
		};    
	public HORSE(int[] tableChess) {
		super(tableChess);
	}

	@Override
	protected List<Location> generateMoves(int i, int color, int sourcePiece) {
		List<Location> list = new ArrayList<Location>();

		for (int j = 0; j < DI_THANG.length; j++) {
			int targetDirection = i+DI_THANG[j];
			if(tableChess[targetDirection] == TableChess.EMPTY) {
				for (int offset = 0; offset < 2; offset++) {
					int targetSquare = i +HORSE_MOVE_OFFSETS[j][offset];
					int targetPiece = tableChess[targetSquare];
					if(targetPiece !=TableChess.OUT && TableChess.PIECE_COLOR[tableChess[targetSquare]] != color) {
						Location location = new Location(sourcePiece, tableChess[targetSquare], i, targetSquare);
						list.add(location);
					}

				}
			}
		}
		return list;
	}

}
