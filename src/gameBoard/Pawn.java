package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {

	private boolean hasMoved;
	
	public Pawn(int x, int y, Side s) {
		this(new Point(x, y), s);
	}
	
	public Pawn(Point position, Side s) {
		super(position, s);
		hasMoved = false;
	}
	
	//Set whether or not the pawn has moved;
	public void setMoved(boolean moved) {
		hasMoved = moved;
	}
	
	//Get whether or not the pawn has moved
	public boolean getMoved() {
		return hasMoved;
	}

	/*
	 * MUST check to see if an opponent piece is at the end point for the (1,1) and (-1,1) moves
	 * @see gameBoard.Piece#getPossibleMoves()
	 */
	@Override
	public ArrayList<Move> getPossibleMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		moves.add(new Move(new Point(0,0), new Point(0,1)));
		moves.add(new Move(new Point(0,0), new Point(0,2)));
		moves.add(new Move(new Point(0,0), new Point(1,1)));
		moves.add(new Move(new Point(0,0), new Point(-1,1)));
		
		return moves;
	}

	
	public String toString() {
		return (getSide() == Side.BLACK ? "B" : "W") + "-Pawn(" + position.x + "," + position.y + ")";
	}
}
