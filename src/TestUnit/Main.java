package TestUnit;

/**
 * Test the classes of the unit test
 * @author yizhuo
 */
public class Main 
{
	/**
	 * Test the test classes
	 * @param args
	 */
	public static void main(String[] args) 
	{
		TestBoard test = new TestBoard ();
		TestPieces pieces = new TestPieces ();
		test.displayStandardBoard ();
		pieces.testPawnMoves();
		pieces.testQueenMoves();
		pieces.testRookMoves();
		pieces.testKnightMoves();
		pieces.testBishopMoves();
		pieces.testKingMoves();
	
		TestCheckMate game = new TestCheckMate ();
//		game.testFoolsMate();
//		game.testSevenMoveCheckMate();
//		game.testLegalTrapMate();
	}

}
