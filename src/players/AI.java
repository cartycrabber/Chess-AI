package players;

import java.awt.Point;
import java.util.ArrayList;

import gameBoard.Board;
import gameBoard.Move;
import gameBoard.Piece;
import gameBoard.Piece.Side;
import tree.Node;

public class AI {
	
	static final int KING_WEIGHT = 2000;
	static final int QUEEN_WEIGHT = 90;
	static final int ROOK_WEIGHT = 50;
	static final int BISHOP_WEIGHT = 30;
	static final int KNIGHT_WEIGHT = 30;
	static final int PAWN_WEIGHT = 10;
	static final int MOVE_WEIGHT = 1;

	private Side side;
	private Node<Board, Move> possibleMoves;
	
	private int nodeCount;
	
	public AI(Side side) {
		this.side = side;
		nodeCount = 0;
	}

	public void takeTurn(Board b, int turnCalcDepth) {
		possibleMoves = new Node<Board, Move>(b);
		nodeCount = 0;
		int negaMaxScore = negaMax(b, side, turnCalcDepth);
		System.out.println("Max score found by negamax: " + negaMaxScore + " for move " + negaMaxMove);
		/*buildMovesTree(b, side, possibleMoves, turnCalcDepth);
		System.out.println("Moves tree complete. There are " + nodeCount + " nodes");
		
		//Get leaves
		ArrayList<Node<Board, Move>> leaves = possibleMoves.getLeaves();
		
		
		ArrayList<Node<Board, Move>> highest = new ArrayList<Node<Board, Move>>();
		//Get max scoring leafs
		int max = eval(leaves.get(0), side);
		for(int i = 1; i < leaves.size(); i++) {
			int score = eval(leaves.get(i), side);
			if(score == max) {
				highest.add(leaves.get(i));
			}
			if(score > max) {
				max = score;
				highest.clear();
				highest.add(leaves.get(i));
			}
		}
		
		//System.out.println("there are " + highest.size() + " nodes tied for the max");
		
		Node<Board, Move> maxNode;
		//if the list is empty then we are in check mate
		//if(highest.size() == 0)
		//	maxNode = leaves.get(0);
		//else
			maxNode = highest.get(/*(int)(Math.random() * highest.size())0);
		//System.out.println("Chosen leaf("+max+"): \n" + maxNode.getData());
		
		//traverse up the tree from that node to get the first move that lead to it
		while(maxNode.getParent().getParent() != null) {
			maxNode = maxNode.getParent();
		}
		
		System.out.println("Move " + maxNode.getLink() + " is the maximizing move with a score of " + max);*/
		
		b.movePiece(negaMaxMove);
	}
	
	private Move negaMaxMove;
	
	private int negaMax(Board b, Side turn, int depth) {
		if(depth <= 0) {
			return eval(b,turn);
		}
		int max = -1000000;
		Move m = null;
		ArrayList<Piece> pieces = b.getPieces(turn);
		for(Piece piece : pieces) {
			for(Move move : piece.getPossibleMoves(b)) {
				Board test = new Board(b);
				test.movePiece(move);
				int score = -negaMax(test, turn.oppositeSide(), depth - 1);
				if(score > max) {
					max = score;
					m = move;
				}
			}
		}
		negaMaxMove = m;
		return max;
	}
	
	private void buildMovesTree(Board b, Side turn, Node<Board, Move> parent, int depth) {
		//System.out.println("New Call: " + turn);
		if((depth <= 0) || b.gameOver()) {
			//System.out.println("Depth: " + depth + " GO: " + b.gameOver());
			return;
		}
		ArrayList<Piece> pieces = b.getPieces(turn);
		for(Piece piece : pieces) {
			for(Move move : piece.getPossibleMoves(b)) {
				Board testBoard = new Board(b);
				//System.out.println("testing move " + move);
				testBoard.movePiece(move);
				//System.out.println("TestBoard("+depth+"):\n" + testBoard);
				//System.out.println("realBoard:\n" + b);
				//If the move does not put me in check, go ahead and add it to the possible moves
				if(!testBoard.inDanger(turn)) {
					Node<Board, Move> n = new Node<Board, Move>(new Board(testBoard), move, parent);
					parent.addChild(n);
					nodeCount++;
					//buildMovesTree(testBoard, turn.oppositeSide(), n, depth - 1);
				}
					//System.out.println("Move puts me in check, ignoring");
			}
		}
		if(depth == 4)
			System.out.println("Finished level " + depth + " with a width of " + parent.getChildren().size());
		for(Node<Board, Move> n : parent.getChildren()) {
			buildMovesTree(n.getData(), turn.oppositeSide(), n, depth - 1);
		}
		//System.out.println("end call: " + turn);
	}
	
	private int eval(Board b, Side s) {
		int sum = 0;
		for(Piece p : b.getPieces(s)) {
			switch(p.getClass().getSimpleName()) {
			case "Pawn":
				sum += PAWN_WEIGHT;
				break;
			case "Rook":
				sum += ROOK_WEIGHT;
				break;
			case "Bishop":
				sum += BISHOP_WEIGHT;
				break;
			case "Knight":
				sum += KNIGHT_WEIGHT;
				break;
			case "Queen":
				sum += QUEEN_WEIGHT;
				break;
			case "King":
				sum += KING_WEIGHT;
				break;
			}
		}
		for(Piece p : b.getPieces(s.oppositeSide())) {
			switch(p.getClass().getSimpleName()) {
			case "Pawn":
				sum -= PAWN_WEIGHT;
				break;
			case "Rook":
				sum -= ROOK_WEIGHT;
				break;
			case "Bishop":
				sum -= BISHOP_WEIGHT;
				break;
			case "Knight":
				sum -= KNIGHT_WEIGHT;
				break;
			case "Queen":
				sum -= QUEEN_WEIGHT;
				break;
			case "King":
				sum -= KING_WEIGHT;
				break;
			}
		}
		
		return sum;
	}
	
	private int eval(Node<Board, Move> n, Side s) {
		int sum = 0;
		Board b = n.getData();
		for(Piece p : b.getPieces(s)) {
			switch(p.getClass().getSimpleName()) {
			case "Pawn":
				sum += PAWN_WEIGHT;
				break;
			case "Rook":
				sum += ROOK_WEIGHT;
				break;
			case "Bishop":
				sum += BISHOP_WEIGHT;
				break;
			case "Knight":
				sum += KNIGHT_WEIGHT;
				break;
			case "Queen":
				sum += QUEEN_WEIGHT;
				break;
			case "King":
				sum += KING_WEIGHT;
				break;
			}
		}
		for(Piece p : b.getPieces(s.oppositeSide())) {
			switch(p.getClass().getSimpleName()) {
			case "Pawn":
				sum -= PAWN_WEIGHT;
				break;
			case "Rook":
				sum -= ROOK_WEIGHT;
				break;
			case "Bishop":
				sum -= BISHOP_WEIGHT;
				break;
			case "Knight":
				sum -= KNIGHT_WEIGHT;
				break;
			case "Queen":
				sum -= QUEEN_WEIGHT;
				break;
			case "King":
				sum -= KING_WEIGHT;
				break;
			}
		}
		
		while(n.getParent() != null) {
			sum--;
			n = n.getParent();
		}
		
		return sum;
	}
}
