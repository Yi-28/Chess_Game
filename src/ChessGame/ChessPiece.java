package ChessGame;

/**
    ChessPiece is the parent of chess pieces 
    (queen, king, bishop, knight, rook, and pawn). 
    It stores the position of the piece, and its color.
 */

import java.util.*;

public class ChessPiece 
{
	//instance variables
	private int row;       //row (1-8)
	private int column;    //column 
	private String color;  //color (black or white)

	/**
	    Constructs the chess piece with the color given
	    @param color   the color of the chess piece
	 */
	public ChessPiece (String color) 
	{
		this.color = color;
	}

	/**
	    Set the move of the chess piece
	    @param coordinates  the coordinates of the chess piece
	 */
	public void move (int coordinates) 
	{
		row    = coordinates/8;
		column = coordinates%8;
	}

	/**
	    Get the row of the chess piece position
	 */
	public int getRow () 
	{
		return row;
	}

	/**
    	Get the column of the chess piece position
	 */
	public int getColumn () 
	{
		return column;
	}

	/** 
	    Get all possible move  
	    that the chess piece can move to in a array list 
	    @return the array list with the possible positions
	 */
	public ArrayList <String> canMoveTo (Movement move) 
	{
		return null;
	}

	/**
	    Get the chess piece in Unicode
	    @return the string with the character of the chess piece
	 */
	public String toString() 
	{
		return null;
	}
	
	/**
	 * Gets the path for piece image
	 * @return
	 */
	public String getPath() 
	{
		return null;
	}

	/**
	   Get the color of the chess piece
	   @return the color of the chess piece
	 */ 
	public String getColor () 
	{
		return color;
	}

	/**
	    Get the position of the chess piece
	    @return the position of the chess piece
	 */
	public String getPosition () 
	{
		String position = "";
		int col = column + 'a';
		switch (col) 
		{
		case 'a': position += "a"; break;
		case 'b': position += "b"; break;		
		case 'c': position += "c"; break;		
		case 'd': position += "d"; break;		
		case 'e': position += "e"; break;		
		case 'f': position += "f"; break;		
		case 'g': position += "g"; break;
		case 'h': position += "h"; break;
		}
		position += "" + row;
		return position;
	}
	
	/**
	 * Gets the coordinates of the piece
	 * @return  the coordinate in string
	 */
	public String getCoordinates () 
	{
		return "" + row + "" + column;
	}

	/**
	   Get the move in string with given row and column
	   @param row the row of the move
	   @param col the column of the move
	   @return the position in string
	 */
	public String getMove (int row, int col) 
	{
		String move = "" + row + "" + col;
		return move;
	}

	/**
	   Get the diagonal moves with a start row, column, and how can it move
	   @param startRow  the start row of the diagonal
	   @param startCol  the start column of the diagonal
	   @param move      the ascendent of descendant diagonal
	   @return  the array list with the positions of the diagonal
	 */
	public ArrayList <String> diagonalMoves (int startRow ,int startCol, int move)
	{
		ArrayList <String> movePositions = new ArrayList <String> ();
		int tempRow = startRow;
		int tempCol = startCol;

		for (int count = 0; count  <8; count ++) 
		{
			if (tempRow >= 0 && tempRow < 8 && tempCol >= 0 && tempCol < 8) 
			{
				movePositions.add(getMove(tempRow, tempCol));
			}
			tempRow += move;
			tempCol++;
		}

		return movePositions;
	}
}
