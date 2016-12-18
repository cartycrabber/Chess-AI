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

	@Override
	public ArrayList<Point> getPossibleMoves(Board b) {
		ArrayList<Point> moves = new ArrayList<Point>();
		
		Point test = new Point(getPosition().x + 1, getPosition().y + 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x, getPosition().y + 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x - 1, getPosition().y + 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x - 1, getPosition().y);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x - 1, getPosition().y - 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x, getPosition().y - 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x + 1, getPosition().y - 1);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x + 1, getPosition().y);
		if(b.validPoint(test)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		return moves;
	}

}
