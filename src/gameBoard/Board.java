package gameBoard;

import java.awt.Point;
import java.util.ArrayList;

import exceptions.*;
import gameBoard.Piece.Side;

public class Board {

	private int width;
	private int height;
	
	private Piece[][] tiles;
	
	private ArrayList<Piece> blackPieces;
	private ArrayList<Piece> whitePieces;
	
	private boolean gameOver;
	private Side winner;
	
	/**
	 * Creates a new board of size width by height
	 * @param width
	 * @param height
	 * @param initDefaultPieces whether or not to place default configuration of pieces. Width must be 8 for this to take effect
	 */
	public Board(int width, int height, boolean initDefaultPieces) {
		this.width = width;
		this.height = height;
		
		tiles = new Piece[width][height];
		
		whitePieces = new ArrayList<Piece>();
		blackPieces = new ArrayList<Piece>();
		
		gameOver = false;
		winner = null;
		
		if(initDefaultPieces && (width == 8)) {
			initPieces();
		}
	}
	
	/**
	 * Creates a new board of size width by height and, if a normal width of 8, places pieces in default configuration
	 * @param width
	 * @param height
	 */
	public Board(int width, int height) {
		this(width, height, true);
	}
	
	/**
	 * Constructs a copy of the specified board
	 * @param b the board to copy
	 */
	public Board(Board b) {
		this.width = b.width;
		this.height = b.height;
		this.tiles = new Piece[width][height];
		this.gameOver = b.gameOver;
		this.winner = b.winner;
		whitePieces = new ArrayList<Piece>();
		blackPieces = new ArrayList<Piece>();
		
		//Copy tiles array and piece list
		for(int x = 0; x < b.tiles.length; x++) {
			for(int y = 0; y < b.tiles[0].length; y++) {
				if(b.tiles[x][y] != null) {
					this.tiles[x][y] = b.tiles[x][y].copy();
					switch(tiles[x][y].getSide()) {
					case BLACK:
						blackPieces.add(tiles[x][y]);
						break;
					case WHITE:
						whitePieces.add(tiles[x][y]);
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Moves the specified piece to the specified position.
	 * Enforces rule against moving to a tile containing a piece of the same side
	 * @param piece the Piece object to move
	 * @param position the Point on the board to move the piece to
	 * @return
	 * @throws InvalidMoveException Thrown if the movement cannot be completed
	 * @throws PieceNotFoundException Thrown if the specified piece was not found on the board
	 */
	public Piece movePiece(Piece piece, Point position) throws InvalidMoveException, PieceNotFoundException {
		if(!validPoint(position))
			throw new InvalidMoveException("Invalid destination: " + position.x + "," + position.y);
		piece = findPiece(piece);
		if(piece == null)
			throw new PieceNotFoundException("No such piece on board: " + piece);
		Piece destroyed = tiles[position.x][position.y];
		if(destroyed != null) {
			if(piece.getSide() == destroyed.getSide())
				throw new InvalidMoveException("Cannot destroy piece of same side");
			boolean success = whitePieces.remove(destroyed);
			//System.out.println("Removed " + destroyed + " from whitePieces: " + success);
			success = blackPieces.remove(destroyed);
			//System.out.println("Removed " + destroyed + " from blackPieces: " + success);
			if(destroyed instanceof King) {
				gameOver = true;
				winner = destroyed.getSide().oppositeSide();
			}
		}
		tiles[position.x][position.y] = piece;
		tiles[piece.getPosition().x][piece.getPosition().y] = null;
		piece.setPosition(position);
		return destroyed;
	}
	
	/**
	 * Executes the specified movement
	 * Enforces rule against moving to a tile containing a piece of the same side
	 * @param m
	 * @return
	 * @throws InvalidMoveException Thrown if the movement cannot be completed
	 * @throws PieceNotFoundException Thrown if the specified piece was not found on the board
	 */
	public Piece movePiece(Move m) throws InvalidMoveException, PieceNotFoundException {
		return movePiece(m.getPiece(), m.getDestination());
	}
	
	/**
	 * Returns a reference to the piece on the board matching the specified piece
	 * @param p Piece to look for
	 * @return Reference to found piece
	 */
	private Piece findPiece(Piece p) {
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				if((tiles[x][y] != null) && tiles[x][y].equals(p)) {
					return tiles[x][y];
				}
			}
		}
		return null;
	}
	
	public Piece getPieceAtPoint(Point p) {
		if(!validPoint(p))
			throw new InvalidPointException("Invalid board position: " + p.x + "," + p.y);
		return tiles[p.x][p.y];
	}
	
	/**
	 * Place all the pieces on the board in their starting position
	 * Assumes a board of width 8
	 */
	private void initPieces() {
		if(getWidth() != 8)
			return;
		//Pawns
		for(int x = 0; x < getWidth(); x++) {
			insertPiece(new Pawn(x, 1, Side.WHITE));
			insertPiece(new Pawn(x, getHeight() - 2, Side.BLACK));
		}
		
		//White Pieces
		insertPiece(new Rook(0, 0, Side.WHITE));
		insertPiece(new Knight(1, 0, Side.WHITE));
		insertPiece(new Bishop(2, 0, Side.WHITE));
		insertPiece(new Queen(3, 0, Side.WHITE));
		insertPiece(new King(4, 0, Side.WHITE));
		insertPiece(new Bishop(5, 0, Side.WHITE));
		insertPiece(new Knight(6, 0, Side.WHITE));
		insertPiece(new Rook(7, 0, Side.WHITE));
		
		//Black Pieces
		insertPiece(new Rook(0, getHeight() - 1, Side.BLACK));
		insertPiece(new Knight(1, getHeight() - 1, Side.BLACK));
		insertPiece(new Bishop(2, getHeight() - 1, Side.BLACK));
		insertPiece(new Queen(3, getHeight() - 1, Side.BLACK));
		insertPiece(new King(4, getHeight() - 1, Side.BLACK));
		insertPiece(new Bishop(5, getHeight() - 1, Side.BLACK));
		insertPiece(new Knight(6, getHeight() - 1, Side.BLACK));
		insertPiece(new Rook(7, getHeight() - 1, Side.BLACK));
	}
	
	/**
	 * Inserts the specified piece into the tiles array
	 * Uses the location stored within the piece
	 * @param p The piece to add to the tiles array
	 */
	public void insertPiece(Piece p) {
		if(!validPoint(p.getPosition()))
			throw new InvalidPointException("Could not insert piece, position is invalid: " + p.getPosition());
		tiles[p.getPosition().x][p.getPosition().y] = p;
		(p.getSide() == Side.BLACK ? blackPieces : whitePieces).add(p);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	/**
	 * Returns whether or not the point exists within the board
	 * @param p The point to check
	 * @return True if the point exists within the board, false if not
	 */
	public boolean validPoint(Point p) {
		return ((p.x < width) && (p.x >= 0) && (p.y < height) && (p.y >= 0));
	}
	
	public ArrayList<Piece> getPieces(Side side) {
		switch(side) {
		case BLACK:
			return (ArrayList<Piece>) blackPieces.clone();
		case WHITE:
			return (ArrayList<Piece>) whitePieces.clone();
		default:
			throw new UnsupportedOperationException("This side has not been implemented: " + side.toString());
		}
	}
	
	/**
	 * Returns whether or not the specified side's king is in direct path of an opponents piece
	 * Does not factor in whether other pieces can block
	 * @param s The side to check whether or not is in check
	 * @return True if the side is in check
	 */
	public boolean inDanger(Side s) {
		ArrayList<Piece> pieces = (s == Side.BLACK ? blackPieces : whitePieces);
		Point kingPosition = null;
		for(Piece p : pieces) {
			if(p instanceof King) {
				kingPosition = p.getPosition();
			}
		}
		if(kingPosition == null) {
			//System.out.println("WARNING: Specified side has no King: " + s);
			return false;
		}
		
		ArrayList<Piece> enemyPieces = (s == Side.WHITE ? blackPieces : whitePieces);
		for(Piece p : enemyPieces) {
			for(Move m : p.getPossibleMoves(this)) {
				if(m.getDestination().equals(kingPosition)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean gameOver() {
		return gameOver;
	}
	
	public Side winner() {
		return winner;
	}
	
	public String toString() {
		String str = "";
		
		for(int i = 0; i < (17 * width) + 2; i++) {
			str += "-";
		}
		
		str += "\n";
		
		for(int y = height - 1; y >= 0; y--) {
			str += "|" + y;
			for(int x = 0; x < width; x++) {
				str += String.format("%15s |", tiles[x][y]);
			}
			str += "\n";
		}
		
		str += "| ";
		
		for(int x = 0; x < width; x++) {
			str += String.format("%15s |", x);
		}
		
		str += "\n";
		
		for(int i = 0; i < (17 * width) + 2; i++) {
			str += "-";
		}
		
		return str;
	}
	
	//Testing
	public static void main(String[] args) {
		Board b = new Board(2,2);
		System.out.println(b);
		
		
		//Testing findPiece and the Piece equals function
		System.out.println("################ Test 1 ################");
		System.out.println("Finding White Pawn at (0,0): " + b.findPiece(new Pawn(0,0, Piece.Side.WHITE)));
		System.out.println("Finding White Pawn at (1,0): " + b.findPiece(new Pawn(1,0, Piece.Side.WHITE)));
		System.out.println("Finding Black Pawn at (1,0): " + b.findPiece(new Pawn(1,0, Piece.Side.BLACK)));
		System.out.println("Finding Black Pawn at (5,0): " + b.findPiece(new Pawn(5,0, Piece.Side.BLACK)));
		System.out.println("Finding Black Pawn at (0,0): " + b.findPiece(new Pawn(0,0, Piece.Side.BLACK)));
		Pawn p = new Pawn(0,0, Piece.Side.BLACK);
		p.setMoved(true);
		System.out.println("Finding moved Black Pawn at (0,0): " + b.findPiece(p));
		
		
		//Testing movement
		System.out.println("################ Test 2 ################");
		b = new Board(4,4, false);
		b.insertPiece(new Bishop(0,0,Side.BLACK));
		b.insertPiece(new Bishop(1,1,Side.WHITE));
		b.insertPiece(new Bishop(2,2,Side.WHITE));
		System.out.println(b);
		System.out.println(b.getPieceAtPoint(new Point(1,1)).getPossibleMoves(b));
		System.out.println("White Pieces: " + b.whitePieces);
		System.out.println("Black Pieces: " + b.blackPieces);
		//b.movePiece(new Pawn(0,0,Side.BLACK), new Point(1,1));
		//System.out.println(b);
		//System.out.println("White Pieces: " + b.whitePieces);
		//System.out.println("Black Pieces: " + b.blackPieces);
		
		//Testing board generation
		System.out.println("################ Test 3 ################");
		b = new Board(8,8);
		System.out.println(b);
		
		//Testing inCheck()
		System.out.println("################ Test 4 ################");
		b = new Board(4,4);
		King k = new King(0,0,Side.BLACK);
		b.insertPiece(k);
		b.insertPiece(new Bishop(3,3,Side.WHITE));
		System.out.println(b);
		System.out.println("Black in check: " + b.inDanger(Side.BLACK));
		b.movePiece(new Move(k, new Point(1,0)));
		System.out.println(b);
		System.out.println("Black in check: " + b.inDanger(Side.BLACK));
		
		//Testing side.oppositeSide()
		System.out.println("################ Test 5 ################");
		b = new Board(2,2);
		p = new Pawn(0,0,Side.BLACK);
		b.insertPiece(p);
		System.out.println("Pawn side: " + p.getSide() + " Opponent side: " + p.getSide().oppositeSide());
		System.out.println("Pawn side again: " + p.getSide());
	}
}
