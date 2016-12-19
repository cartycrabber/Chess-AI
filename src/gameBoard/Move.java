package gameBoard;

import java.awt.Point;

public class Move {

	Piece piece;
	Point destination;
	
	public Move(Piece pieceToMove, Point destination) {
		piece = pieceToMove;
		this.destination = destination;
	}

	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece p) {
		this.piece = p;
	}
	
	public Point getDestination() {
		return destination;
	}
	
	public Point getOrigin() {
		return piece.getPosition();
	}
	
	public String toString() {
		return piece + " => (" + destination.x + "," + destination.y + ")";
	}
}
