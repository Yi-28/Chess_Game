package ChessGame;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Player has a set of 16 pieces (white or black), 
 * a current piece in play, its king, and its check status
 * 
 * @author yizhuo
 */
public class Player 
{
	//instance variables
	private final ChessGame GAME;
	private final String PIECE_COLOR;
	private ChessPiece currentPiece;
	private ArrayList<String> currentMoves; 
	private ArrayList<ChessPiece> activePieces; 
	private ArrayList<ChessPiece> removedPieces; 
	private ArrayList<String> opponentLegalMoves;
	private King playerKing;
	private Boolean isInCheck;

	/**
	 * Construct the player in game with given pieces and color
	 * @param game   the game
	 * @param pieces the pieces
	 * @param color  the color
	 */
	public Player (ChessGame game, ArrayList<ChessPiece> pieces, String color) 
	{
		GAME         = game;
		PIECE_COLOR  = color;
		activePieces = pieces;
		currentMoves = null;
		currentPiece = null;
		removedPieces = new ArrayList<ChessPiece> ();
		isInCheck     = false;
		playerKing    = setKing();
	}

	/**
	 * Remove the given piece from its active pieces
	 * @param piece  the piece
	 */
	public void removedPiece (ChessPiece piece) 
	{
		activePieces.remove(piece);
		removedPieces.add(piece);
	} 

	/**
	 * Checks its check status
	 * @return its check status
	 */
	public boolean isInCheck () 
	{
		opponentLegalMoves = GAME.getOpponentLegalMoves();
		isInCheck = opponentLegalMoves.contains(playerKing.getCoordinates());
		playerKing.setInCheck(isInCheck);
		return isInCheck;
	}

	/**
	 * Check if is check mate
	 * @return the status
	 */
	public boolean isCheckMate () 
	{
		if (isInCheck ())
			return !hasEscapeMoves();
		return isInCheck;
	}

	/**
	 * Find its escape moves
	 * @return the escape moves
	 */
	private boolean hasEscapeMoves () 
	{
		ArrayList<String> kingEscape = new ArrayList<String> ();
		ArrayList<String> possibleEscape = playerKing.canMoveTo(GAME.movementAccess ());
		
		int row , col = 0;
		int attack = 1;
		int blockAttack = canBlockAttack (getAllLegalMoves()).size() - possibleEscape.size();
		
		for (String move: possibleEscape) 
		{
			row = Integer.valueOf(move)/10;
			col = Integer.valueOf(move)%10;
			if(!opponentLegalMoves.contains(move) ||
					GAME.getBoard().isOccupied(row, col)) 
			{
				kingEscape.add(move);
			}
			attack += Collections.frequency(opponentLegalMoves, move); 
		}
		playerKing.setEscapeMoves (kingEscape);
				
		if (attack <= blockAttack) 
		{	
			return true;
		}
		
		if (possibleEscape.contains(GAME.getLastMove())||(attack < 3 &&
				GAME.getOpponent().getCurrentLegalMoves().containsAll(possibleEscape)==false)) 
		{
			return true;
		}
		return false;
	}

	/**
	 * Find escape by blocking the check
	 * @param toBlock  the possible blocks
	 * @return         the list of moves
	 */
	private ArrayList<String> canBlockAttack (ArrayList<String> toBlock ) 
	{
		ArrayList<String> kingMoves = playerKing.canMoveTo(GAME.movementAccess ());
		ArrayList<String> block = new ArrayList<String> ();

		for (String move: toBlock) 
		{
			if (kingMoves.contains(move))
			{
				block.add(move);
			}
		}
		return block;
	}

	/**
	 * Checks if the move is legal
	 * @param move the move
	 * @return     true if move is legal
	 */
	public boolean isLegalMove (String move) 
	{
		return (!opponentLegalMoves.contains(move));	
	}

	/**
	 * Gets the player color
	 * @return the color
	 */
	public String getColor () 
	{
		return PIECE_COLOR;
	}

	/**
	 * Set the current piece in play
	 * @param piece the piece
	 */
	public void setPiece (ChessPiece piece) 
	{
		currentPiece = piece;
		if (currentPiece == playerKing) 
		{
			playerKing.setToMove(true);
		}
		currentMoves = currentPiece.canMoveTo(GAME.movementAccess ());
	}

	/**
	 * Get the current legal moves of the current piece
	 * @return the list of moves
	 */
	public ArrayList<String> getCurrentLegalMoves ()
	{
		if (isInCheck ()) 
		{
			if (currentPiece == playerKing) 
			{
				return playerKing.getEscapeMoves();
			}
			return canBlockAttack (currentMoves);
		}
		currentMoves = currentPiece.canMoveTo(GAME.movementAccess ());
		return currentMoves;
	}

	/**
	 * Get the active pieces  
	 * @return the active pieces
	 */
	public ArrayList<ChessPiece> getActivePieces ()
	{
		return activePieces;
	}

	/**
	 * Get all legal moves for all the active pieces
	 * @return the list of all moves
	 */
	public ArrayList<String> getAllLegalMoves ()
	{
		ArrayList<String>  all = new ArrayList<String> ();

		for (ChessPiece piece: activePieces) 
		{
			if (piece != playerKing) 
			{
				all.addAll(piece.canMoveTo(GAME.movementAccess ()));
			}
			else if (!isInCheck) 
			{
				all.addAll(piece.canMoveTo(GAME.movementAccess ()));
			}
		}		
		return all;
	}
	
	/**
	 * Set the king of this player
	 * @return  the king
	 */
	private King setKing () 
	{
		for (ChessPiece king: activePieces) 
		{
			if (king.getClass().getName()=="ChessGame.King")
				return (King) king;
		}
		return null;
	}
}
