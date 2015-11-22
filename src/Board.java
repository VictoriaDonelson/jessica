import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The Class Board creates a board with timer and keyboard listener functionality.
 */
public class Board extends JPanel implements KeyListener{
	
	/** The board. */
	private Color [][] board;
	
	/** The current shape. */
	private Shape currentShape;
	
	/** The next shape. */
	private Shape nextShape;
	
	/** The timer. */
	private Timer timer;
	
	/** The paused. */
	public boolean paused;
	
	/** The lines removed. */
	private int linesRemoved;
	
	/** The score. */
	private int score;
	
	/** The window. */
	private Game window;
	
	/** The game over. */
	private boolean gameOver;
	
	/** The explode. */
	private AudioClip explode;
	
	/** The rotate. */
	private AudioClip rotate;
	
	/** The drop. */
	private AudioClip drop;
	
	/** The fail. */
	private AudioClip fail;
	
	/** The cell size. */
	public final int CELL_SIZE = Block.SIZE;
	
	/** The rows. */
	public final int ROWS = 20;
	
	/** The columns. */
	public final int COLUMNS = 15;
	
	/** The board width. */
	public final int BOARD_WIDTH = COLUMNS * CELL_SIZE;
	
	/** The board height. */
	public final int BOARD_HEIGHT = ROWS * CELL_SIZE;
	
	/** The points. */
	public final int POINTS = 10;
	
	/** The bonus points. */
	public final int BONUS_POINTS = 5;
	
	
	/**
	 * Instantiates a new board.
	 *
	 * @param parent the parent
	 */
	public Board(Game parent){
		window = parent;
		setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setOpaque(true);
        setBackground(Color.black);
		this.addKeyListener(this);
		timer = new Timer (400, new TimerListener()); 
		paused = false;
		gameOver = false;
		linesRemoved = 0;
		score = 0;
		try {
			explode = Applet.newAudioClip(new URL("file:explode.wav"));	
			drop = Applet.newAudioClip(new URL("file:drop3.wav"));
			rotate = Applet.newAudioClip(new URL("file:rotate.wav"));
			fail = Applet.newAudioClip(new URL("file:fail.wav"));
		} catch (MalformedURLException e) {			
			e.printStackTrace();
		}
		createBoard();
		randomShape();
		newShape();
		
	}
	
	/**
	 * Creates the board.
	 */
	public void createBoard(){
		board = new Color [ROWS][COLUMNS];
		for(int i=0; i<ROWS; i++){
			for(int j=0; j<COLUMNS; j++){
				board[i][j] = Color.gray;
			}
		}
	} 
	
	/**
	 * Random shape.
	 */
	public void randomShape(){
		Random random = new Random();
		int choice = random.nextInt(7);
		if (choice == 0){nextShape = new L_Shape();}
		else if (choice == 1){nextShape = new J_Shape();}
		else if (choice == 2){nextShape = new S_Shape();}
		else if (choice == 3){nextShape = new Z_Shape();}
		else if (choice == 4){nextShape = new T_Shape();}
		else if (choice == 5){nextShape = new I_Shape();}
		else if (choice == 6){nextShape = new O_Shape();}
	}
	
	/**
	 * New shape.
	 */
	public void newShape(){
		currentShape = nextShape;
		randomShape();		
		currentShape.updateX(COLUMNS/2*CELL_SIZE);
		timer.start();

	}
	
	/**
	 * This is the paint method that will track all the blocks and draw a square there,
	 * will check to see if the game is done and will draw a GameOver sign over the board,
	 * and will check to see if the game is paused and will draw over the board.
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent (Graphics g) {
		int x, y;
		super.paintComponent(g);	
		for(int i=0; i<ROWS; i++){
			for(int j=0; j<COLUMNS; j++){
				if (board[i][j] != Color.gray){
					x = j * CELL_SIZE;
					y = i * CELL_SIZE;
					drawSquare(g, x, y, board[i][j]);
				}
			}
		}
		for(Block block : currentShape.getCurrentPosition()){
			x = block.getX();
			y = block.getY();
			drawSquare(g, x, y, currentShape.getColor());
		}	
		 
		if (gameOver){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			g.setColor(Color.white);
			g.drawString("Game Over", 50, 150);
		}
		if (paused){
			g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
			g.setColor(Color.white); 
			g.drawString("Paused", 80, 150);
			
		}
		
	}
	
	/**
	 * Draw square draws a square with a lighter color superimposed on a darker square.
	 *
	 * @param g the g
	 * @param x the x
	 * @param y the y
	 * @param color the color
	 */
	public void drawSquare(Graphics g, int x, int y, Color color){		
		g.setColor(color);
		g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
		g.setColor(color.brighter());
	    g.drawLine(x, y + CELL_SIZE - 1, x, y);
	    g.drawLine(x, y, x + CELL_SIZE - 1, y);

	    g.setColor(color.darker());
	    g.drawLine(x + 1, y + CELL_SIZE - 1,
	                         x + CELL_SIZE - 1, y + CELL_SIZE - 1);
	    g.drawLine(x + CELL_SIZE - 1, y + CELL_SIZE - 1,
	                         x + CELL_SIZE - 1, y + 1);
			
	}
	
