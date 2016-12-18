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

	@Override
	public ArrayList<Point> getPossibleMoves(Board b) {
		ArrayList<Point> moves = new ArrayList<Point>();
		
		Point test;
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
		return moves;
	}
}
