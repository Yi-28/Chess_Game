package ChessGame;

/**
   Bishop can move any number of squares diagonally as long
   as it does not leap over any other chess piece.
 */

import java.util.ArrayList;
public class Bishop extends ChessPiece 
{
	//instance variable
	private ArrayList <String> movePositions;
	/**
	   Construct the bishop with the given color
	   @param color the color of the bishop
	 */
	public Bishop (String color) 
	{
		super (color);
		movePositions = new ArrayList <String> ();
	}
	
	/**
	   Get all the possible position that bishop can move to
	   @return the array list with all the possible locations
	 */
	public ArrayList <String> canMoveTo(Movement move) 
	{
		int row = super.getRow ();
		int column = super.getColumn(); 
		
		int topLeft = Math.min(row, column);
		int bottomLeft = Math.min(7 - row, column);

		movePositions.clear();
		movePositions = move.getDiagonalMoves (row - topLeft , column - topLeft, 1, this);
		movePositions.addAll(move.getDiagonalMoves (row + bottomLeft , column - bottomLeft, -1, this));
		
		return movePositions;
	}
	
	/**
	 * Get the path extension for piece image
	 */
	public String getPath () 
	{
		if (super.getColor().equals("black"))
			return "/bB" ; 
		return "/wB" ; 
	}
	
	/**
	   Get the character of the bishop
	   @return 
	 */
	public String toString () 
	{
		if (super.getColor().equals("black"))
			return "\u2657" ; 
		return "\u265D" ; 
	}
}
