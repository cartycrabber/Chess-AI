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
	public ArrayList<Point> getPossibleMoves(Board b) {
		ArrayList<Point> moves = new ArrayList<Point>();
		
		Point test = new Point(getPosition().x + 1, getPosition().y + 2);
		Piece pieceAtPoint;
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x - 1, getPosition().y + 2);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x - 1, getPosition().y - 2);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x + 1, getPosition().y - 2);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x + 2, getPosition().y + 1);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x - 2, getPosition().y + 1);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x - 2, getPosition().y - 1);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		test = new Point(getPosition().x + 2, getPosition().y - 1);
		if(b.validPoint(test)) {
			pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide()))
				moves.add((Point) test.clone());
		}
		
		return moves;
	}

}
