import java.awt.Color;
import java.awt.Graphics;

public class T extends Shape {

	public T(int x, int y, int rightBoundary, int leftBoundary, int downBoundary, Orientation orientation) {
		super(x, y, rightBoundary, leftBoundary, downBoundary, orientation, 2, 3);

		this.color = Color.ORANGE;
	}

	@Override
	public void drawSelf(Graphics g) {
		if (this.orientation == Orientation.UP) {
			drawSquare(g, this.x, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x + (2 * SQUARE_DIMENSION), this.y + SQUARE_DIMENSION);
		} else if (this.orientation == Orientation.DOWN) {
			drawSquare(g, this.x, this.y);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x + (2 * SQUARE_DIMENSION), this.y);
		} else if (this.orientation == Orientation.LEFT) {
			drawSquare(g, this.x, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y + (2 * SQUARE_DIMENSION));
		} else if (this.orientation == Orientation.RIGHT) {
			drawSquare(g, this.x, this.y);
			drawSquare(g, this.x, this.y + SQUARE_DIMENSION);
			drawSquare(g, this.x, this.y + (2 * SQUARE_DIMENSION));
			drawSquare(g, this.x + SQUARE_DIMENSION, this.y + SQUARE_DIMENSION);
		}

	}
	
	@Override
	public int[][] getOccupiedSquares(int[] startSquareIndex) {
		int[][] occupiedSquares = new int[4][2];
		
		int startX = startSquareIndex[0];
		int startY = startSquareIndex[1];
		
		if (this.orientation == Orientation.UP) {
			occupiedSquares[0] =  new int[] {startX, startY + 1};
			occupiedSquares[1] =  new int[] {startX + 1, startY};
			occupiedSquares[2] =  new int[] {startX + 1, startY + 1};
			occupiedSquares[3] =  new int[] {startX + 2, startY + 1};
		} else if (this.orientation == Orientation.DOWN) {
			occupiedSquares[0] =  new int[] {startX + 1, startY};
			occupiedSquares[1] =  new int[] {startX, startY + 1};	
			occupiedSquares[2] =  new int[] {startX + 1, startY + 1};
			occupiedSquares[3] =  new int[] {startX + 2, startY + 1};
		} else if (this.orientation == Orientation.LEFT) {
			occupiedSquares[0] =  new int[] {startX + 1, startY};
			occupiedSquares[1] =  new int[] {startX, startY + 1};
			occupiedSquares[2] =  new int[] {startX + 1, startY + 1};
			occupiedSquares[3] =  new int[] {startX + 1, startY + 2};
		} else if (this.orientation == Orientation.RIGHT) {
			occupiedSquares[0] =  new int[] {startX, startY};
			occupiedSquares[1] =  new int[] {startX, startY + 1};
			occupiedSquares[2] =  new int[] {startX + 1, startY + 1};
			occupiedSquares[3] =  new int[] {startX, startY + 2};
		}
		
		return occupiedSquares;
	}

}
