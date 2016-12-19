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
	public ArrayList<Move> getPossibleMoves(Board b) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		//Basically, possible moves of a rook and bishop combined
		Piece subTest = new Rook(getPosition(), getSide());
		moves.addAll(subTest.getPossibleMoves(b));
		subTest = new Bishop(getPosition(), getSide());
		moves.addAll(subTest.getPossibleMoves(b));
		return moves;
	}

}
