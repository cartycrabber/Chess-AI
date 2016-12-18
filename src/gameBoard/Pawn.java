package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {

	private boolean hasMoved;
	
	public Pawn(int x, int y, Side s) {
		super(x, y, s, "Pawn");
		hasMoved = false;
	}
	
	public Pawn(Point position, Side s) {
		super(position, s, "Pawn");
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

	
	@Override
	public ArrayList<Point> getPossibleMoves(Board b) {
		ArrayList<Point> moves = new ArrayList<Point>();
		Point test = new Point(getPosition().x, getPosition().y + 1);
		//If it is a valid point on the board and either there is no piece at the point or the piece belongs to the other side
		if(b.validPoint(test) && ((b.getPieceAtPoint(test) == null)))
			moves.add(test);
		
		if(!hasMoved) {
			test = new Point(getPosition().x, getPosition().y + 2);
			if(b.validPoint(test) && ((b.getPieceAtPoint(test) == null)))
				moves.add(test);
		}
		
		test = new Point(getPosition().x + 1, getPosition().y + 1);
		Piece pieceAtPoint;
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint != null) && (pieceAtPoint.getSide() != getSide()))
				moves.add(test);
		}
		
		test = new Point(getPosition().x - 1, getPosition().y + 1);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint != null) && (pieceAtPoint.getSide() != getSide()))
				moves.add(test);
		}
		return moves;
	}
	
	public boolean equals(Object o) {
		return super.equals(o) && (((Pawn) o).getMoved() == getMoved());
	}
}
