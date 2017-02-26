import java.awt.Color;
import java.awt.Graphics;

public class Square extends Shape {
	
	public Square(int x, int y, int rightBoundary, int leftBoundary, int downBoundary, Orientation orientation) {
		super(x, y, rightBoundary, leftBoundary, downBoundary, orientation, 2, 2);
		
		this.color = Color.BLUE;
	}

	@Override
	public void drawSelf(Graphics g) {
		this.drawSquare(g, this.x, this.y);
		this.drawSquare(g, this.x + SQUARE_DIMENSION, this.y);
		this.drawSquare(g, this.x, this.y + SQUARE_DIMENSION);
		this.drawSquare(g, this.x + SQUARE_DIMENSION, this.y + SQUARE_DIMENSION);
	}


}
