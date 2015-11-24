import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InfoPanel extends JPanel implements ActionListener{
	private JLabel nextShape;
	private JLabel score;
	private JLabel lines;
	private JButton mute;
	private JButton pause;
	private JButton restart;
	private Game window;
	private int height;
	public final int WIDTH = 150;
	
	
	public InfoPanel(Game parent){
		window = parent;
		height = window.getBoard().getBoardHeight();
		setPreferredSize(new Dimension(WIDTH, height));	
		setLayout(new GridLayout(10, 1));
		nextShape = new JLabel("Next Shape:", SwingConstants.CENTER);
		nextShape.setVerticalAlignment(JLabel.TOP);
		nextShape.setFont(new Font("Serif", Font.PLAIN, 18));	
		
		JLabel filler1 = new JLabel("");
		JLabel filler2 = new JLabel("");
		JLabel filler3 = new JLabel("");
		JLabel filler4 = new JLabel("");
		JLabel filler5 = new JLabel("");
		
		lines = new JLabel("  Lines:  0  ");
		lines.setFont(new Font("Serif", Font.PLAIN, 18));
		lines.setHorizontalAlignment(JLabel.LEFT);
		
		score = new JLabel("  Score:  0  ");
		score.setFont(new Font("Serif", Font.PLAIN, 18));
		score.setHorizontalAlignment(JLabel.LEFT);
		
		restart = new JButton("Restart");
		restart.addActionListener(this);
				
		pause = new JButton("Pause/Play");
		pause.addActionListener(this);
		
		JPanel pausePanel = new JPanel();
		pausePanel.add(restart);
		JPanel restartPanel = new JPanel();
		restartPanel.add(pause);
		
		add(nextShape);
		add(filler1);
		add(filler2);
		add(filler3);		
		add(lines);
		add(score);
		add(filler5);
		add(pausePanel);
		add(restartPanel);
		add(filler4);

	}
	
	public void paintComponent(Graphics g2){
		super.paintComponent(g2);
		Shape shape = window.getBoard().getNextShape();
		int blockSize = window.getBoard().getCellSize();
		for(Block block : shape.getCurrentPosition()){
			int x = block.getX();
			int y = block.getY();
			drawSquare(g2, x + 53, y + 40, shape.getColor(), blockSize);
		}	
	}
	public void drawSquare(Graphics g2, int x, int y, Color color, int size){	
		
		g2.setColor(color);
		g2.fillRect(x, y, size, size);
		g2.setColor(color.brighter());
	    	g2.drawLine(x, y + size - 1, x, y);
	    	g2.drawLine(x, y, x + size - 1, y);

	    	g2.setColor(color.darker());
	    	g2.drawLine(x + 1, y + size - 1,
	                         x + size - 1, y + size - 1);
	    	g2.drawLine(x + size - 1, y + size - 1,
	                         x + size - 1, y + 1);
			
	}
	
	public JLabel getScore(){
		return score;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == pause){
			window.getBoard().pause();
			window.getBoard().requestFocusInWindow();
		}
		if(e.getSource() == restart){
			window.getBoard().createBoard();
			window.getBoard().randomShape();
			window.getBoard().newShape();
			window.getBoard().setScore(0);
			window.getBoard().setLinesRemoved(0);
			lines.setText("  Lines:  0");
			score.setText("  Score:  0");
			window.getBoard().paused = false;
			window.getBoard().requestFocusInWindow();
		}
		
		
	}
	
	public JLabel getLines(){
		return lines;
	}
}
