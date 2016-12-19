package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

public class Knight extends Piece {

	public Knight(int x, int y, Side s) {
		super(x, y, s, "Knight");
	}

	public Knight(Point position, Side side) {
		super(position, side, "Knight");
	}

	@Override
	public ArrayList<Move> getPossibleMoves(Board b) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		Point test = new Point(getPosition().x + 1, getPosition().y + 2);
		Piece pieceAtPoint;
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x - 1, getPosition().y + 2);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x - 1, getPosition().y - 2);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x + 1, getPosition().y - 2);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x + 2, getPosition().y + 1);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x - 2, getPosition().y + 1);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x - 2, getPosition().y - 1);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		test = new Point(getPosition().x + 2, getPosition().y - 1);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add(new Move(this, (Point) test.clone()));
		}
		
		return moves;
	}

}
