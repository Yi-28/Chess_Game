package ChessGame;

/**
 * Board has a 8x8 square board to set, move, and get piece.
 * It gets and checks if a square is occupied and selected. 
 *  
 * @author yizhuo
 */
public class Board 
{
	//instance variables
	private final int ROW_COL_SIZE = 8;

	private Square[][] board;
	private boolean squareSelected;

	/**
	 * Construct and initialize the board with row and column size
	 */
	public Board() 
	{
		board = new Square[ROW_COL_SIZE][ROW_COL_SIZE];
		initializeBoard();
		squareSelected = false;
	}

	/**
	 * Set piece at given row, column 
	 * @param row    the row
	 * @param col	 the column
	 * @param piece  the piece
	 */
	public void setPiece (int row, int col, ChessPiece piece) 
	{
		getSquare(row,col).setPiece(piece);
	}

	/**
	 * Move piece from square to the destination row and column
	 * @param move        
	 * @param srcSquare  the square
	 * @param destRow	 the destination row
	 * @param destCol    the destination column
	 * @return           the piece in destination 
	 */
	public ChessPiece movePiece (Square srcSquare, int destRow, int destCol) 
	{
		ChessPiece movedPiece = srcSquare.getPiece();
		ChessPiece removed = null;

		int row = srcSquare.getRow();
		int col = srcSquare.getCol();

		if (isOccupied (destRow,destRow)) 
		{
			removed = getSquare(destRow,destCol).removePiece();
		}
		getSquare(destRow,destCol).setPiece(movedPiece);

		if (movedPiece.getClass().getName() == "ChessGame.Pawn") 
		{
			Pawn pawn = (Pawn)movedPiece;
			pawn.setFirstMove();
			if (destRow == 0 || destRow == 7) 
			{
				pawn.promote(this, destRow, col);
			}
		}
		else if (movedPiece.getClass().getName() == "ChessGame.King")
		{
			King king = (King)movedPiece;

			if (col == destCol - 2) 
			{	
				king.kingSideCastle (this, row, destCol-1);
			}
			else if (col == destCol + 2) 
			{
				king.queenSideCastle (this,row, destCol+1);
			}
			king.setFirstMove();
		}
		getSquare(row,col).removePiece();
		return removed;
	}

	/**
	 * Checks if a square is occupied
	 * @param row  the row
	 * @param col  the column
	 * @return     true if is occupied 
	 */
	public boolean isOccupied (int row, int col) 
	{
		if  (getPiece (row, col) == null) 
		{
			return false;
		}
		return true;
	}

	/**
	 * Get piece at given row and column
	 * @param row  the row
	 * @param col  the column
	 * @return     the piece
	 */
	public ChessPiece getPiece (int row, int col) 
	{
		return getSquare(row,col).getPiece();
	}

	/**
	 * Get square at given row and column
	 * @param row  the row
	 * @param col  the column
	 * @return     the square
	 */
	public Square getSquare (int row, int col) 
	{
		return board[row][col];
	}

	/**
	 * Checks if square is selected
	 * @return  squareSelected
	 */
	public boolean isSquareSelected () 
	{
		return squareSelected;
	}

	/**
	 * Initialize board
	 */
	private void initializeBoard() 
	{
		for (int row = 0; row < ROW_COL_SIZE; row++) 
		{
			for (int col = 0; col < ROW_COL_SIZE; col++) 
			{
				board[row][col] = new Square(row*8 + col, row, col);
			}
		}
	}

}
