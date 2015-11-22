import java.awt.Color;

/**
 * The Class T_Shape creates a T shaped Tetronimo.
 */
public class T_Shape extends Shape{

	/**
	 * Instantiates a new t shape.
	 */
	public T_Shape () {
		super(Color.cyan);
		
	}
	
	/**
	 * Will start a block at the initial positions.
	 */
	public void initPositions(){
		position1 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(Block.SIZE, Block.SIZE), new Block(2*Block.SIZE, 0)};
		position2 = new Block [] {new Block(0, 0), new Block(0, Block.SIZE), 
				new Block(0, 2*Block.SIZE), new Block(Block.SIZE, Block.SIZE)};
		position3 = new Block [] { new Block(0, Block.SIZE), new Block(Block.SIZE, 0), 
				new Block(Block.SIZE, Block.SIZE), new Block(2*Block.SIZE, Block.SIZE)};
		position4 = new Block [] {new Block(0, Block.SIZE), new Block(Block.SIZE, 0),  
				new Block(Block.SIZE, Block.SIZE), new Block(Block.SIZE, 2*Block.SIZE)};
	}
	
}
