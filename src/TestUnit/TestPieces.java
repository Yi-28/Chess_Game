package TestUnit;

import ChessGame.Bishop;
import ChessGame.Board;
import ChessGame.ChessGame;
import ChessGame.ChessPiece;
import ChessGame.King;
import ChessGame.Knight;
import ChessGame.Movement;
import ChessGame.Pawn;
import ChessGame.Queen;
import ChessGame.Rook;

/**
 * Test the pieces by printing the in the console
 * @author yizhuo
 */
public class TestPieces 
{
	//instance variables
	private TestBoard testBoard ;
	private final String WHITE = "white";
	private final String BLACK = "black";
	private Movement move;
	
	/**
	 * Construct the class
	 */
	public TestPieces () 
	{
		ChessGame game = new ChessGame();
		testBoard = new TestBoard ();
		move = new Movement (game);
	}
	
	/**
	 * Test the moves for the pawn 
	 */
	public void testPawnMoves () 
	{
		System.out.println ("Test Pawn Moves");
		ChessPiece piece = new Pawn (WHITE);
		Board board = new Board ();
		move.setBoard(board);

		System.out.println (" Pawn at 2e");
		board.setPiece(6, 4, piece);
		
		piece = new Pawn (BLACK);
		Pawn p = (Pawn) piece;
		p.setFirstMove();
		board.setPiece(3, 4, piece);
		testBoard.displayMoves (piece.canMoveTo(move),piece, board); 
		
		System.out.println ("Move two squares from 2e to 4e");
		board.movePiece(board.getSquare(6, 4), 4, 4);
		testBoard.displayBoard (board); 

		System.out.println ("Get moves for pawn at 4e");
		testBoard.displayMoves (piece.canMoveTo(move), piece, board); 
		System.out.println ("Expected: No moves ");

		System.out.println ("Pawn gets to opponent side");
		board.movePiece(board.getSquare(4, 4), 0, 4);
		testBoard.displayBoard (board); 
		System.out.println ("Expected: Promoted to queen");
		
		piece = new Knight (WHITE);
		board.setPiece(4, 5, piece);
		testBoard.displayBoard (board); 

		System.out.println ("Pawn can only capture diagonally");
		testBoard.displayMoves (p.canMoveTo(move), piece, board); 
	}
	
	/**
	 * Test the moves for the queen 
	 */
	public void testQueenMoves () 
	{
		System.out.println ("Test Queen Moves");
		ChessPiece piece = new Queen (BLACK);
		Board board = new Board ();
		move.setBoard(board);
		
		System.out.println (" Queen at 8d");
		board.setPiece(0, 3, piece);
		testBoard.displayBoard (board); 
		testBoard.displayMoves (piece.canMoveTo(move), piece, board); 
		
		System.out.println ("Move two diagonally from 8d to 6f");
		board.movePiece(board.getSquare(0, 3), 2, 5);
		testBoard.displayBoard (board); 
		System.out.println ("Get moves for queen at 6f");
		testBoard.displayMoves (piece.canMoveTo(move), piece, board); 
	
		ChessPiece queen = piece;
		piece = new Knight (WHITE);
		board.setPiece(6, 1, piece);
		piece = new Pawn (BLACK);
		board.setPiece(2, 1, piece);
		testBoard.displayBoard (board); 

		System.out.println ("Queen cannot leap over other pieces ");
		testBoard.displayMoves (queen.canMoveTo(move), piece, board); 
		System.out.println ("Expected: Queen can attack opponent and cannot leap");
	}
	
	/**
	 * Test the moves for the rook 
	 */
	public void testRookMoves () 
	{
		System.out.println ("Test Rook Moves");
		ChessPiece rook = new Rook (BLACK);
		Board board = new Board ();
		move.setBoard(board);
		
		System.out.println (" Rook at 8a");
		board.setPiece(0, 0, rook);
		testBoard.displayBoard (board); 
		testBoard.displayMoves (rook.canMoveTo(move), rook, board); 
		
		System.out.println ("Move two vertically from 8a to 4a");
		board.movePiece(board.getSquare(0, 0), 4, 0);
		testBoard.displayBoard (board); 
		System.out.println ("Get moves for rook at 4a");
		testBoard.displayMoves (rook.canMoveTo(move), rook, board); 
	
		System.out.println ("Move two horizontally from 4a to 4c");
		board.movePiece(board.getSquare(4, 0), 4, 2);
		testBoard.displayBoard (board); 
		System.out.println ("Get moves for rook at 4c");
		testBoard.displayMoves (rook.canMoveTo(move), rook, board); 
	
		ChessPiece piece = new Knight (WHITE);
		board.setPiece(4, 1, piece);
		piece = new Pawn (BLACK);
		board.setPiece(2, 2, piece);
		testBoard.displayBoard (board); 

		System.out.println ("Rook cannot leap over other pieces ");
		testBoard.displayMoves (rook.canMoveTo(move), piece, board); 
		System.out.println ("Expected: Rook can attack opponent and cannot leap");

	}
	
