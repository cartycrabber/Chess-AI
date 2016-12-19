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
	
	public Queen(Queen q) {
		super (q);
	}

	@Override
	public ArrayList<Move> getPossibleMoves(Board b) {
		ArrayList<Move> moves = new ArrayList<Move>();
		
		//Basically, possible moves of a rook and bishop combined
		Piece subTest = new Rook(getPosition(), getSide());
		moves.addAll(subTest.getPossibleMoves(b));
		subTest = new Bishop(getPosition(), getSide());
		moves.addAll(subTest.getPossibleMoves(b));
		
		//All the move pieces are Rooks and Bishops, need to convert them to Queens
		for(Move m : moves) {
			m.setPiece(this);
		}
		return moves;
	}

	@Override
	public Piece copy() {
		return new Queen(this);
	}
}
