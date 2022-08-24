package ChessGame;
import java.util.ArrayList;

/**
 * Movement class controls the moves of the game in the board.
 *  - Provides the possible horizontal, vertical, and diagonal moves.
 *  - Allows special move for king  or "Castling"
 * @author yizhuo
 */
public class Movement 
{
	//instance variables
	private ChessGame game;
	private Board board;

	/**
	 * Construct movement, sets game and board
	 * @param game
	 */
	public Movement (ChessGame game) 
	{
		setBoard(game.getBoard());
		this.game = game;
	}
	
	/**
	 * Gets the board
	 * @return  the board
	 */
	public Board getBoard () 
	{
		return board;
	}
	
	/**
	 * Sets the board
	 * @param board 
	 */
	public void setBoard (Board board) 
	{
		this.board = board;
	}

	/**
	 * Get horizontal and vertical moves
	 * @param row  the row
	 * @param col  the column
	 * @return     the list of moves
	 */
	public ArrayList <String> getHorizontalVerticalMoves (int row, int col)
	{
		ChessPiece piece = new ChessPiece ("black");
		int[] range = getHVRange( board, row, col);
		ArrayList <String> moves = new ArrayList <String> ();

		for (int count = range[0]; count <= range[1]; count++) 
		{
			if (count != row)
				moves.add(piece.getMove (count, col));
		}

		for (int count = range[2]; count <= range[3]; count++) 
		{
			if (count != col)
				moves.add(piece.getMove (row, count));
		}
		return moves;
	}

    /**
     * Get the horizontal and vertical moves range
     * @param board  the board 
     * @param row    the row
     * @param col    the column
     * @return       the range in an array
     */
	private int[] getHVRange (Board board, int row, int col) 
	{
		int[] range = {0,7,0,7};
		String color = board.getPiece(row, col).getColor();
		for (int count = 0; count < 8; count++) 
		{
			if (board.isOccupied(count, col))
			{
				if (count < row && range[0] <= count) 
				{
					range[0] = count + 1;
					if (!color.equals(board.getPiece(count, col).getColor())) 
					{
						range[0] -= 1;
					}	
				}
				else if (row < count && range[1] >= count)
				{
					range[1] = count - 1;
					if (!color.equals(board.getPiece(count, col).getColor())) 
					{
						range[1] += 1;
					}
				}
			}
			if (board.isOccupied(row, count))
			{
				if (count < col && range[2] <= count) 
				{
					range[2] = count + 1;
					if (!color.equals(board.getPiece(row, count).getColor())) 
					{
						range[2] -= 1;
					}
				}
				else if (col < count && range[3] >= count)
				{
					range[3] = count - 1;
					if (!color.equals(board.getPiece(row, count).getColor())) 
					{
						range[3] += 1;
					}
				}
			}
		}
		return range;
	}

	/**
	 * Get diagonal moves
	 * @param startRow  the start row
	 * @param startCol  the start column
	 * @param direction the direction
	 * @param piece     the piece
	 * @return          the list of moves
	 */
	public ArrayList <String> getDiagonalMoves (int startRow ,int startCol, int direction, ChessPiece piece)
	{
		ArrayList <String> movePositions = new ArrayList <String> ();

		int[] range  = getDiagRange (board, startRow,  startCol, direction, piece);
		int tempRow = range[0];

		for (int count = range[2]; count <= range[3] && tempRow > -1 && tempRow < 8; count ++) 
		{
			if (tempRow != piece.getRow())
				movePositions.add(piece.getMove(tempRow, count));

			tempRow += direction;
		}

		return movePositions;
	}

