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
	
	public Pawn(Pawn p) {
		super(p);
		this.hasMoved = p.hasMoved;
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
	public ArrayList<Move> getPossibleMoves(Board b) {
		ArrayList<Move> moves = new ArrayList<Move>();
		int direction = (getSide() == Side.WHITE ? 1 : -1);
		Point test = new Point(getPosition().x, getPosition().y + 1 * direction);
		//If it is a valid point on the board and either there is no piece at the point or the piece belongs to the other side
		if(b.validPoint(test) && ((b.getPieceAtPoint(test) == null)))
			moves.add(new Move(this, test));
		
		if(!hasMoved) {
			test = new Point(getPosition().x, getPosition().y + 2 * direction);
			if(b.validPoint(test) && (b.getPieceAtPoint(test) == null)
					&& (b.getPieceAtPoint(new Point(getPosition().x, getPosition().y + 1)) == null))
				moves.add(new Move(this, test));
		}
		
		test = new Point(getPosition().x + 1, getPosition().y + 1 * direction);
		Piece pieceAtPoint;
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint != null) && (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, test));
		}
		
		test = new Point(getPosition().x - 1, getPosition().y + 1 * direction);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint != null) && (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, test));
		}
		return moves;
	}
	
	public boolean equals(Object o) {
		return super.equals(o) && (((Pawn) o).getMoved() == getMoved());
	}

	@Override
	public Piece copy() {
		return new Pawn(this);
	}
}