	/**
	 * Ycollision checks to see if the block touched another block in a column.
	 *
	 * @return true, if successful
	 */
	public boolean Ycollision(){
		boolean collision = false;
		for(Block block : currentShape.getCurrentPosition()){
			int y = block.getY()/CELL_SIZE;
			int x = block.getX()/CELL_SIZE;
			if (board[y+1][x] != Color.gray){collision = true;}
		}
		return collision;
	}
	
	/**
	 * Xcollision checks to see if the block touched another block in a row.
	 *
	 * @return true, if successful
	 */
	public boolean Xcollision (){
		boolean collision = false;
		for(Block block : currentShape.getCurrentPosition()){
			int y = block.getY()/CELL_SIZE;
			int x = block.getX()/CELL_SIZE;
			if (block.getX()==0 && board[y][x + 1] != Color.gray){collision = true;}
			else if ((block.getX() + CELL_SIZE == BOARD_WIDTH) && board[y][x - 1] != Color.gray){collision = true;}
			else if (block.getX()!=0 && (block.getX() + CELL_SIZE != BOARD_WIDTH) && 
					(board[y][x + 1] != Color.gray || board[y][x - 1] != Color.gray)){collision = true;}
			
		}
		return collision;
	}
	
	/**
	 * Move down.
	 */
	public void moveDown(){
		
    	if (currentShape.maxY() + CELL_SIZE == BOARD_HEIGHT){shapeDropped();}
    	else if (Ycollision()){shapeDropped();}
    	else {currentShape.updateY(CELL_SIZE);	}			
		repaint();
		
	}
	
	/**
	 * Shape dropped.
	 */
	public void shapeDropped(){
		timer.stop();
		for(Block block: currentShape.getCurrentPosition()){
			int column = block.getX()/CELL_SIZE;
			int row = block.getY()/CELL_SIZE;
			board[row][column] = currentShape.getColor();
		}
		removeLines();
		drop.play();
		gameOver();
		window.getPanel().repaint();
		//System.out.println(toString());
	}
	
	/**
	 * Game over.
	 */
	public void gameOver(){
		for (int i=0; i<COLUMNS;i++){
			if (board[0][i] != Color.gray){
				gameOver = true; 
				fail.play();
				break;}
		}
		if (!gameOver){newShape();}
	}
	
	/**
	 * Full line at.
	 *
	 * @return the int
	 */
	public int fullLineAt(){
		int fullCells = 0;
		int row = -1;
		for(int i=0; i<ROWS; i++){	
			fullCells = 0;
			for(int j=0; j<COLUMNS; j++){					
				if (board[i][j] != Color.gray){
					fullCells++;
				}
			if(fullCells == COLUMNS){return i;}
			}
		}
		
		return row;
	}
	
	/**
	 * Update board.
	 *
	 * @param row the row
	 */
	public void updateBoard(int row){
		for(int i=row; i>0; i--){
			for(int j=0; j<COLUMNS; j++){				
				board[i][j] = board[i-1][j];
			}
		}
	}
	
	/**
	 * Removes the lines.
	 */
	public void removeLines(){
		boolean allLinesRemoved = false;
		int lineCounter = 0;
		while(!allLinesRemoved){
			int fullLineAt = fullLineAt();
			if (fullLineAt == -1){allLinesRemoved = true;}
			else {
				updateBoard(fullLineAt);
				explode.play();
				linesRemoved++;
				lineCounter++;
				score += linesRemoved * POINTS + (lineCounter - 1) * BONUS_POINTS;
				window.getPanel().getLines().setText("  Lines:  " + linesRemoved);
				window.getPanel().getScore().setText("  Score:  " + score);
			}
		}
		repaint();
		
	}
	
