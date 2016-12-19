package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

import gameBoard.Piece.Side;

public class Rook extends Piece {

	public Rook(int x, int y, Side s) {
		super(x, y, s, "Rook");
	}

	public Rook(Point position, Side side) {
		super(position, side, "Rook");
	}
	
	public Rook(Rook r) {
		super(r);
	}

	@Override
	public ArrayList<Move> getPossibleMoves(Board b) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		Point test;
		//Test left to right
		for(int x = 0; x < b.getWidth(); x++) {
			if(x == getPosition().x)
				continue;
			test = new Point(x, getPosition().y);
			if((b.getPieceAtPoint(test) == null) || (b.getPieceAtPoint(test).getSide() != getSide()))
				moves.add(new Move(this, test));
		}
		
		//Test bottom to top
		for(int y = 0; y < b.getHeight(); y++) {
			if(y == getPosition().y)
				continue;
			test = new Point(getPosition().x, y);
			if((b.getPieceAtPoint(test) == null) || (b.getPieceAtPoint(test).getSide() != getSide()))
				moves.add(new Move(this, test));
		}
		return moves;
	}
	
	public Rook copy() {
		return new Rook(this);
	}
}
