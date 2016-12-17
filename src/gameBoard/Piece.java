package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
	
	enum Side {
		BLACK, WHITE
	};
	
	protected Point position;
	private Side side;
	
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
	public abstract ArrayList<Move> getPossibleMoves();
	public abstract String toString();
	
}
