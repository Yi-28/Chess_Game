package TestUnit;
import java.util.ArrayList;

import ChessGame.Bishop;
import ChessGame.Board;
import ChessGame.ChessPiece;
import ChessGame.King;
import ChessGame.Knight;
import ChessGame.Pawn;
import ChessGame.Queen;
import ChessGame.Rook;

/**
 * Test the board by setting the pieces and displaying in 
 * the console
 * 
 * @author yizhuo
 */
public class TestBoard 
{
	//instance variable
	private Board board;

	/**
	 * Construct the test board
	 */
	public TestBoard () 
	{
		board = new Board ();
	}

	/**
	 * Set piece at given row and column
	 * @param row   the row
	 * @param col	the column
	 * @param piece the piece
	 */
	public void setPiece (int row, int col, ChessPiece piece) 
	{
		board.setPiece(row, col, piece);
	}

	/**
	 * Display the chess board with its corresponding pieces 
	 */
	public void displayStandardBoard () 
	{
		Board standBoard = new Board ();
		System.out.println(" Standard Chess Game Board ");
		initializeStandardBoard (standBoard);
		displayBoard (standBoard);		
	}

	/**
	 * Initialize the chess board
	 * @param board the board
	 */
	public void initializeStandardBoard (Board board)
	{
		int MAX_ROW = 8;
		String WHITE = "white";
		String BLACK = "black";

		ArrayList <ChessPiece> whitePieces = new ArrayList <ChessPiece> ();; 
		ArrayList <ChessPiece> blackPieces = new ArrayList <ChessPiece> ();; 

		int firstRow = 0;
		whitePieces.add(new Rook(WHITE));
		whitePieces.add(new Knight(WHITE));
		whitePieces.add(new Bishop(WHITE));
		whitePieces.add(new Queen(WHITE));
		whitePieces.add(new King(WHITE));
		whitePieces.add(new Bishop(WHITE));
		whitePieces.add(new Knight(WHITE));
		whitePieces.add(new Rook(WHITE));

		blackPieces.add(new Rook(BLACK));
		blackPieces.add(new Knight(BLACK));
		blackPieces.add(new Bishop(BLACK));
		blackPieces.add(new Queen(BLACK));
		blackPieces.add(new King(BLACK));
		blackPieces.add(new Bishop(BLACK));
		blackPieces.add(new Knight(BLACK));
		blackPieces.add(new Rook(BLACK));

		for (int index = 0 ; index < MAX_ROW; index++) 
		{
			board.setPiece(MAX_ROW - 1, index, whitePieces.get(index));
			board.setPiece(firstRow, index, blackPieces.get(index));
		}

		ArrayList <ChessPiece> wPawns = new ArrayList <ChessPiece> ();
		ArrayList <ChessPiece> bPawns = new ArrayList <ChessPiece> ();
		for (int col = 0; col < 8; col++) 
		{
			wPawns.add(new Pawn(WHITE));
			board.setPiece(MAX_ROW - 2, col,wPawns. get(col));

			bPawns.add(new Pawn(BLACK));
			board.setPiece(1, col, bPawns.get(col));
		}
		whitePieces.addAll(wPawns);
		blackPieces.addAll(bPawns);
	}

	/**
	 * Display the board 
	 * @param board
	 */
	public void displayBoard ( Board board ) 
	{
		ChessPiece piece = null;
		System.out.println(" --------------------------");
		System.out.println("   a  b  c  d  e  f  g  h  ");

		for (int row =0 ; row < 8; row ++) 
		{
			System.out.print( 8 - row);
			for (int col = 0; col < 8; col ++) 
			{
				if (board.isOccupied(row, col)) 
				{
					piece = board.getPiece(row, col);
					System.out.print("  " + piece.toString());
				}
				else 
				{
					System.out.print ("  -");
				}
			}
			System.out.println();
		}
		System.out.printf( "%s%n%n", " --------------------------");
	}

	/**
	 * Display the board and the possible moves of the piece
	 * @param moves
	 * @param piece
	 * @param board
	 */
	public void displayMoves (ArrayList <String> moves, ChessPiece piece, Board board) 
	{
		boolean chessBoard [][] = new boolean [8][8]; 

		for (String move : moves) 
		{
			int row = Integer.valueOf(move)/10;
			int col = Integer.valueOf(move)%10;

			chessBoard [row][col] = true;
		}

		System.out.println(" --------------------------");
		System.out.println("   a  b  c  d  e  f  g  h  ");
		for (int row =0 ; row < 8; row ++) 
		{
			System.out.print( 8 - row);
			for (int col = 0; col < 8; col ++) 
			{
				if (board.isOccupied(row, col)) 
				{
					if (chessBoard[row][col]) 
					{
						System.out.print ("  x");
					}
					else 
					{
						piece = board.getPiece(row, col);
						System.out.print("  " + piece.toString());
					}
				}
				else if (chessBoard[row][col]) 
				{
					System.out.print ("  o");
				}
				else 
				{
					System.out.print ("  -");
				}
			}
			System.out.println();
		}
		System.out.printf( "%s%n%n", " --------------------------");
	}
}
