import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
	public static final int SQUARE_DIMENSION = 15; // 1 square unit
	
	int baseWidth; // this is the baseWidth when the shape is facing left or right
	int baseHeight; // this is the baseHeight when the shape is facing left or right
	int x;
	int y;
	int height;
	int width;
	int rightBoundary;
	int leftBoundary;
	int downBoundary;
	Orientation orientation;
	Color color;

	public Shape(int x, int y, int rightBoundary, int leftBoundary, int downBoundary, Orientation orientation, int baseHeight, int baseWidth) {
		this.x = x;
		this.y = y;
		this.rightBoundary = rightBoundary;
		this.leftBoundary = leftBoundary;
		this.downBoundary = downBoundary;
		this.orientation = orientation;
		this.baseHeight = baseHeight;
		this.baseWidth = baseWidth;
		
		this.setNewDimensions();
	}

	
	public abstract void drawSelf(Graphics g);


	public boolean isWithinBounds() {
		return !(this.x + this.width > this.rightBoundary)
				&& !(this.x < this.leftBoundary)
				&& !(this.y + this.height > this.downBoundary);
	}
	
	public void setNewDimensions(){
		if (this.orientation == Orientation.UP || this.orientation == Orientation.DOWN) {
			this.width = this.baseWidth * SQUARE_DIMENSION;
			this.height = this.baseHeight * SQUARE_DIMENSION;
		} else if (this.orientation == Orientation.LEFT || this.orientation == Orientation.RIGHT) {
			this.width = this.baseHeight * SQUARE_DIMENSION;
			this.height = this.baseWidth * SQUARE_DIMENSION;
		}
	}
	
	
	public boolean isOutOfBounds() {
		return !isWithinBounds();
	}

	public void rotate(){
		Orientation prevOrientation = this.orientation;
		if (this.orientation == Orientation.UP) {
			this.orientation = Orientation.RIGHT;
		} else if (this.orientation == Orientation.RIGHT) {
			this.orientation = Orientation.DOWN;
		} else if (this.orientation == Orientation.DOWN) {
			this.orientation = Orientation.LEFT;
		} else {
			this.orientation = Orientation.UP;
		}
		
		this.setNewDimensions();
		if(this.isOutOfBounds()) {
			this.orientation = prevOrientation;
			this.setNewDimensions();
		}
	}
	
	

	public void drawSquare(Graphics g, int x, int y) {
		g.setColor(this.color);
		g.fillRect(x, y, Shape.SQUARE_DIMENSION, Shape.SQUARE_DIMENSION);
	}
}
