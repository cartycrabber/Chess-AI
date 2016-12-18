import java.awt.Point;

import exceptions.InvalidMoveException;
import gameBoard.Board;
import gameBoard.Pawn;
import gameBoard.Piece;


public class Driver {

	public static void main(String[] args) {
		Board b = new Board(8,8);
		
		System.out.println(b.toString());
		
		try {
			b.movePiece(new Pawn(0,0, Piece.Side.BLACK), new Point(5,5));
		} catch (InvalidMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
