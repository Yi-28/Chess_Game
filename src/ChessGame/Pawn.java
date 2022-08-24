package ChessGame;
/**
   Pawn may move one or two squares vertically for their first move only.
   Then, starting their second move they can only one square forward. 
 */

import java.util.ArrayList;

public class Pawn extends ChessPiece 
{
	//instance variables
	private boolean firstMove;    //flag for first move 
	private int canMove;          
	private ArrayList <String> movePositions;
	
	/**
	   Constructs the pawn with the given color
	   @param color the color of the piece
	 */
	public Pawn (String color) 
	{
		super (color);
		firstMove = true;
		movePositions = new ArrayList <String> ();
		canMove = -1;
		if (color == "black")
			canMove = 1;
	}

	/**
	   Get all the possible positions that pawn can move to
	   @return the array list with all the possible locations
	 */
	public ArrayList <String> canMoveTo(Movement move)  
	{
		int row = super.getRow ();
		int column = super.getColumn(); 

		int tempRow = row + canMove;
		int tempCol = column - 1;

		movePositions.clear();
		if (firstMove) 
		{
			addMove ( move.getBoard(),tempRow, column);
			addMove ( move.getBoard() ,tempRow + canMove, column);
		}

		int count = 0;
		while (count < 3) 
		{
			if (tempCol >= 0 && tempCol < 8) 
			{
				addMove (move.getBoard() , tempRow,  tempCol);
			}
			tempCol ++;
			count ++;
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
		if (!board.isOccupied (row , col)
				&&col == super.getColumn()) 
		{ 
			movePositions.add(getMove (row , col));
		}
		else if (col != super.getColumn() && board.isOccupied (row , col)
				&&!board.getPiece(row, col).getColor().equals(super.getColor()))
		{
			movePositions.add(getMove (row , col));
		}
	}
	
	/**
	 * Promote pawn when it gets to opponent site 8th rank
	 * @param board the board
	 * @param row   the row
	 * @param col   the column
	 */
	public void promote (Board board, int row, int col) 
	{
		board.setPiece(row, col, new Queen (super.getColor()));
	}

	/**
	 * Set first move to false
	 */
	public void setFirstMove () 
	{
		firstMove = false;
	}

	/**
	 * Get the path extension for piece image
	 */
	public String getPath () 
	{
		if (super.getColor().equals("black"))
			return "/bP" ; 
		return "/wP" ; 
	}
	
	/**
	   Get the character of the pawn
	   @return 
	 */
	public String toString () 
	{
		if (super.getColor().equals("black"))
			return "\u2659" ; 
		return "\u265F" ; 
	}
}
