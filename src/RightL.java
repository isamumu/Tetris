import java.awt.Color;
import java.awt.Graphics;

public class RightL extends Shape{

	public RightL(int x, int y, int rightBoundary, int leftBoundary, int downBoundary, Orientation orientation) {
		super(x, y, rightBoundary, leftBoundary, downBoundary, orientation, 3, 2);
		
		this.color = Color.GREEN;
	}


	@Override
	public void drawSelf(Graphics g) {
		if (this.orientation == Orientation.UP) {
			drawSquare(g, this.x, this.y);
			drawSquare(g, this.x, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x, this.y + (2 * SQUARE_DIMENSION));
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y + (2 * SQUARE_DIMENSION));
		} else if (this.orientation == Orientation.DOWN) {
			drawSquare(g, this.x, this.y);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y + (2 * SQUARE_DIMENSION));
		} else if (this.orientation == Orientation.LEFT) {
			drawSquare(g, this.x + (2 * SQUARE_DIMENSION), this.y);
			drawSquare(g, this.x + (2 * SQUARE_DIMENSION), this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x, this.y + SQUARE_DIMENSION);
		} else if (this.orientation == Orientation.RIGHT) {
			drawSquare(g, this.x, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x, this.y);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y);
			drawSquare(g, this.x + (2 * SQUARE_DIMENSION), this.y);
		}

	}

}
