package ChessGame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

/**
 * This frame contains a chess game and a reset button
 * 
 * @author yizhuo
 */
public class GameFrame extends JFrame
{
	//instance variables
	private JPanel gamePanel = new JPanel(new BorderLayout());
	private JPanel gameBoard = new JPanel(new GridLayout(8,8)); 
	private final Dimension BOARD_DIMENSION = new Dimension (400,350);
	private final Dimension FRAME_SIZE = new Dimension (400,410);
	private final Dimension RESET_SIZE = new Dimension (70, 20);
	private final int NUM_ROWS = 8;

	private ArrayList<SquarePanel> boardSquares;
	private ChessGame game;
	private Board board;
	private Square sourceSquare; 
	private ChessPiece playerPiece;
	private JButton resetButton;
	private boolean gameOver; 
	private boolean inCheck;
	private boolean blockOrEscape;

	/**
	 * Constructs the frame and initialized the variables
	 */
	public GameFrame() 
	{
		game  = new ChessGame();
		board = game.getBoard();
		sourceSquare = null;
		playerPiece  = null;
		gameOver = false;
		blockOrEscape = false;
		inCheck = false;
	}

	/**
	 * Initialize the frame and run the game
	 */
	public void run() 
	{
		createResetButton ();
		initializeFrame   ();
	}

	/**
	 * Create the game board and square panels
	 */
	public void creatBoard () 
	{
		gameBoard.setSize(BOARD_DIMENSION);

		String squareNum = "";
		boardSquares = new ArrayList <SquarePanel> ();

		for (int row = 1; row <= NUM_ROWS; row++) 
		{
			squareNum += row;
			for (int col = 1; col <= NUM_ROWS; col++) 
			{
				squareNum += col;
				SquarePanel square = new SquarePanel(squareNum, gameBoard);
				boardSquares.add(square);
				gameBoard.add(square);
				squareNum = squareNum.substring(0, 1);
			}
			squareNum = "";
		}
	}

	/**
	 * Update the game status
	 */
	public void gameStatus () 
	{
		if (game.isCheck ()) 
		{
			inCheck = true;
			setTitle("Check!");
			if (game.isCheckMate()) 
			{
				setTitle("CheckMate!");
				gameOver = true;
			}
		}
		else 
		{
			setTitle("Chess Game");
			blockOrEscape = false;
			inCheck = false;
		}
		repaint();
	}

	/**
	 * Draw the board
	 * @param board
	 */
	public void drawBoard (Board board) 
	{
		gameBoard.removeAll();
		for (SquarePanel square : boardSquares) 
		{
			square.drawSquare(board);
			gameBoard.add(square);
		}
		validate();
		repaint();
	}

	/**
	 * Highlight the squares for possible moves
	 */
	public void hightlightSquares () 
	{
		for (SquarePanel square : boardSquares) 
		{
			if (game.isPossibleMove (square.SQUARE_NUM)) 
			{
				square.highlightSquare();
				if (inCheck) 
				{
					blockOrEscape = true;
				}
			}
		}
	}

	/**
	 * Initialize the frame
	 */
	private void initializeFrame () 
	{
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Chess Game");
		this.setResizable(false);
		creatBoard ();

		gamePanel.add(gameBoard, BorderLayout.CENTER);
		gamePanel.add(resetButton, BorderLayout.SOUTH);

		this.add(gamePanel);
		this.setSize(FRAME_SIZE);
		this.setLocation(200, 50);
		this.setVisible(true);
	}

	/**
	 * Create the reset button and set the mouse adapter on button
	 * to reset the game 
	 */
	private void createResetButton () 
	{
		resetButton = new JButton ("Reset");
		resetButton.setPreferredSize(RESET_SIZE);
		MouseAdapter mouseEvent = new MouseAdapter () 
		{
			@Override
			public void mouseReleased(MouseEvent event) 
			{
				resetGame ();
			}
		};
		resetButton.addMouseListener(mouseEvent);
	}

