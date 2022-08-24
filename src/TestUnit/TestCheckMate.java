package TestUnit;

import ChessGame.ChessGame;

/**
 * Test check mate
 * 
 * @author yizhuo
 */
public class TestCheckMate 
{
	//instance variables
	private TestBoard testBoard = new TestBoard ();

	/**
	 * Test the Fool Mate
	 */
	public void testFoolsMate() 
	{
		System.out.println ("Test the fools Mate");
		ChessGame game = new ChessGame ();
		testBoard.displayBoard(game.getBoard());

		System.out.println ("Move f2 to f3");
		game.makeMove(game.getBoard().getSquare(6, 5),game.getBoard().getSquare(5, 5));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move e7 to e5");
		game.makeMove(game.getBoard().getSquare(1, 4),game.getBoard().getSquare(3, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move g2 to g4");
		game.makeMove(game.getBoard().getSquare(6, 6),game.getBoard().getSquare(4, 6));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move d8 to h4");
		game.makeMove(game.getBoard().getSquare(0, 3),game.getBoard().getSquare(4, 7));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());
		System.out.println (" Expected: CheckMate true" );
	}
	
	/**
	 * Test the seven move check mate
	 */
	public void testSevenMoveCheckMate() 
	{
		System.out.println ("Test the Seven Move CheckMate");
		ChessGame game = new ChessGame ();
		testBoard.displayBoard(game.getBoard());

		System.out.println ("Move e2 to e4");
		game.makeMove(game.getBoard().getSquare(6, 4),game.getBoard().getSquare(4, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move e7 to e5");
		game.makeMove(game.getBoard().getSquare(1, 4),game.getBoard().getSquare(3, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move d2 to d4");
		game.makeMove(game.getBoard().getSquare(6, 3),game.getBoard().getSquare(4, 3));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move d7 to d6");
		game.makeMove(game.getBoard().getSquare(1, 3),game.getBoard().getSquare(2, 3));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());
		
		System.out.println ("Move d4 to e5");
		game.makeMove(game.getBoard().getSquare(4, 3),game.getBoard().getSquare(3, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());
		
		System.out.println ("Move d8 to e7");
		game.makeMove(game.getBoard().getSquare(0, 3),game.getBoard().getSquare(1, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move e5 to d6");
		game.makeMove(game.getBoard().getSquare(3, 4),game.getBoard().getSquare(2, 3));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());
		
		System.out.println ("Move e7 to e4");
		game.makeMove(game.getBoard().getSquare(1, 4),game.getBoard().getSquare(4, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());
		
		System.out.println ("Move f1 to e2");
		game.makeMove(game.getBoard().getSquare(7, 5),game.getBoard().getSquare(6, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());
		
		System.out.println ("Move e4 to g2");
		game.makeMove(game.getBoard().getSquare(4, 4),game.getBoard().getSquare(6, 6));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());
		
		System.out.println ("Move d6 to c7");
		game.makeMove(game.getBoard().getSquare(2, 3),game.getBoard().getSquare(1, 2));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());
		
		System.out.println ("Move g2 to h1");
		game.makeMove(game.getBoard().getSquare(6, 6),game.getBoard().getSquare(7, 7));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());
		
		System.out.println ("Move d1 to d8");
		game.makeMove(game.getBoard().getSquare(7, 3),game.getBoard().getSquare(0, 3));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());
		System.out.println (" Expected: CheckMate true" );
	}
	
	/**
	 * Test the legal trap 
	 */
	public void testLegalTrapMate() 
	{
		System.out.println ("Test the Legal Trap Mate");
		ChessGame game = new ChessGame ();
		testBoard.displayBoard(game.getBoard());

		System.out.println ("Move e2 to e4");
		game.makeMove(game.getBoard().getSquare(6, 4),game.getBoard().getSquare(4, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move e7 to e5");
		game.makeMove(game.getBoard().getSquare(1, 4),game.getBoard().getSquare(3, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move f1 to c4");
		game.makeMove(game.getBoard().getSquare(7, 5),game.getBoard().getSquare(4, 2));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move d7 to d6");
		game.makeMove(game.getBoard().getSquare(1, 3),game.getBoard().getSquare(2, 3));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());
	
		System.out.println ("Move g1 to f3");
		game.makeMove(game.getBoard().getSquare(7, 6),game.getBoard().getSquare(5, 5));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move c8 to g4");
		game.makeMove(game.getBoard().getSquare(0, 2),game.getBoard().getSquare(4, 6));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move b1 to c3");
		game.makeMove(game.getBoard().getSquare(7, 1),game.getBoard().getSquare(5, 2));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move g7 to g6");
		game.makeMove(game.getBoard().getSquare(1, 6),game.getBoard().getSquare(2, 6));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move f3 to e5");
		game.makeMove(game.getBoard().getSquare(5, 5),game.getBoard().getSquare(3, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move g4 to d1");
		game.makeMove(game.getBoard().getSquare(4, 6),game.getBoard().getSquare(7, 3));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move c4 to f7");
		game.makeMove(game.getBoard().getSquare(4, 2),game.getBoard().getSquare(1, 5));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());

		System.out.println ("Move e8 to e7");
		game.makeMove(game.getBoard().getSquare(0, 4),game.getBoard().getSquare(1, 4));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" CheckMate " + game.isCheckMate());
		
		System.out.println ("Move c3 to d5");
		game.makeMove(game.getBoard().getSquare(5, 2),game.getBoard().getSquare(3, 3));
		testBoard.displayBoard(game.getBoard());
		System.out.println (" Check " + game.isCheck());
		System.out.println (" CheckMate " + game.isCheckMate());
		System.out.println (" Expected: CheckMate true" );

	}
	
	
}
