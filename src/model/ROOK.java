package model;

import java.util.ArrayList;
import java.util.List;

public class ROOK extends Chess {

	public ROOK(int[] tableChess) {
		super(tableChess);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected List<Location> generateMoves(int i, int color, int sourcePiece) {
		List<Location> list = new ArrayList<Location>();

			for (int j = 0; j < DI_THANG.length; j++) {
				int targetSquare = i + DI_THANG[j];
				int jumpOVer = 0;
//				System.out.println(j);
				while(tableChess[targetSquare] != TableChess.OUT) {
					int targetPiece = tableChess[targetSquare];
					if(jumpOVer == 0) {
						if( TableChess.PIECE_COLOR[tableChess[targetSquare]] != color) {
							Location location = new Location(sourcePiece, targetPiece, i, targetSquare);
							list.add(location);
						}
					}else {
						break;
					}
					
					if(targetPiece != TableChess.EMPTY) jumpOVer++;
					
					targetSquare += DI_THANG[j];
				}
			}
		
		return list;
	}

}
