package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

public class King extends Piece {

	public King(int x, int y, Side s) {
		super(x, y, s, "King");
	}

	public King(Point position, Side side) {
		super(position, side, "King");
	}
	
	public King(King k) {
		super(k);
	}

	@Override
	public ArrayList<Move> getPossibleMoves(Board b) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		Point test = new Point(getPosition().x + 1, getPosition().y + 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x, getPosition().y + 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x - 1, getPosition().y + 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x - 1, getPosition().y);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x - 1, getPosition().y - 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x, getPosition().y - 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x + 1, getPosition().y - 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x + 1, getPosition().y);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		return moves;
	}

	@Override
	public Piece copy() {
		return new King(this);
	}
}