	/**
	 * Test the moves for the bishop 
	 */
	public void testBishopMoves () 
	{
		System.out.println ("Test Bishop Moves");
		ChessPiece piece = new Bishop (WHITE);
		Board board = new Board ();
		move.setBoard(board);
		
		System.out.println (" Bishop at 8d");
		board.setPiece(0, 3, piece);
		testBoard.displayBoard (board); 
		testBoard.displayMoves (piece.canMoveTo(move), piece, board); 
		
		System.out.println ("Move two diagonally from 8d to 6f");
		board.movePiece(board.getSquare(0, 3), 2, 5);
		testBoard.displayBoard (board); 
		System.out.println ("Get moves for bishop at 6f");
		testBoard.displayMoves (piece.canMoveTo(move), piece, board); 
	
		ChessPiece queen = piece;
		piece = new Knight (WHITE);
		board.setPiece(6, 1, piece);
		piece = new Pawn (BLACK);
		board.setPiece(4, 7, piece);
		testBoard.displayBoard (board); 

		System.out.println ("Bishop cannot leap over other pieces ");
		testBoard.displayMoves (queen.canMoveTo(move), piece, board); 
		System.out.println ("Expected: Bishop can attack opponent and cannot leap");
	}
	
	/**
	 * Test the moves for the knight 
	 */
	public void testKnightMoves () 
	{
		System.out.println ("Test Knight Moves");
		ChessPiece knight = new Knight (WHITE);
		Board board = new Board ();
		move.setBoard(board);
		
		System.out.println (" Knight at 4d");
		board.setPiece(4, 3, knight);
		testBoard.displayBoard (board); 
		testBoard.displayMoves (knight.canMoveTo(move), knight, board); 
		
		System.out.println ("Move from 4d to 4a");
		board.movePiece(board.getSquare(4, 3), 6, 2);
		testBoard.displayBoard (board); 
		System.out.println ("Get moves for knight at 6c");
		testBoard.displayMoves (knight.canMoveTo(move), knight, board); 
	
		ChessPiece piece = new Knight (WHITE);
		board.setPiece(6, 1, piece);
		piece = new Pawn (BLACK);
		board.setPiece(4, 3, piece);
		testBoard.displayBoard (board); 

		System.out.println ("Knight cannot leap over other pieces ");
		testBoard.displayMoves (knight.canMoveTo(move), piece, board); 
		System.out.println ("Expected: Knight can attack opponent and leap");
	}
	
	/**
	 * Test the moves for the king 
	 */
	public void testKingMoves () 
	{
		System.out.println ("Test King Moves");
		ChessPiece king = new King (WHITE);
		Board board = new Board ();
		move.setBoard(board);
		
		System.out.println (" King at 3c");
		board.setPiece(3, 2, king);
		testBoard.displayBoard (board); 
		testBoard.displayMoves (king.canMoveTo(move), king, board); 
		
		System.out.println ("Move from 3c to 4d");
		board.movePiece(board.getSquare(3, 2), 4, 3);
		testBoard.displayBoard (board); 
		System.out.println ("Get moves for king at 4d");
		testBoard.displayMoves (king.canMoveTo(move), king, board); 
	
		ChessPiece piece = new Rook (BLACK);
		board.setPiece(0, 7, piece);
		piece = new King (BLACK);
		board.setPiece(0, 4, piece);
		testBoard.displayBoard (board); 
		System.out.println ("King can castle king side if is first move ");
		testBoard.displayMoves (piece.canMoveTo(move), piece, board); 
	
		board.movePiece(board.getSquare(0, 4), 0, 6);
		testBoard.displayBoard (board); 
	}
}
