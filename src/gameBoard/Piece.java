package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

import gameBoard.Piece.Side;

public abstract class Piece {
	
	private String debugName;
	
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
	 * @param debugName The name to use in toString()
	 */
	protected Piece(int x, int y, Side s, String debugName) {
		this(new Point(x, y), s, debugName);
	}
	
	protected Piece(Point position, Side side, String debugName) {
		this.position = position;
		this.side = side;
		this.debugName = debugName;
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
	
	public String toString() {
		return (getSide() == Side.BLACK ? "B" : "W") + "-" + debugName + "(" + getPosition().x + "," + getPosition().y + ")";
	}
	
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
