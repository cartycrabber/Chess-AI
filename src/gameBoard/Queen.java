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
		
		//Basically, possible moves of a rook and bishop combined
		Piece subTest = new Rook(getPosition(), getSide());
		moves.addAll(subTest.getPossibleMoves(b));
		subTest = new Bishop(getPosition(), getSide());
		moves.addAll(subTest.getPossibleMoves(b));
		
		return moves;
	}

}
