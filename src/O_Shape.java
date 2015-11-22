import java.awt.Color;

/**
 * The Class O_Shape creates a square shape.
 */
public class O_Shape extends Shape{

	/**
	 * Instantiates a new shape.
	 */
	public O_Shape () {
		super(Color.magenta);
	}
	
	/**
	 * Will start a block at the initial positions.
	 */
	public void initPositions(){
		position1 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(0, Block.SIZE), new Block(Block.SIZE, Block.SIZE)};
		position2 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(0, Block.SIZE), new Block(Block.SIZE, Block.SIZE)};
		position3 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(0, Block.SIZE), new Block(Block.SIZE, Block.SIZE)};
		position4 = new Block [] {new Block(0, 0), new Block(Block.SIZE, 0), 
				new Block(0, Block.SIZE), new Block(Block.SIZE, Block.SIZE)};
	}
	
}

