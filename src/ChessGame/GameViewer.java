package ChessGame;
/**
 * This program consists of a chess game. 
 * 
 * - A two-player board game. 
 * - Each player has 16 pieces (2 rooks, 2 bishops, 2 knights, 1 king, 1 queen, and 8 pawns).
 * - Players take turns, and move one piece at a time
 * - Each piece is moved to an unoccupied square or one occupied 
 *   by the opponent’s piece that can be captured and removed from the board
 * 
 * CheckMate or Game Over:
 * - When the king of the opponent is captured and cannot escape
 * - When one player has no legal move without putting its king
 *   in danger, the other player wins. 

 * @author yizhuo
 *
 */
public class GameViewer 
{
	/**
	 * Run the chess game
	 * @param args
	 */
	public static void main(String[] args) 
	{
		GameFrame chessGame = new GameFrame();
		chessGame.run();
	}

}
