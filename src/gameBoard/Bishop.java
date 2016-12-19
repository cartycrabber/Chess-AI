package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

public class Bishop extends Piece {

	public Bishop(int x, int y, Side s) {
		super(x, y, s, "Bishop");
	}

	public Bishop(Point position, Side side) {
		super(position, side, "Bishop");
	}
	
	public Bishop(Bishop b) {
		super(b);
	}

	@Override
	public ArrayList<Move> getPossibleMoves(Board b) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		Point test = new Point();
		//Test up and to the right
		for(test.setLocation(getPosition().x + 1, getPosition().y + 1); b.validPoint(test); test.setLocation(test.x + 1, test.y + 1)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide())) {
				moves.add(new Move(this, (Point) test.clone()));
			}
		}
		
		//Test up and to the left
		for(test.setLocation(getPosition().x - 1, getPosition().y + 1); b.validPoint(test); test.setLocation(test.x - 1, test.y + 1)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide())) {
				moves.add(new Move(this, (Point) test.clone()));
			}
		}
		
		//Test down and to the left
		for(test.setLocation(getPosition().x - 1, getPosition().y - 1); b.validPoint(test); test.setLocation(test.x - 1, test.y - 1)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide())) {
				moves.add(new Move(this, (Point) test.clone()));
			}
		}
		
		//Test down and to the right
		for(test.setLocation(getPosition().x + 1, getPosition().y - 1); b.validPoint(test); test.setLocation(test.x + 1, test.y - 1)) {
			Piece pieceAtPoint = b.getPieceAtPoint(test);
			if((pieceAtPoint == null) || (pieceAtPoint.getSide() != getSide())) {
				moves.add(new Move(this, (Point) test.clone()));
			}
		}
		
		return moves;
	}
	
	@Override
	public Piece copy() {
		return new Bishop(this);
	}
}
