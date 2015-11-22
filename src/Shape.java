import java.awt.Color;
import java.util.Random;

/**
 * The Class Shape will create the blocks for each shape, have getters and setters for the 
 * positions of each block and will rotate the shapes to each desired position.
 */
abstract class Shape {
	
	/** The color. */
	protected Color color;
	
	/** The position1. */
	protected Block [] position1;
	
	/** The position2. */
	protected Block [] position2;
	
	/** The position3. */
	protected Block [] position3;
	
	/** The position4. */
	protected Block [] position4;
	
	/** The current position. */
	protected Block [] currentPosition;

	/**
	 * Instantiates a new shape.
	 *
	 * @param shapeColor the shape color
	 */
	public Shape (Color shapeColor){
		color = shapeColor;
		initPositions();
		Random random = new Random();
		int position = random.nextInt(4) + 1;
		if (position == 1){currentPosition = position1;}
		else if (position == 2){currentPosition = position2;}
		else if (position == 3){currentPosition = position3;}
		else if (position == 4){currentPosition = position4;}
	}
	
	/**
	 * Inits the positions.
	 */
	abstract void initPositions();
	
	/**
	 * Rotate left.
	 */
	public void rotateLeft(){
		if (currentPosition == position1){currentPosition = position2;}
		else if (currentPosition == position2){currentPosition = position3;}
		else if (currentPosition == position3){currentPosition = position4;}
		else if (currentPosition == position4){currentPosition = position1;}
	}
	
	/**
	 * Rotate right.
	 */
	public void rotateRight(){
		if (currentPosition == position1){currentPosition = position4;}
		else if (currentPosition == position2){currentPosition = position1;}
		else if (currentPosition == position3){currentPosition = position2;}
		else if (currentPosition == position4){currentPosition = position3;}
	}

	/**
	 * Gets the position1.
	 *
	 * @return the position1
	 */
	public Block[] getPosition1() {
		return position1;
	}

	/**
	 * Gets the position2.
	 *
	 * @return the position2
	 */
	public Block[] getPosition2() {
		return position2;
	}

	/**
	 * Gets the position3.
	 *
	 * @return the position3
	 */
	public Block[] getPosition3() {
		return position3;
	}

	/**
	 * Gets the position4.
	 *
	 * @return the position4
	 */
	public Block[] getPosition4() {
		return position4;
	}

	/**
	 * Gets the current position.
	 *
	 * @return the current position
	 */
	public Block[] getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * Update x.
	 *
	 * @param Xcoord the xcoord
	 */
	public void updateX(int Xcoord){
		for (Block block : position1){
			block.setX(block.getX() + Xcoord);
		}
		
		for (Block block : position2){
			block.setX(block.getX() + Xcoord);
		}
		
		for (Block block : position3){
			block.setX(block.getX() + Xcoord);
		}
		
		for (Block block : position4){
			block.setX(block.getX() + Xcoord);
		}
	}
	
	/**
	 * Update y.
	 *
	 * @param Ycoord the ycoord
	 */
	public void updateY(int Ycoord){
		for (Block block : position1){
			block.setY(block.getY() + Ycoord);
		}
		
		for (Block block : position2){
			block.setY(block.getY() + Ycoord);
		}
		
		for (Block block : position3){
			block.setY(block.getY() + Ycoord);
		}
		
		for (Block block : position4){
			block.setY(block.getY() + Ycoord);
		}
	}
	
	/**
	 * Max y.
	 *
	 * @return the int
	 */
	public int maxY(){
		int maxY = 0;
    	for (Block block : currentPosition){
    		if(block.getY() > maxY){maxY = block.getY();}
    	}
    	return maxY;
	}
	
	/**
	 * Max x.
	 *
	 * @return the int
	 */
	public int maxX(){
		int maxX = 0;
    	for (Block block : currentPosition){
    		if(block.getX() > maxX){maxX = block.getX();}
    	}
    	return maxX;
	}
	
	/**
	 * Min x.
	 *
	 * @return the int
	 */
	public int minX(){
		int minX = 10000000;
    	for (Block block : currentPosition){
    		if(block.getX() < minX){minX = block.getX();}
    	}
    	return minX;
	}
}
