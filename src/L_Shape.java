import java.awt.Color;

/**
 * The Class L_Shape creates a new L shaped Tetronimo.
 */
public class L_Shape extends Shape{

	/**
	 * Instantiates a new shape.
	 */
	public L_Shape () {
		super(Color.red);
		
	}
	
	/**
	 * Will start a block at the initial positions.
	 */
	public void initPositions(){
		position1 = new Block [] {new Block(0, 0), new Block(0, Block.SIZE), 
				new Block(0, 2*Block.SIZE), new Block(Block.SIZE, 2*Block.SIZE)};
		position2 = new Block [] {new Block(0, Block.SIZE),  new Block(Block.SIZE, Block.SIZE), 
				new Block(2*Block.SIZE, Block.SIZE), new Block(2*Block.SIZE, 0)};
		position3 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(Block.SIZE, Block.SIZE), new Block(Block.SIZE, 2*Block.SIZE)};
		position4 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(0, Block.SIZE), new Block(2*Block.SIZE, 0)};
	}
}
