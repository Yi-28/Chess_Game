package ChessGame;

/**
   Rook can move any number of squares horizontally or vertically,
   forward or backward.
 */
import java.util.ArrayList;

public class Rook extends ChessPiece 
{
	private boolean firstMove;

	/**
	   Constructs the rook with the given color
	   @param color the color of the rook piece
	 */
	public Rook (String color) 
	{
		super (color);
		firstMove = true;
	}

	/**
	   Get all the possible positions that rook can move to
	   @return the array list with all the possible locations
	 */
	public ArrayList <String> canMoveTo(Movement move) 
	{
		int row = super.getRow ();
		int column = super.getColumn(); 

		return move.getHorizontalVerticalMoves(row, column);
	}

	/**
	 * Set first move to false
	 */
	public void setFirstMove () 
	{
		firstMove = false;
	}
	
	/**
	 * Get first move flag
	 */
	public boolean isFirstMove () 
	{
		return firstMove;
	}
	
	/**
	 * Get the path extension for piece image
	 */
	public String getPath () 
	{
		if (super.getColor().equals("black"))
			return "/bR"; 
		return "/wR"; 
	}
	/**
	   Get the character of the rook
	   @return 
	 */
	public String toString () 
	{
		if (super.getColor().equals("black"))
			return "\u2656"; 
		return "\u265C"; 
	}


}
