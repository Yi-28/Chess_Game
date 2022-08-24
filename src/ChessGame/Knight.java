package ChessGame;

/**
   Knight can only move in L, which means two squares vertically or horizontally, 
   and one horizontal or vertical.
 */

import java.util.ArrayList;

public class Knight extends ChessPiece 
{
	//instance variable
	private ArrayList <String> movePositions;

	/**
	   Constructs the knight with the given color
	   @param color the color of the knight piece
	 */
	public Knight (String color) 
	{
		super (color);
		movePositions = new ArrayList <String> ();
	}

	/**
	   Get all the possible positions that knight can move to
	   @return the array list with all the possible locations
	 */
	public ArrayList <String> canMoveTo(Movement move) 
	{
		movePositions.clear();
		int possibleRow [] =  {1, 2, -1, -2, 1, 2, -1, -2};
		int possibleCol [] =  {2, 1, -2, -1, -2, -1, 2, 1};
		int row = super.getRow ();
		int column = super.getColumn(); 
		

		for (int index = 0 ; index < 8; index++)
		{
			int tempCol = column + possibleCol [index];
			int tempRow = row + possibleRow [index];
			if (tempCol >= 0 && tempCol < 8 && tempRow >= 0 && tempRow < 8) 
			{
				addMove (move.getBoard() ,tempRow, tempCol);
			}
		}
		return movePositions;
	}
	
	/**
	 * Add move into possible moves list
	 * @param board the board
	 * @param row   the row
	 * @param col   the column
	 */
	private void addMove (Board board ,int row, int col) 
	{
		if (!board.isOccupied (row , col))
		{
			movePositions.add(getMove (row , col));
		}
		else if (!board.getPiece(row, col).getColor().equals(super.getColor())) 
		{
			movePositions.add(getMove (row , col));
		}
	}
	
	/**
	 * Get the path extension for piece image
	 */
	public String getPath () 
	{
		if (super.getColor().equals("black"))
			return "/bN" ; 
		return "/wN" ; 
	}
	
	/**
	   Get the character of the knight
	   @return 
	 */
	public String toString () 
	{
		if (super.getColor().equals("black"))
			return "\u2658" ; 
		return "\u265E" ; 
	}

}