	/**
	 * Reset the game
	 */
	private void resetGame () 
	{
		game = new ChessGame();
		board = game.getBoard();
		gameOver = false;
		blockOrEscape = false;
		inCheck = false;
		creatBoard ();
		drawBoard(board);
	}

	/**
	 * Square panel has its square number, row, column, color, piece icon,
	 * and a selected color. 
	 */
	public class SquarePanel extends JPanel
	{
		//instance variables
		private final int SQUARE_NUM;
		private final Color DARK_GREEN = new Color(76,153,0);
		private final Color LIGHT_GREEN = new Color (215,240,199);
		private final int ROW, COL;
		private final Border BORDER = BorderFactory.createLineBorder(Color.black);
		private final Border DEFAULT_BORDER = BorderFactory.createLineBorder(Color.white);

		private String pieceIconPath = "images/pieces";
		private final Color selectedColor = new Color (255,153,153); 
		private final Color possibleMove = new Color (204,255,255); 

		/**
		 * Construct the square panel with given number and board panel
		 * Set a mouse adapter for the game interface
		 * @param num         the number 
		 * @param boardPanel  the board panel
		 */
		public SquarePanel (String num, JPanel boardPanel)
		{
			ROW = Integer.valueOf(num)/10 - 1;
			COL = Integer.valueOf(num)%10 - 1;
			SQUARE_NUM = ROW*8 + COL;
			drawSquare (board);

			/**
			 * Mouse Adapter to get the selected squares and move pieces of 
			 * the game
			 */
			addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseReleased(MouseEvent event) 
				{
					if (!gameOver) 
					{
						if (sourceSquare == null) 
						{
							sourceSquare = board.getSquare(ROW, COL);
							playerPiece = sourceSquare.getPiece();

							if (playerPiece == null 
									|| game.getCurrentPlayerColor()!=playerPiece.getColor()) 
							{
								sourceSquare = null;
							}
							else
							{								
								game.setPiece2Move(playerPiece);
								hightlightSquares ();
								isSelected ();
							}
						}
						else if(sourceSquare != null && 
								game.getCurrentPlayerColor() == playerPiece.getColor())
						{
							if (game.makeMove(sourceSquare, board.getSquare(ROW, COL))) 
							{
								board = game.getBoard();
							}
							drawBoard (board);
							gameStatus ();
							sourceSquare = null;
						}
					}
				}

			});
		}

		/**
		 * Set the color of the square by alternating colors
		 */
		private void setColor () 
		{
			this.setBackground(LIGHT_GREEN);		

			if(ROW%2 == 0 && (COL%2 == 0)) 
			{			
				this.setBackground(DARK_GREEN);		
			}
			else if(ROW%2 != 0 &&(COL%2 != 0)) 
			{
				this.setBackground(DARK_GREEN);		
			} 
		}

		/**
		 * Set the piece icon if the square holds a piece
		 * @param board
		 */
		private void setPieceIcon (Board board) 
		{
			ChessPiece piece = board.getPiece(ROW, COL);

			if(piece != null) 
			{
				try{
					BufferedImage image = ImageIO.read(new File(pieceIconPath +
							piece.getPath() + ".png"));
					add(new JLabel(new ImageIcon(image)));
				} 
				catch(IOException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * Highlight the square if is a possible move
		 */
		private void highlightSquare () 
		{
			setBackground(possibleMove);
			setBorder (BORDER);
			repaint();
		}

		/**
		 * Paint the selected square
		 */
		private void isSelected () 
		{
			if (!inCheck) 
			{
				setBackground(selectedColor);
			}
			else if (blockOrEscape && inCheck) 
			{
				setBackground(selectedColor);
				blockOrEscape = false;
			}
			repaint();
		}

		/**
		 * Draw the square
		 * @param board
		 */
		public void drawSquare (Board board) 
		{
			removeAll();
			setColor ();
			setPieceIcon (board);
			setBorder (DEFAULT_BORDER);
			validate();
			repaint();
		}

	}
}
