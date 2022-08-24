package ChessGame;

/**
 * A square has a number, and row, column position. 
 * It may also hold a piece.
 * 
 * @author yizhuo
 */
public class Square 
{
	//instance variables
	private final int NUMBER;   
	private final int ROW;
	private final int COL;
	
	private ChessPiece piece;
	
	/**
	 * Constructs the square with the given number, row, and column
	 * @param num    the square number
	 * @param row    the row
	 * @param col    the column
	 */
	public Square (int num, int row, int col)
	{
		ROW = row;
		COL = col;
		NUMBER = num;
		piece = null;
	}
	
	/**
	 * Set piece in square with the given piece
	 * @param cheespiece   the piece
	 */
	public void setPiece(ChessPiece cheespiece) 
	{
		piece = cheespiece; 
		piece.move(NUMBER);
	}
	
	/**
	 * Gets the row of the square
	 * @return the row
	 */
	public int getRow () 
	{
		return ROW;
	}
	
	/**
	 * Gets the column of the square
	 * @return the column
	 */
	public int getCol () 
	{
		return COL;
	}
	
	/**
	 * Gets the piece in the square
	 * @return the piece
	 */
	public ChessPiece getPiece () 
	{
		return piece;
	}
	
	/**
	 * Gets the square number
	 * @return the number
	 */
	public int getNumber () 
	{
		return NUMBER;
	}
	
	/**
	 * Gets the position of square in string
	 * @return the position
	 */
	public String getPosition () 
	{
		return "" + ROW + "" + COL;
	}
	
	/**
	 * Remove the piece from square
	 * @return the piece removed
	 */
	public ChessPiece removePiece () 
	{
		ChessPiece toRemove = piece;
		piece = null;
		return toRemove;
	}
}

