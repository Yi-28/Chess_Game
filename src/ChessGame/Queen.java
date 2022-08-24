package ChessGame;

/**
   Queen can move any number of squares vertically, horizontally, and diagonally.
 */
import java.util.ArrayList;

public class Queen extends ChessPiece 
{
	ArrayList <String> movePositions;
	/**
	   Constructs the queen with the given color
	   @param color the color of the queen piece
	 */
	public Queen (String color) 
	{
		super (color);
		movePositions = new ArrayList <String> ();
	}

	/**
	   Get all the possible positions that queen can move to
	   @return the array list with all the possible locations
	 */
	public ArrayList <String> canMoveTo(Movement move) 
	{
		int row = super.getRow ();
		int column = super.getColumn(); 

		int topLeft = Math.min(row, column);
		int bottomLeft = Math.min(7 - row, column);		
		movePositions.clear();
		movePositions = move.getHorizontalVerticalMoves(row, column);
		movePositions.addAll(move.getDiagonalMoves (row - topLeft , column - topLeft, 1, this));
		movePositions.addAll(move.getDiagonalMoves (row + bottomLeft , column - bottomLeft, -1, this));

		return movePositions;
	}

	/**
	 * Get the path extension for piece image
	 */
	public String getPath () 
	{
		if (super.getColor().equals("black"))
			return "/bQ" ; 
		return "/wQ" ; 
	}

	/**
	   Get the character of the queen
	   @return 
	 */
	public String toString () 
	{
		if (super.getColor().equals("black"))
			return "\u2655" ; 
		return "\u265B" ; 
	}
}
