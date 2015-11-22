/**
 * The Class Block creates a block at a certain point
 * and you can get and set this certain point.
 */
public class Block {
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The Constant SIZE. */
	public final static int SIZE = 15;
	
	/**
	 * Instantiates a new block.
	 *
	 * @param Xcoord the xcoord
	 * @param Ycoord the ycoord
	 */
	public Block (int Xcoord, int Ycoord){
		x = Xcoord;
		y = Ycoord;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param Xcoord the new x
	 */
	public void setX(int Xcoord) {
		x = Xcoord;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param Ycoord the new y
	 */
	public void setY(int Ycoord) {
		y = Ycoord;
	}
	
}
