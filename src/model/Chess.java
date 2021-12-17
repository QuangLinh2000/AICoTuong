package model;

import java.util.List;

public abstract class Chess {
	
	public static final int UP = -11;
	public static final int DOWN = 11;
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	
	public static final int[] DI_THANG = {LEFT,RIGHT,UP,DOWN}; 
	public static final int[] DI_CHEO = {UP+LEFT,UP+RIGHT,DOWN+LEFT,DOWN+RIGHT}; 
	
	
	
	int[] tableChess;
	public Chess(int[] tableChess) {
		this.tableChess = tableChess;
	}
	//lay nuoc di cho tung con co
	
	protected abstract List<Location> generateMoves(int i,int color,int sourcePiece);

}
