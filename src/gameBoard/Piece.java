package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
	
	public enum Side {
		BLACK, WHITE
	};
	
	private Point position;
	private Side side;
	
	/**
	 * Creates a new Piece at position (x,y) belonging to the specified side
	 * @param x
	 * @param y
	 * @param s
	 */
	public Piece(int x, int y, Side s) {
		this(new Point(x, y), s);
	}
	
	public Piece(Point position, Side side) {
		this.position = position;
		this.side = side;
	}
	
	public void setPosition(Point position) {
		position.setLocation(position);
	}
	
	public void setPosition(int x, int y) {
		position.setLocation(x, y);
	}
	
	public Side getSide() {
		return side;
	}
	
	public Point getPosition() {
		return position;
	}
	
	//A list of all possible movement offsets from 0,0
	//Must be extrapolated to pieces current position
	public abstract ArrayList<Point> getPossibleMoves(Board b);
	public abstract String toString();
	
	public boolean equals(Object o) {
		if(o == null)
			return false;
		if(o == this)
			return true;
		if(o.getClass() == this.getClass()) {
			Piece p = (Piece)o;
			return ((p.getPosition().equals(getPosition()))
					&& (p.getSide() == getSide()));
		}
		return false;
	}
	
}
