package ChessGame;

/**
   King can only move one square horizontally, vertically, or diagonally.
 */

import java.util.ArrayList;

public class King extends ChessPiece 
{
	//instance variables
	private ArrayList <String> movePositions;
	private ArrayList <String> escapeMoves;
	private boolean firstMove;
	private boolean isInCheck;
	private boolean isPiece2Move;
	/**
	   Constructs the king with the given color
	   @param color the color of the king piece
	 */
	public King (String color) 
	{
		super (color);
		movePositions = new ArrayList <String> ();
		firstMove = true;
		isInCheck = false;
		isPiece2Move = false;
		escapeMoves = new ArrayList <String> ();
	}

	/**
	   Get all the possible position that king can move to
	   @return the array list with all the possible locations
	 */
	public ArrayList <String> canMoveTo(Movement move) 
	{
		movePositions.clear();
		int row = super.getRow ();
		int column = super.getColumn(); 
		
		for (int rows = (row - 1); rows <= row + 1; rows++)
		{
			for (int col = (column - 1); col <= column + 1; col++)
			{
				if (rows >= 0 && rows < 8 && col >= 0 && col < 8) 
				{
					addMove (move.getBoard() ,rows, col);
				}
			}
		}
		
		if (isFirstMove() && !isInCheck && isPiece2Move) 
		{
			String movePos = move.isKingSideCastle(this);
			if (!movePos.isEmpty()) 
			{
				movePositions.add(movePos);
			}
			
			movePos = move.isQueenSideCastle (this);
			if (!movePos.isEmpty()) 
			{
				movePositions.add(movePos);
			}
		}
		return movePositions;
	}
	
	/**
	 * Set is to move
	 * @param toMove
	 */
	public void setToMove (boolean toMove) 
	{
		isPiece2Move = toMove;
	}
	
	/**
	 * Set king in check
	 * @param check
	 */
	public void setInCheck (boolean check) 
	{
		isInCheck = check;
	}

	/**
	 * Add move to possible move list
	 * @param board
	 * @param row
	 * @param col
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
	 * Set the escape moves
	 * @param moves  the moves
	 */
	public void setEscapeMoves (ArrayList <String> moves) 
	{
		escapeMoves = moves;
	}
	
	/**
	 * Get escape moves
	 * @return  the moves
	 */
	public ArrayList <String> getEscapeMoves ()
	{
		return escapeMoves;
	}
	
	/**
	 * Set first move to false
	 */
	public void setFirstMove () 
	{
		firstMove = false;
	}
	
	/**
	 * Get the first move flag
	 * @return
	 */
	public boolean isFirstMove () 
	{
		return firstMove;
	}
	
	/**
	 * Castle in king side
	 * @param board  the board
	 * @param row    the row
	 * @param col    the column
	 */
	public void kingSideCastle (Board board, int row, int col) 
	{
		board.setPiece(row, col, board.getSquare(row, 7).removePiece());
	}
	
	/**
	 * Castle in queen side
	 * @param board  the board
	 * @param row    the row
	 * @param col    the column
	 */
	public void queenSideCastle (Board board, int row, int col)
	{
		board.setPiece(row, col, board.getSquare(row, 0).removePiece());
	}
	
	/**
	 * Get the path extension for piece image
	 */
	public String getPath () 
	{
		if (super.getColor().equals("black"))
			return "/bK" ; 
		return "/wK" ; 
	}
	
	/**
	   Get the character of the king
	   @return 
	 */
	public String toString () 
	{
		if (super.getColor().equals("black"))
			return "\u2654" ; 
		return "\u265A" ; 
	}
}
