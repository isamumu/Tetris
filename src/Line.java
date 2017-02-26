import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	public Line(int x, int y, int rightBoundary, int leftBoundary, int downBoundary, Orientation orientation) {
		super(x, y, rightBoundary, leftBoundary, downBoundary, orientation, 4, 1);
		
		this.color = Color.MAGENTA;
	}

	@Override
	public void drawSelf(Graphics g) {
		if (this.orientation == Orientation.UP || this.orientation == Orientation.DOWN) {
			drawSquare(g, this.x, this.y);
			drawSquare(g, this.x, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x, this.y + (2 * SQUARE_DIMENSION));
			drawSquare(g, this.x, this.y + (3 * SQUARE_DIMENSION));
		} else if (this.orientation == Orientation.LEFT || this.orientation == Orientation.RIGHT) {
			drawSquare(g, this.x, this.y);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y);
			drawSquare(g, this.x + (2 * SQUARE_DIMENSION), this.y);
			drawSquare(g, this.x + (3 * SQUARE_DIMENSION), this.y);

		}

	}
	
}