    /**
     * Get the diagonal moves range
     * @param board     the board 
     * @param row       the row
     * @param col       the column
	 * @param direction the direction
     * @param piece     the piece
     * @return          the range in an array
     */
	private int[] getDiagRange (Board board, int row, int col, int direction, ChessPiece piece) 
	{
		int[] range = {row,7,col,7};
		if(direction < 0) 
		{
			int[] range1 = {row,0,col,7};
			range = range1;
		}
		int tempRow = row;
		int pieceRow= piece.getRow();
		String color = piece.getColor();

		for (int count = col; count < 8 && tempRow < 8 && tempRow > -1; count++ ) 
		{
			if (board.isOccupied(tempRow, count) && tempRow != pieceRow)
			{
				if (direction > 0) 
				{
					if (tempRow < pieceRow && range[0] <= tempRow )
					{
						range[0] = tempRow + direction;		
						range[2] = count + 1;
						if (!color.equals(board.getPiece(tempRow, count).getColor())) 
						{
							range[0] -= direction;		
							range[2] -= 1;
						}
					}

					else if (tempRow > pieceRow  && range[1] >= tempRow)
					{
						range[1] = tempRow - direction;
						range[3] = count - 1;
						if (!color.equals(board.getPiece(tempRow, count).getColor())) 
						{
							range[1] +=  direction;
							range[3] += 1;
						}
					}
				}
				else 
				{
					if (tempRow > pieceRow && range[0] >= tempRow) {
						range[0] = tempRow + direction;
						range[2] = count + 1;
						if (!color.equals(board.getPiece(tempRow, count).getColor())) 
						{
							range[0] -=  direction;
							range[2] -= 1;
						}
					}
					else if (tempRow < pieceRow  && range[1] <= tempRow) {
						range[1] = tempRow - direction;
						range[3] = count - 1;
						if(!color.equals(board.getPiece(tempRow, count).getColor())) 
						{
							range[1] += direction;
							range[3] += 1;
						}
					}
				}
			}
			tempRow += direction;
		}
		return range;
	}

	/**
	 * Gets king side castle
	 * @param king the king
	 * @return     the castle position
	 */
	public String isKingSideCastle (King king)
	{
		String move = "";
		if (king.getColor().equals("white") && canCastleKingSide(7)) 
		{
			Rook rook = (Rook) board.getPiece(7, 7);
			move = "" + king.getRow() + "" + (king.getColumn() + 2);
			if (rook.isFirstMove() && !game.isCheck()&& game.isLegal (move)) 
			{
				return move;
			}
		}
		else if (king.getColor().equals("black") && canCastleKingSide(0)) 
		{
			Rook rook = (Rook) board.getPiece(0, 7);
			move = "" + king.getRow() + "" + (king.getColumn() + 2);
			if (rook.isFirstMove() && !game.isCheck()&& game.isLegal (move)) 
			{
				return move;
			}
		}
		return move;
	}

	/**
	 * Gets queen side castle
	 * @param king the king
	 * @return     the castle position
	 */
	public String isQueenSideCastle (King king)
	{
		String move = "";

		if (king.getColor().equals("white")&& canCastleQueenSide(7)) 
		{
			Rook rook = (Rook) board.getPiece(7, 0);
			move = "" + king.getRow() + "" + (king.getColumn() - 2);
			if (rook.isFirstMove() && !game.isCheck()&& game.isLegal (move)) 
			{
				return move;
			}
		}
		else if (king.getColor().equals("black")&& canCastleQueenSide(0)) 
		{
			Rook rook = (Rook) board.getPiece(0, 0);
			move = "" + king.getRow() + "" + (king.getColumn() - 2);
			if (rook.isFirstMove() && !game.isCheck()&& game.isLegal (move)) 
			{
				return move;
			}
		}
		return move;
	}

	/**
	 * Checks if the king can castle in king side
	 * @param row the row
	 * @return    true if king can castle in king side
	 */
	private boolean canCastleKingSide (int row) 
	{
		for (int kingCol = 4 + 1; kingCol < 8; kingCol++) 
		{
			if (board.isOccupied (row, kingCol) && kingCol != 7) 
			{
				return false;
			}
			else if (!board.isOccupied (row, kingCol) && kingCol ==7) 
			{
				return false;
			}
		}
		if (board.getPiece(row,7).getClass().getName().equals("ChessGame.Rook")) 
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the king can castle in queen side
	 * @param row the row
	 * @return    true if king can castle in queen side
	 */
	private boolean canCastleQueenSide (int row) 
	{
		for (int kingCol = 4 - 1; kingCol > -1; kingCol--) 
		{
			if (board.isOccupied (row, kingCol) && kingCol != 0) 
			{
				return false;
			}
			else if (!board.isOccupied (row, kingCol) && kingCol == 0) 
			{
				return false;
			}
		}
		if (board.getPiece(row,0).getClass().getName().equals("ChessGame.Rook")) 
		{
			return true;
		}
		return false;
	}
}
