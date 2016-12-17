package gameBoard;

import java.awt.Point;

public class Move {

	private Point start;
	private Point end;
	
	public Move(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public Point getStart() {
		return start;
	}
	
	public Point getEnd() {
		return end;
	}
}
