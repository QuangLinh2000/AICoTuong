package model;

import java.util.ArrayList;
import java.util.List;

public class CANNON extends Chess{

	public CANNON(int[] tableChess) {
		super(tableChess);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<Location> generateMoves(int i, int color, int sourcePiece) {
		List<Location> list = new ArrayList<Location>();
		for (int j = 0; j < DI_THANG.length; j++) {
			int targetSquare = i + DI_THANG[j];
			int jumpOVer = 0;
			while(tableChess[targetSquare] != TableChess.OUT) {
				int targetPiece = tableChess[targetSquare];
				
				
				if(targetPiece != TableChess.EMPTY &&  targetPiece != TableChess.OUT) {
					jumpOVer++;
				}
				if(jumpOVer == 0) {
					if( TableChess.PIECE_COLOR[tableChess[targetSquare]] != color) {
						Location location = new Location(sourcePiece, tableChess[targetSquare], i, targetSquare);
						list.add(location);					}
				}
				if((targetPiece != TableChess.EMPTY|| targetPiece != TableChess.OUT)
						&&TableChess.PIECE_COLOR[targetPiece] != TableChess.NO_COLOR
						&& TableChess.PIECE_COLOR[targetPiece] != color 
						&&  jumpOVer == 2) {
					Location location = new Location(sourcePiece, tableChess[targetSquare], i, targetSquare);
					list.add(location);
					break;

				}
				targetSquare += DI_THANG[j];
			}
		}
	
		return list;
	}

}
