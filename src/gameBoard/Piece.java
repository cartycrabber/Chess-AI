package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

import gameBoard.Piece.Side;

public abstract class Piece {
	
	private String debugName;
	
	public enum Side {
		BLACK, WHITE;
		public Side oppositeSide() { return (this == BLACK ? WHITE : BLACK); }
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
	
	public Piece(Piece p) {
		this.position = new Point(p.position);
		this.side = p.side;
		this.debugName = new String(p.debugName);
	}
	
	/**
	 * Creates a new Piece at the specified point belonging to the specified side
	 * @param position
	 * @param side
	 * @param debugName The name to use in toString()
	 */
	protected Piece(Point position, Side side, String debugName) {
		this.position = position;
		this.side = side;
		this.debugName = debugName;
	}
	
	public void setPosition(Point position) {
		this.position.setLocation(position);
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
	
	/**
	 * A list off all possible moves according to the rules of chess for this piece
	 * @param b the board instance to check for possible moves
	 * @return list of possible destination points
	 */
	public abstract ArrayList<Move> getPossibleMoves(Board b);
	public abstract Piece copy();
	
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
