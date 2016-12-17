package gameBoard;

import java.awt.Point;

public class Board {

	private int width;
	private int height;
	
	private Piece[][] tiles;
	
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		
		tiles = new Piece[width][height];
		
		initPieces();
	}
	
	public Board(Board b) {
		this.width = b.width;
		this.height = b.height;
		this.tiles = b.tiles.clone();
	}
	
	public void movePiece(Move move) {
		
	}
	
	private void initPieces() {
		setPiece(new Pawn(0,0, Piece.Side.BLACK));
	}
	
	private void setPiece(Piece p) {
		tiles[p.getPosition().x][p.getPosition().y] = p;
	}
	
	public String toString() {
		String str = "";
		
		for(int i = 0; i < (17 * width) + 2; i++) {
			str += "-";
		}
		
		str += "\n";
		
		for(int y = height - 1; y >= 0; y--) {
			str += "|" + y;
			for(int x = 0; x < width; x++) {
				str += String.format("%15s |", tiles[x][y]);
			}
			str += "\n";
		}
		
		str += "| ";
		
		for(int x = 0; x < width; x++) {
			str += String.format("%15s |", x);
		}
		
		str += "\n";
		
		for(int i = 0; i < (17 * width) + 2; i++) {
			str += "-";
		}
		
		return str;
	}
}
