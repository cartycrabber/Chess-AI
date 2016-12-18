package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(int x, int y, Side s) {
		super(x, y, s, "Queen");
	}

	public Queen(Point position, Side side) {
		super(position, side, "Queen");
	}

	@Override
	public ArrayList<Point> getPossibleMoves(Board b) {
		ArrayList<Point> moves = new ArrayList<Point>();
		
		Point test;
		
		//Knight Test
		//Test left to right
		for(int x = 0; x < b.getWidth(); x++) {
			if(x == getPosition().x)
				continue;
			test = new Point(x, getPosition().y);
			if((b.getPieceAtPoint(test) == null) || (b.getPieceAtPoint(test).getSide() != getSide()))
				moves.add(test);
		}
		
		//Test bottom to top
		for(int y = 0; y < b.getHeight(); y++) {
			if(y == getPosition().y)
				continue;
			test = new Point(getPosition().x, y);
			if((b.getPieceAtPoint(test) == null) || (b.getPieceAtPoint(test).getSide() != getSide()))
				moves.add(test);
		}
		
		//Bishop Test
		test = new Point();
		//Test up and to the right
		for(test.setLocation(getPosition().x + 1, getPosition().y + 1); b.validPoint(test); test.setLocation(test.x + 1, test.y + 1)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide())) {
				moves.add((Point) test.clone());
			}
		}
		
		//Test up and to the left
		for(test.setLocation(getPosition().x - 1, getPosition().y + 1); b.validPoint(test); test.setLocation(test.x - 1, test.y + 1)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide())) {
				moves.add((Point) test.clone());
			}
		}
		
		//Test down and to the left
		for(test.setLocation(getPosition().x - 1, getPosition().y - 1); b.validPoint(test); test.setLocation(test.x - 1, test.y - 1)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide())) {
				moves.add((Point) test.clone());
			}
		}
		
		//Test down and to the right
		for(test.setLocation(getPosition().x + 1, getPosition().y - 1); b.validPoint(test); test.setLocation(test.x + 1, test.y - 1)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide())) {
				moves.add((Point) test.clone());
			}
		}
		return moves;
	}

}
