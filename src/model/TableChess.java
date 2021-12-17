package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TableChess {
	// chua vi tri con co tron mang nameChess vi du KING_BLACK = 1
	public int[] tableChess = new int[154];
	public static final String[] nameChess = { ".", "k", "a", "e", "h", "r", "c", "p", "K", "A", "E", "H", "R", "C",
			"P", "x" };
	public static final String[] nameChess2 = { ".", "將", "士", "象", "馬", "車", "炮", "卒", "帥", "仕", "相", "傌", "俥", "炮.",
			"兵", "x" };
	private Stack<Location> saveLocations;

	// mau con co
	public static final int RED = 0;
	public static final int BLACK = 1;
	public static final int NO_COLOR = -1;

	public static final int EMPTY = 0;
	public static final int KING_BLACK = 1;
	public static final int ADVISOR_BLACK = 2; // con sĩ đen
	public static final int ELEPHANT_BLACK = 3;// con tượng đen
	public static final int HORSE_BLACK = 4; // con mã đen
	public static final int ROOK_BLACK = 5;// con xe đen
	public static final int CANNON_BLACK = 6; // con pháo đen
	public static final int PAWN_BLACK = 7; // con tốt đen

	public static final int KING_RED = 8;
	public static final int ADVISOR_RED = 9;
	public static final int ELEPHANT_RED = 10;
	public static final int HORSE_RED = 11;
	public static final int ROOK_RED = 12;
	public static final int CANNON_RED = 13;
	public static final int PAWN_RED = 14;
	// ko nam trong ban co
	public static final int OUT = 15;

	// con co chung
	public static final int KING = 16;
	public static final int ADVISOR = 17;
	public static final int ELEPHANT = 18;
	public static final int HORSE = 19;
	public static final int ROOK = 20;
	public static final int CANNON = 21;
	public static final int PAWN = 22;

	// tao do con co
	public static final int A9 = 23, B9 = 24, C9 = 25, D9 = 26, E9 = 27, F9 = 28, G9 = 29, H9 = 30, I9 = 31;
	public static final int A8 = 34, B8 = 35, C8 = 36, D8 = 37, E8 = 38, F8 = 39, G8 = 40, H8 = 41, I8 = 42;
	public static final int A7 = 45, B7 = 46, C7 = 47, D7 = 48, E7 = 49, F7 = 50, G7 = 51, H7 = 52, I7 = 53;
	public static final int A6 = 56, B6 = 57, C6 = 58, D6 = 59, E6 = 60, F6 = 61, G6 = 62, H6 = 63, I6 = 64;
	public static final int A5 = 67, B5 = 68, C5 = 69, D5 = 70, E5 = 71, F5 = 72, G5 = 73, H5 = 74, I5 = 75;
	public static final int A4 = 78, B4 = 79, C4 = 80, D4 = 81, E4 = 82, F4 = 83, G4 = 84, H4 = 85, I4 = 86;
	public static final int A3 = 89, B3 = 90, C3 = 91, D3 = 92, E3 = 93, F3 = 94, G3 = 95, H3 = 96, I3 = 97;
	public static final int A2 = 100, B2 = 101, C2 = 102, D2 = 103, E2 = 104, F2 = 105, G2 = 106, H2 = 107, I2 = 108;
	public static final int A1 = 111, B1 = 112, C1 = 113, D1 = 114, E1 = 115, F1 = 116, G1 = 117, H1 = 118, I1 = 119;
	public static final int A0 = 122, B0 = 123, C0 = 124, D0 = 125, E0 = 126, F0 = 127, G0 = 128, H0 = 129, I0 = 130;

	public static final int UP = -11;
	public static final int DOWN = 11;
	public static final int LEFT = -1;
	public static final int RIGHT = 1;

	// map con co
	public static final int[] PIECE_TYPE = { 0, KING, ADVISOR, ELEPHANT, HORSE, ROOK, CANNON, PAWN, KING, ADVISOR,
			ELEPHANT, HORSE, ROOK, CANNON, PAWN };

	// map color
	public static final int[] PIECE_COLOR = { NO_COLOR, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, RED, RED, RED,
			RED, RED, RED, RED, NO_COLOR };

	// ATTACK CUA CON TOT
	public static final int[][] PAWN_ATTACK = { { DOWN, LEFT, RIGHT }, { UP, LEFT, RIGHT } };

	public static final int[] DI_THANG = { LEFT, RIGHT, UP, DOWN };
	public static final int[] DI_CHEO = { UP + LEFT, UP + RIGHT, DOWN + LEFT, DOWN + RIGHT };

	// ATTACK CUA CON NGUA
	public static final int[][] HORSE_ATTACK = { { UP + UP + LEFT, LEFT + LEFT + UP },
			{ UP + UP + RIGHT, RIGHT + RIGHT + UP }, { DOWN + DOWN + LEFT, LEFT + LEFT + DOWN },
			{ DOWN + DOWN + RIGHT, RIGHT + RIGHT + DOWN }

	};
	
	// GIOI HAN NUOC DI 
	public static final int[][] BOARD_ZONES = {
		{   0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,
			0,1,1,1,1,1,1,1,1,1,0,// 1 nuoc cua con red
			0,1,1,1,1,1,1,1,1,1,0,
			0,1,1,1,2,2,2,1,1,1,0,//2 hoang cung king xi
			0,1,1,1,2,2,2,1,1,1,0,
			0,1,1,1,2,2,2,1,1,1,0,
			0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0
			// con red
			},{
				// con black
				0,0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0,0,
				0,1,1,1,2,2,2,1,1,1,0,
				0,1,1,1,2,2,2,1,1,1,0,
				0,1,1,1,2,2,2,1,1,1,0,
				0,1,1,1,1,1,1,1,1,1,0,
				0,1,1,1,1,1,1,1,1,1,0,
				0,0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0,0
			}
	}; 
	public static final int[] MATERIAL_WEIGHTS_BLACK= {
			 // k     A     E      H     R    C     P  // ben can set -- ben black
			0, 6000 , 120, 120 , 270, 600 , 285, 30,
			// k      A      E       H      R      C   P
			 -6000 , -120, -120 , -270, -600 , -285, -30 // be doi thu  -- ben red
	};
	public static final int[] MATERIAL_WEIGHTS_RED = {
			 // k       A     E      H     R    C     P  
			0,-6000 , -120, -120 , -270, -600 , -285, -30,  // be doi thu -- ben black
			// k    A     E   H     R     C   P
			6000 , 120, 120 , 270, 600 , 285, 30          // ben can set   -- ben red
			
			
	};
	
	int INDEXT_KING_RED = 0;
	int INDEXT_KING_BLACK = 0;
	
	public static final boolean[] EVALUATION_TYPES = {false, false, false, true, true, true, true};
	
	// diem danh cho con red
	public static final int[][] PST = {
		
			 {}, // skip kings
	        {}, // skip advisors
	        {}, // skip elephants

	        // horses
	        {
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      4,      8,     16,     12,      4,     12,     16,      8,      4,      0,
	            0,      4,     10,     28,     16,      8,     16,     28,     10,      4,      0,
	            0,     12,     14,     16,     20,     18,     20,     16,     14,     12,      0,
	            0,      8,     24,     18,     24,     20,     24,     18,     24,      8,      0,
	            0,      6,     16,     14,     18,     16,     18,     14,     16,      6,      0,
	            0,      4,     12,     16,     14,     12,     14,     16,     12,      4,      0,
	            0,      2,      6,      8,      6,     10,      6,      8,      6,      2,      0,
	            0,      4,      2,      8,      8,      4,      8,      8,      2,      4,      0,
	            0,      0,      2,      4,      4,     -2,      4,      4,      2,      0,      0,
	            0,      0,     -4,      0,      0,      0,      0,      0,     -4,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	},
	        // rooks
	        {
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,     14,     14,     12,     18,     16,     18,     12,     14,     14,      0,
	            0,     16,     20,     18,     24,     26,     24,     18,     20,     16,      0,
	            0,     12,     12,     12,     18,     18,     18,     12,     12,     12,      0,
	            0,     12,     18,     16,     22,     22,     22,     16,     18,     12,      0,
	            0,     12,     14,     12,     18,     18,     18,     12,     14,     12,      0,
	            0,     12,     16,     14,     20,     20,     20,     14,     16,     12,      0,
	            0,      6,     10,      8,     14,     14,     14,      8,     10,      6,      0,
	            0,      4,      8,      6,     14,     12,     14,      6,      8,      4,      0,
	            0,      8,      4,      8,     16,      8,     16,      8,      4,      8,      0,
	            0,     -2,     10,      6,     14,     12,     14,      6,     10,     -2,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	},
	        // cannons
	        {
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      6,      4,      0,    -10,    -12,    -10,      0,      4,      6,      0,
	            0,      2,      2,      0,     -4,    -14,     -4,      0,      2,      2,      0,
	            0,      2,      2,      0,    -10,     -8,    -10,      0,      2,      2,      0,
	            0,      0,      0,     -2,      4,     10,      4,     -2,      0,      0,      0,
	            0,      0,      0,      0,      2,      8,      2,      0,      0,      0,      0,
	            0,     -2,      0,      4,      2,      6,      2,      4,      0,     -2,      0,
	            0,      0,      0,      0,      2,      4,      2,      0,      0,      0,      0,
	            0,      4,      0,      8,      6,     10,      6,      8,      0,      4,      0,
	            0,      0,      2,      4,      6,      6,      6,      4,      2,      0,      0,
	            0,      0,      0,      2,      6,      6,      6,      2,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	},

	      
	   	 // pawns
			{
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      3,      6,      9,     12,      9,      6,      3,      0,      0,
	            0,     18,     36,     56,     80,    120,     80,     56,     36,     18,      0,
	            0,     14,     26,     42,     60,     80,     60,     42,     26,     14,      0,
	            0,     10,     20,     30,     34,     40,     34,     30,     20,     10,      0,
	            0,      6,     12,     18,     18,     20,     18,     18,     12,      6,      0,
	            0,      2,      0,      8,      0,      8,      0,      8,      0,      2,      0,
	            0,      0,      0,     -2,      0,      4,      0,     -2,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	            0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	}
	       
	};
	
	// diem danh cho con black

	public static final int[] MIRROR_SQUARE = {

	                        0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	                        0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	                        0,     A0,     B0,     C0,     D0,     E0,     F0,     G0,     H0,     I0,      0,
	                        0,     A1,     B1,     C1,     D1,     E1,     F1,     G1,     H1,     I1,      0,
	                        0,     A2,     B2,     C2,     D2,     E2,     F2,     G2,     H2,     I2,      0,
	                        0,     A3,     B3,     C3,     D3,     E3,     F3,     G3,     H3,     I3,      0,
	                        0,     A4,     B4,     C4,     D4,     E4,     F4,     G4,     H4,     I4,      0,
	                        0,     A5,     B5,     C5,     D5,     E5,     F5,     G5,     H5,     I5,      0,
	                        0,     A6,     B6,     C6,     D6,     E6,     F6,     G6,     H6,     I6,      0,
	                        0,     A7,     B7,     C7,     D7,     E7,     F7,     G7,     H7,     I7,      0,
	                        0,     A8,     B8,     C8,     D8,     E8,     F8,     G8,     H8,     I8,      0,
	                        0,     A9,     B9,     C9,     D9,     E9,     F9,     G9,     H9,     I9,      0,
	                        0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	                        0,      0,      0,      0,      0,      0,      0,      0,      0,      0,      0,
	 };

	public TableChess() {
		
		saveLocations = new Stack<Location>();
		createTableChess("rheakaehr/9/1c5c1/p1p1p1p1p/9/9/P1P1P1P1P/1C5C1/9/RHEAKAEHR");
//		createTableChess("r1ea1a3/4kh3/2h1e4/pHp1p1p1p/4c4/6P2/P1P2R2P/1CcC5/9/2EAKAE2");
//		createTableChess("1ceak4/9/h2a5/2p1p3p/5cp2/2h2H3/6PCP/3AE4/2C6/3A1K1H1");
//		createTableChess("5a3/3k5/3aR4/9/5r3/5h3/9/3A1A3/5K3/2EC2E2");
		
//		perftDrive(5, BLACK);
//         System.out.println(nodes);
		
//		System.out.println(minimaxAphaBeta(5, Integer.MIN_VALUE, Integer.MAX_VALUE, BLACK, 5));
//		System.out.println(value);
        
//		System.out.println(checkBiTanCong(A1, RED));
//		Chess chess = new CANNON(tableChess);
//		System.out.println(chess.generateMoves(B7, BLACK, CANNON_BLACK));
	}
	
	//----------------------AI ----------------------
	Location locationResult = null;
	int value = 0;
	public Location getPointerMinimax(int depth,int thuatToan) {
		locationResult = null;
		value = 0;
        
		if(thuatToan == 1) {
			minimaxAphaBeta(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, BLACK,depth);

		}else {
			minimax(depth, BLACK,depth);
		}
		
		
        return locationResult;		
	}
	
	public Stack<Location> getSaveLocations() {
		return saveLocations;
	}
	
	
	public int minimax(int depth, int color,int df) {
		if(depth == 0) {
//			int c = (color == RED?BLACK:RED);
			 return evaluate(BLACK);
		}
		List<Location> moveList = getALLMoves(color);
		if(color == BLACK) {
			int max = -Integer.MAX_VALUE;
			for (int i = 0; i < moveList.size(); i++) {
				Location location = moveList.get(i);
				if(!makeChess(location)) continue;
				int eval = minimax(depth -1, RED,df);
				
				if(eval > max ) {
					value = eval;

					if(depth == df)
						locationResult = location;

				}
				
				max = Math.max(max, eval);
           				
				undo();
				
			}

			return max;

		}else {

			int min = Integer.MAX_VALUE;
			for (int i = 0; i < moveList.size(); i++) {
				Location location = moveList.get(i);
				if(!makeChess(location)) continue;
				int eval = minimax(depth -1, BLACK,df);
				min = Math.min(min, eval);
				undo();
                


			}

			return min;

		}
        
	}
	public int minimaxAphaBeta(int depth,int anpha,int beta, int color,int df) {
		if(depth == 0) {
//			int c = (color == RED?BLACK:RED);
			 return evaluate(BLACK);
		}
//		if(checkHetCo(BLACK)) {
//			return Integer.MIN_VALUE;
//		}
//		if(checkHetCo(RED)) {
//			System.out.println(depth);
//			return Integer.MAX_VALUE;
//			
//		}
		List<Location> moveList = getALLMoves(color);
		if(color == BLACK) {
			int max = -Integer.MAX_VALUE;
			for (int i = 0; i < moveList.size(); i++) {
				Location location = moveList.get(i);
				if(!makeChess(location)) continue;
				int eval = minimaxAphaBeta(depth -1,anpha,beta, RED,df);
				
				if(eval > max ) {

					if(depth == df) {
						value = eval;
						locationResult = location;

					}

				}
				
				max = Math.max(max, eval);
           
				anpha = Math.max(anpha, eval);
				
				undo();
				
				if(beta <= anpha) break;
			}

			return max;

		}else {

			int min = Integer.MAX_VALUE;
			for (int i = 0; i < moveList.size(); i++) {
				Location location = moveList.get(i);
				if(!makeChess(location)) continue;
				int eval = minimaxAphaBeta(depth -1,anpha,beta, BLACK,df);
				min = Math.min(min, eval);
				beta = Math.min(beta, eval);
				undo();
                
				if(beta <= anpha) break;


			}

			return min;

		}
        
	}
	
	
	// -------------tinh diem ----------------------
	public  int evaluate(int color) {
		// color tinh diem ben muon set
		// CACH LAY VI TRI ROW DIEM TRONG MANG "PST PIECE_TYPE[NAME CHESS] -16" (danh cho con ches red) colum thi bang vi tri thu i
		// cach lay row trong bang PST cho con black row =  "PIECE_TYPE[NAME CHESS] -16" column = [MIRROR_SQUARE[i]
		
		int score = 0;
		
		for (int i = 0; i < tableChess.length; i++) {
			int chess = tableChess[i];
			if( chess!= OUT) {
				if( chess != EMPTY) {
					int piece = chess; // vi tri tren ban co (A -> I and 0 -> 9) or name chess
					int pstIndex = PIECE_TYPE[piece] -16; // row trong bang PST bảng tính diem vi tri con co
					int pieceColor = PIECE_COLOR[piece]; // mau con co tai vi tri i
//					
//					// tinh diem con co
//					score += MATERIAL_WEIGHTS[piece];
					if(color == RED) {
					
					   score +=  MATERIAL_WEIGHTS_RED[piece];
						
					}else {
						   score +=  MATERIAL_WEIGHTS_BLACK[piece];

					}
					
					
					//tinh diem vi tri con co
					if(EVALUATION_TYPES[pstIndex]) {
						
						if(color == RED) {
							if(pieceColor == RED) {
								score += PST[pstIndex][i];	
							}
							if(pieceColor == BLACK) {
								score -=PST[pstIndex][MIRROR_SQUARE[i]];
							}
						}else {
							if(pieceColor == RED) {
								score -= PST[pstIndex][i];	
							}
							if(pieceColor == BLACK) {
								score +=PST[pstIndex][MIRROR_SQUARE[i]];
							}
						}
						
						
					}
				}
			}
		}
		
		   
			return score;
		
		
	}
	


	// ----------------- di chuyen con co ----------------------------------------------
	public boolean makeChess(Location location) {

		tableChess[location.getPointerNew()] = location.getNameOld();
		tableChess[location.getPointerOld()] = EMPTY;
		int sourceSquare = tableChess[location.getPointerNew()];
		
		 if(location.getNameOld() == KING_RED) INDEXT_KING_RED = location.getPointerNew();
         if(location.getNameOld() == KING_BLACK) INDEXT_KING_BLACK = location.getPointerNew();
		
		int c = (PIECE_COLOR[sourceSquare] == BLACK?RED:BLACK);
		int index = (PIECE_COLOR[sourceSquare] == BLACK?INDEXT_KING_BLACK:INDEXT_KING_RED);
		saveLocations.push(location);

		if (checkBiTanCong(index, c)) {
			undo();
			return false;
		}
		
		return true;
	}

	public int pointerKing(int color) {
		for (int i = 0; i < tableChess.length; i++) {
			if (color == RED && tableChess[i] == KING_RED) {
				return i;
			}
			if (color == BLACK && tableChess[i] == KING_BLACK) {
				return i;
			}
		}
		return -1;
	}

	// ------------------lay nuoc di -------------------------------------------
	public List<Location> getALLMoves(int color){
	List<Location> list = new ArrayList<Location>();
		
		for (int j = 0; j < tableChess.length; j++) {
			int taget = tableChess[j];
			if(PIECE_COLOR[taget] == color) {
				list.addAll(generateMoves(j, taget));
				
			}
		}
		return list;
	}
	public boolean checkHetCo(int color) {
		int index = (color== BLACK?INDEXT_KING_BLACK:INDEXT_KING_RED);
		int c = (color == BLACK?RED:BLACK);
		if(checkBiTanCong(index, c)) {
			for (Location l : getALLMoves(color)) {
				if(makeChess(l)) {
					undo();
					return false;
				}
			}
			return true;
		}else {
			return false;
		}
		
		
	}
	public List<Location> generateMoves(int i, int targetSource) {
		int color = PIECE_COLOR[targetSource];
		int typeChess = PIECE_TYPE[targetSource];
		Chess chess;
		if (typeChess == ROOK) {
			chess = new ROOK(tableChess);
			return chess.generateMoves(i, color, targetSource);
		}
		if (typeChess == PAWN) {
			chess = new PAWN(tableChess);
			return chess.generateMoves(i, color, targetSource);
		}
		if (typeChess == KING) {
			chess = new KING(tableChess);
			return chess.generateMoves(i, color, targetSource);
		}
		if (typeChess == HORSE) {
			chess = new HORSE(tableChess);
			return chess.generateMoves(i, color, targetSource);
		}
		if (typeChess == ELEPHANT) {
			chess = new ELEPHANT(tableChess);
			return chess.generateMoves(i, color, targetSource);
		}
		if (typeChess == CANNON) {
			chess = new CANNON(tableChess);
			return chess.generateMoves(i, color, targetSource);
		}
		if (typeChess == ADVISOR) {
			chess = new ADVISOR(tableChess);
			return chess.generateMoves(i, color, targetSource);
		}
		
		return null;

	}

	// -------------------undo nuoc di ------------------------------------------
	public boolean undo() {
		if (saveLocations != null && saveLocations.size() > 0) {
			Location location = saveLocations.pop();
			tableChess[location.getPointerNew()] = location.getNameNew();
			tableChess[location.getPointerOld()] = location.getNameOld();
            if(location.getNameOld() == KING_RED) INDEXT_KING_RED = location.getPointerOld();
            if(location.getNameOld() == KING_BLACK) INDEXT_KING_BLACK = location.getPointerOld();
			return true;
		}
		return false;
	}
	

	// ------------------- check nuoc di ---------------------------------------
	int nodes = 0;
	public void perftDrive(int depth,int color) {
		if(depth == 0) {
			nodes++; 
			return;
		}
		List<Location> moveList = getALLMoves(color);
		for (int i = 0; i < moveList.size(); i++) {
			Location location = moveList.get(i);
					if(!makeChess(location)) continue;
						
						if(color == RED) {
							perftDrive(depth-1, BLACK);

						}
						if(color == BLACK) {
							perftDrive(depth-1, RED);

						}
						undo();
					}				
		
	}
	
	// -------------------check xem bi tan cong --------------------------------
	// color ben tan cong
	public boolean checkBiTanCong(int vitri, int color) {
		//check bi tan cong boi con tot
		for (int i = 0; i < PAWN_ATTACK[color].length; i++) {
			int vitriMoi = vitri+PAWN_ATTACK[color][i];
			
				if(vitriMoi >= 23 && vitriMoi <= 130) {
					int targetSquare = tableChess[vitriMoi];
					if(color == BLACK ) {
						if(PIECE_COLOR[targetSquare] == BLACK && targetSquare== PAWN_BLACK) {
							 return true;
						}
					}
					if(color == RED){
						if(PIECE_COLOR[targetSquare]== RED && targetSquare == PAWN_RED) {
							 return true;
						}
					}
				}		
		}
		// con ma
		for (int i = 0; i < DI_CHEO.length; i++) {
			int vitriMoi = vitri+DI_CHEO[i];
			if(vitriMoi >= 23 && vitriMoi <= 130) {
				int targetSquare = tableChess[vitriMoi];

				//xem co bi chan ko neu ko chan thi cho no di
				 if(targetSquare == EMPTY) {
		            	for (int j = 0; j < 2; j++) {
							int vitriHorse = vitri+HORSE_ATTACK[i][j];
							
							if(vitriHorse >= 23 && vitriHorse <= 130) {
								int chess = tableChess[vitriHorse];
								if(color == BLACK ) {
									if(PIECE_COLOR[chess] == BLACK && chess == HORSE_BLACK) {
										 return true;
									}
								}else {
									if(PIECE_COLOR[chess] == RED && chess == HORSE_RED) {
										 return true;
									}
								}
							}		
						}
		            }	
			}
           
		}
		// con phao xe tuong
		for (int i = 0; i < DI_THANG.length; i++) {
			int vitriMoi = vitri+ DI_THANG[i];
			int count = 0;
			if(vitriMoi >= 23 && vitriMoi <= 130) {
				while (tableChess[vitriMoi] != OUT) {
					if(count == 0) {
						if(vitriMoi >= 23 && vitriMoi <= 130) {
							int chess = tableChess[vitriMoi];
							if(color == BLACK ) {
								if((PIECE_COLOR[chess] == BLACK && chess == KING_BLACK) || 
									( PIECE_COLOR[chess]== BLACK && chess == ROOK_BLACK) ) {
									 return true;
								}
							}
							if(color == RED){
								if((PIECE_COLOR[chess] == RED && chess == KING_RED) || 
									(PIECE_COLOR[chess] == RED && chess == ROOK_RED)) {
									 return true;
								}
							}
						}

						
					}
					
					if(tableChess[vitriMoi] != EMPTY && tableChess[vitriMoi] != OUT) {
						count++;
					}
					
					if(count == 2) {
						if(vitriMoi >= 23 && vitriMoi <= 130) {
							int chess = tableChess[vitriMoi];
							if(color == BLACK ) {
								if((PIECE_COLOR[chess]== BLACK && chess == CANNON_BLACK)) {
									 return true;
								}
							}else {
								if((PIECE_COLOR[chess] == RED && chess == CANNON_RED)) {
									 return true;
								}
							}
						}
					}
					vitriMoi+= DI_THANG[i];
					
				}
			}

		}
		return false;
	}
	// --------------------- create table ---------------------------------------
	public void createTableChess(String pen) {
		saveLocations = new Stack<Location>();
		INDEXT_KING_BLACK = pointerKing(BLACK);
		INDEXT_KING_RED = pointerKing(RED);
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < pen.length(); i++) {
			char element = pen.charAt(i);
			if (element >= 'a' && element <= 'z') {
				result.append(element);
			}
			if (element >= 'A' && element <= 'Z') {
				result.append(element);
			}
			if (element >= '0' && element <= '9') {
				int number = Integer.parseInt(element + "");
				for (int j = 0; j < number; j++) {
					result.append(".");
				}
			}

		}
		String x = "xxxxxxxxxxx";
		String[] arr = new String[14];
		arr[0] = x;
		arr[1] = x;
		arr[12] = x;
		arr[13] = x;
		int t = 2;
		String s = "";
		for (int i = 0; i <= 90; i++) {
			if (i % 9 == 0 && i != 0) {
				arr[t] = "x" + s + "x";
				s = "";
				t++;
			}
			if (i < 90)
				s += result.charAt(i);
		}
		StringBuilder result2 = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			result2.append(arr[i]);
		}
		for (int i = 0; i < tableChess.length; i++) {
			char ele = result2.charAt(i);
			if (ele >= 'a' && ele <= 'z') {
				tableChess[i] = indexChess(ele);
			}
			if (ele >= 'A' && ele <= 'Z') {
				tableChess[i] = indexChess(ele);
			}
			if (ele == '.') {
				tableChess[i] = indexChess(ele);
			}
			if (ele == 'x') {
				tableChess[i] = indexChess(ele);
			}

		}
	}

	private int indexChess(char s) {
		for (int i = 0; i < nameChess.length; i++) {
			if ((s + "").equals(nameChess[i])) {
				return i;
			}
		}
		return 15;
	}

	public void printTableChess() {
		for (int i = 0; i < tableChess.length; i++) {
			if (i % 11 == 0) {
				System.out.println();
			}

//			System.out.print(nameChess2[tableChess[i]]+"\t");
			System.out.print(nameChess[tableChess[i]] + " ");
		}
	}

	public static void main(String[] args) {
		TableChess t = new TableChess();
//		t.printTableChess();

	}
}
