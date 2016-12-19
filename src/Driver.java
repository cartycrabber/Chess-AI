import java.awt.Point;

import exceptions.InvalidMoveException;
import gameBoard.*;
import gameBoard.Piece.Side;
import players.AI;


public class Driver {

	public static void main(String[] args) {
		//testing();
		Board b = new Board(8,8);
		AI playerWhite = new AI(Side.WHITE);
		AI playerBlack = new AI(Side.BLACK);
		
		int turns = 0;
		
		Side turn = Side.WHITE;
		long turnTime;
		long gameTime = System.currentTimeMillis();
		while(!b.gameOver()) {
			turns++;
			System.out.println("----------------Turn " + turns + "(" + turn + ")" + "----------------");
			turnTime = System.currentTimeMillis();
			switch(turn) {
			case WHITE:
				playerWhite.takeTurn(b,3);
				break;
			case BLACK:
				playerBlack.takeTurn(b,3);
				break;
			}
			turn = turn.oppositeSide();
			turnTime = System.currentTimeMillis() - turnTime;
			System.out.println("Turn over after " + turnTime + "ms\n" + b);
			try {
				Thread.sleep(Math.max(0, 0 - turnTime));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		gameTime = System.currentTimeMillis() - gameTime;
		System.out.println("Game Over! " + b.winner() + " wins!");
		System.out.println("Game took " + gameTime + "ms and had a total of " + turns + " turns");
	}
	
	public static void testing() {
		Board b = new Board(8,8, false);
		b.insertPiece(new Queen(3,0, Side.WHITE));
		b.insertPiece(new Rook(2,7, Side.WHITE));
		
		b.insertPiece(new Knight(6,7, Side.BLACK));
		b.insertPiece(new Pawn(2,1, Side.BLACK));
		System.out.println(b);
		
		AI player1 = new AI(Side.WHITE);
		player1.takeTurn(b,3);
		System.out.println(b);
	}

	/*
	 * 
------------------------------------------------------------------------------------------------------------------------------------------
|7           null |           null |    W-Rook(2,7) |           null |           null |           null |  B-Knight(6,7) |    B-Rook(7,7) |
|6           null |    B-Pawn(1,6) |    B-Pawn(2,6) |    B-Pawn(3,6) |    B-King(4,6) |    B-Pawn(5,6) |    B-Pawn(6,6) |    B-Pawn(7,6) |
|5           null |           null |           null |           null |           null |           null |           null |           null |
|4           null |           null |           null |           null |           null |           null |           null |           null |
|3           null |           null |           null |           null |           null |           null |           null |           null |
|2           null |           null |           null |    W-Pawn(3,2) |    W-Pawn(4,2) |           null |           null |           null |
|1           null |    W-Pawn(1,1) |    B-Pawn(2,1) |           null |    W-Pawn(4,1) |           null |    W-Pawn(6,1) |    W-Pawn(7,1) |
|0           null |  W-Knight(1,0) |           null |   W-Queen(3,0) |    W-King(4,0) |  W-Bishop(5,0) |  W-Knight(6,0) |    W-Rook(7,0) |
|               0 |              1 |              2 |              3 |              4 |              5 |              6 |              7 |
------------------------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------------------------
|7           null |           null |           null |           null |           null |           null |    W-Rook(6,7) |    B-Rook(7,7) |
|6           null |    B-Pawn(1,6) |    B-Pawn(2,6) |    B-Pawn(3,6) |    B-King(4,6) |    B-Pawn(5,6) |    B-Pawn(6,6) |    B-Pawn(7,6) |
|5           null |           null |           null |           null |           null |           null |           null |           null |
|4           null |           null |           null |           null |           null |           null |           null |           null |
|3           null |           null |           null |           null |           null |           null |           null |           null |
|2           null |           null |           null |    W-Pawn(3,2) |    W-Pawn(4,2) |           null |           null |           null |
|1           null |    W-Pawn(1,1) |    B-Pawn(2,1) |           null |    W-Pawn(4,1) |           null |    W-Pawn(6,1) |    W-Pawn(7,1) |
|0           null |  W-Knight(1,0) |           null |   W-Queen(3,0) |    W-King(4,0) |  W-Bishop(5,0) |  W-Knight(6,0) |    W-Rook(7,0) |
|               0 |              1 |              2 |              3 |              4 |              5 |              6 |              7 |
------------------------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------------------------------
|7           null |           null |           null |           null |           null |           null |    W-Rook(6,7) |    B-Rook(7,7) |
|6           null |    B-Pawn(1,6) |    B-Pawn(2,6) |    B-Pawn(3,6) |    B-King(4,6) |    B-Pawn(5,6) |    B-Pawn(6,6) |    B-Pawn(7,6) |
|5           null |           null |           null |           null |           null |           null |           null |           null |
|4           null |           null |           null |           null |           null |           null |           null |           null |
|3           null |           null |           null |           null |           null |           null |           null |           null |
|2           null |           null |           null |    W-Pawn(3,2) |    W-Pawn(4,2) |           null |           null |           null |
|1           null |    W-Pawn(1,1) |           null |           null |    W-Pawn(4,1) |           null |    W-Pawn(6,1) |    W-Pawn(7,1) |
|0           null |  W-Knight(1,0) |           null |    B-Pawn(3,0) |    W-King(4,0) |  W-Bishop(5,0) |  W-Knight(6,0) |    W-Rook(7,0) |
|               0 |              1 |              2 |              3 |              4 |              5 |              6 |              7 |
------------------------------------------------------------------------------------------------------------------------------------------
	 */
	
}
