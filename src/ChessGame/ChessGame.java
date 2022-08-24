package ChessGame;
import java.util.ArrayList;

/**
 * Chess game is a two-player board game. Each player has 16 pieces
 * (2 rooks, 2 bishops, 2 knights, 1 king, 1 queen, and 8 pawns).
 * - Players take turns, and move one piece at a time
 * - Each piece is moved to an unoccupied square or one occupied 
 *   by the opponent’s piece that can be captured and removed from the board
 * 
 * CheckMate or Game Over:
 * - When the king of the opponent is captured and cannot escape
 * - When one player has no legal move without putting its king
 *   in danger, the other player wins. 
 *   
 * @author yizhuo
 */
public class ChessGame 
{
	//instance variables
	final private int MAX_ROW = 7;
	final private String WHITE = "white";
	final private String BLACK = "black";
	final private ArrayList <ChessPiece> whitePieces; 
	final private ArrayList <ChessPiece> blackPieces; 

	private Board chessBoard;
	private Player whitePlayer;
	private Player blackPlayer;
	private Player currentPlayer;
	private Movement move;
	private boolean gameOver;
	private String lastMove;

	/**
	 * Construct the chess game and initialize the variables
	 */
	public ChessGame () 
	{
		chessBoard = new Board ();
		whitePieces = new ArrayList <ChessPiece> ();
		blackPieces = new ArrayList <ChessPiece> ();
		move = new Movement (this);
		initializeGameBoard (chessBoard);
		whitePlayer = new Player (this,whitePieces, WHITE);
		currentPlayer = null;
		setCurrentPlayer (whitePlayer);
		blackPlayer = new Player (this,blackPieces, BLACK);
		gameOver = false;
		lastMove = "";
	}

	/**
	 * Get check status of game
	 * @return the check status
	 */
	public boolean isCheck () 
	{
		return currentPlayer.isInCheck();
	}

	/**
	 * Get check mate status of game
	 * @return  the game status
	 */
	public boolean isCheckMate () 
	{
		gameOver = currentPlayer.isCheckMate();
		return gameOver;
	}

	/**
	 * Verifies if the given move is legal 
	 * (unoccupied or occupied by opponent's piece)
	 * @param move the move
	 * @return     true if is legal
	 */
	public boolean isLegal(String move) 
	{
		return currentPlayer.isLegalMove (move);
	}

	/**
	 * Get opponent's legal moves
	 * @return the opponent moves
	 */
	public ArrayList<String> getOpponentLegalMoves () 
	{
		if (whitePlayer != currentPlayer) 
		{
			return whitePlayer.getAllLegalMoves();
		}
		return blackPlayer.getAllLegalMoves();
	}

	/**
	 * Get the opponent player
	 * @return the opponent
	 */
	public Player getOpponent () 
	{
		if (whitePlayer != currentPlayer) 
		{
			return whitePlayer;
		}
		return blackPlayer;
	}
	
	/**
	 * Set board
	 * @param board
	 */
	public void setBoard (Board board) 
	{
		chessBoard = board;
	}

	/**
	 * Gets the board
	 * @return the board
	 */
	public Board getBoard () 
	{
		return chessBoard;
	}

	/**
	 * Access to the piece movements
	 * @return the move class
	 */
	public Movement movementAccess () 
	{
		return move;
	}

	/**
	 * Set the piece to move
	 * @param piece the piece to move
	 */
	public void setPiece2Move (ChessPiece piece) 
	{
		currentPlayer.setPiece(piece);
	}

	/**
	 * Checks if the square is a possible for current piece
	 * @param squareNum the square number
	 * @return          true if is possible move
	 */
	public boolean isPossibleMove (int squareNum) 
	{
		int row = squareNum/8;
		int col = squareNum%8;
		String coordinates = "" + row+ "" + col;
		return (currentPlayer.getCurrentLegalMoves().contains(coordinates));
	}

	/**
	 * Make a move from the source square to destination
	 * @param source  the source square
	 * @param dest    the destination
	 * @return        true if move is done
	 */
	public boolean makeMove (Square source, Square dest) 
	{
		if (!gameOver) 
		{
			currentPlayer.setPiece(source.getPiece());
			ArrayList <String> moves = currentPlayer.getCurrentLegalMoves(); 

			int row = dest.getRow();
			int col = dest.getCol();
			String checkMove = dest.getPosition();

			if (moves.contains(checkMove)) 
			{
				ChessPiece piece = chessBoard.movePiece(source, row, col);
				if (piece != null && !piece.getColor().equals(currentPlayer.getColor())) 
				{
					getOpponent().removedPiece(piece);			
				}
				lastMove = checkMove;
				nextMovePlayer ();
				return true;
			}	
		}
		return false;
	}
	
	/**
	 * Get the last move coordinates
	 * @return
	 */
	public String getLastMove ()
	{
		return lastMove;
	}
	
	/**
	 * Set next move player
	 */
	private void nextMovePlayer () 
	{
		if (getCurrentPlayerColor() == WHITE) 
		{
			setCurrentPlayer (blackPlayer); 
		}
		else 
		{
			setCurrentPlayer (whitePlayer); 
		}
	}

	/**
	 * Set current player
	 * @param play
	 */
	private void setCurrentPlayer (Player play) 
	{
		currentPlayer = play;
	}

	/**
	 * Gets the piece color of current player
	 * @return the color
	 */
	public String getCurrentPlayerColor () 
	{
		return currentPlayer.getColor();
	}

	/**
	 * Initialize the game board
	 */
	private void initializeGameBoard (Board chessBoard) 
	{
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

		for (int index = 0 ; index <= MAX_ROW; index++) 
		{
			chessBoard.setPiece(MAX_ROW, index, whitePieces.get(index));
			chessBoard.setPiece(firstRow, index, blackPieces.get(index));
		}

		ArrayList <ChessPiece> wPawns = new ArrayList <ChessPiece> ();
		ArrayList <ChessPiece> bPawns = new ArrayList <ChessPiece> ();
		for (int col = 0; col < 8; col++) 
		{
			wPawns.add(new Pawn(WHITE));
			chessBoard.setPiece(MAX_ROW - 1, col,wPawns. get(col));

			bPawns.add(new Pawn(BLACK));
			chessBoard.setPiece(1, col, bPawns.get(col));
		}
		whitePieces.addAll(wPawns);
		blackPieces.addAll(bPawns);
	}
}
