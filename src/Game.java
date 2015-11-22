import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * The Class Game is where Tetris will be run out of. It creates a board, the side 
 * information panel, then starts a new game.
 */
public class Game extends JFrame{
	
	/** The board. */
	private Board board;
	
	/** The panel. */
	private InfoPanel panel;
	
	/**
	 * Instantiates a new game.
	 */
	public Game(){
		setTitle("Tetris");
		setLayout(new BorderLayout());
		board = new Board(this);
		panel = new InfoPanel(this);
		add(board, BorderLayout.CENTER);
		add(panel, BorderLayout.EAST);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

		board.setFocusable(true);
		board.requestFocusInWindow();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Game window = new Game();

	}
	
	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public Board getBoard(){
		return board;
	}
	
	/**
	 * Gets the panel.
	 *
	 * @return the panel
	 */
	public InfoPanel getPanel(){
		return panel;
	}

}