	/**
	 * Gets the lines removed.
	 *
	 * @return the lines removed
	 */
	public int getLinesRemoved(){
		return linesRemoved;
	}
	
	/**
	 * Adjust coords.
	 */
	public void adjustCoords(){
		int blocksOffScreen;
		if (currentShape.maxX() > BOARD_WIDTH - CELL_SIZE){
			blocksOffScreen = currentShape.maxX() - (BOARD_WIDTH - CELL_SIZE);
			currentShape.updateX(-blocksOffScreen);
		}
		if(currentShape.maxY() > BOARD_HEIGHT - CELL_SIZE){
			blocksOffScreen = currentShape.maxY() - (BOARD_HEIGHT - CELL_SIZE);
			currentShape.updateY(-blocksOffScreen);
		}
	}
	
	/* keyPressed checks to see if the user pressed p to pause the game or else
	 * the arrow commands to move the block in the desired direction.
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent event) {
		   int keyCode = event.getKeyCode();
		   
		   if (keyCode == 'p' || keyCode == 'P') {
               pause();
               return;
           }
		    switch( keyCode ) { 
		        case KeyEvent.VK_UP:
		        	rotate.play();
		        	if (paused){;}
		        	else if (Xcollision()){;}
		        	else {
		        		currentShape.rotateLeft();
		        		adjustCoords();
		        		
		        	}
		            break;
		            
		        case KeyEvent.VK_DOWN:
		        	if (paused){;}
		        	else moveDown();			
		        	break;
		        	
		        case KeyEvent.VK_LEFT:
		        	if (paused){;}
		        	else if(currentShape.minX() == 0){;}
		        	else if (Xcollision()){;}
		        	else {currentShape.updateX(-CELL_SIZE);}
		            break;
		            
		        case KeyEvent.VK_RIGHT :
		        	if (paused){;}
		    		else if(currentShape.maxX() + CELL_SIZE == BOARD_WIDTH){;}
		        	else if (Xcollision()){;}
		        	else {currentShape.updateX(CELL_SIZE);}
		            break;
		            
		        //case KeyEvent.VK_SPACE :
		        	
		    }
		    repaint();
		
	}
	
	/**
	 * Pause.
	 */
	public void pause()
    {
        if (!gameOver){
        	paused = !paused;
            if (paused) {
                timer.stop();
         
            } else {
                timer.start();
            }
            repaint();
        }
    }
	
	/** 
	 * The method toString return a string about what the board looks like, the currentShape
	 * and the nextShape.
	 * @see java.awt.Component#toString()
	 */
	@Override
	public String toString() {
		return "Board [board=" + Arrays.toString(board) + ", currentShape=" + currentShape + ", nextShape=" + nextShape
				+ "]";
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	/**
	 * The listener interface for receiving timer events.
	 * The class that is interested in processing a timer
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addTimerListener<code> method. When
	 * the timer event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see TimerEvent
	 */
	private class TimerListener implements ActionListener {

		/**
		 * Method creates and starts a timer that fires an action event once per second 
		 * (as specified by the first argument to the Timer constructor).
		 * So everytime moveDown is called the timer will increment by one second.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			moveDown();
		
		}
	
	}
	 
 	/**
 	 * Gets the next shape.
 	 *
 	 * @return the next shape
 	 */
 	public Shape getNextShape(){
		 return nextShape;
	 }
	 
	 /**
 	 * Gets the cell size.
 	 *
 	 * @return the cell size
 	 */
 	public int getCellSize(){
		 return CELL_SIZE;
	 }
	 
	 /**
 	 * Gets the board height.
 	 *
 	 * @return the board height
 	 */
 	public int getBoardHeight(){
		 return BOARD_HEIGHT;
	 }
	 
	 /**
 	 * Sets the lines removed.
 	 *
 	 * @param number the new lines removed
 	 */
 	public void setLinesRemoved(int number){
		 linesRemoved = number;
	 }
	 
	 /**
 	 * Sets the score.
 	 *
 	 * @param number the new score
 	 */
 	public void setScore(int number){
		 score = number;
	 }
}