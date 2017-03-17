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

	@Override
	public int[][] getOccupiedSquares(int[] startSquareIndex) {
		int[][] occupiedSquares = new int[4][2];
		
		int startX = startSquareIndex[0];
		int startY = startSquareIndex[1];
		
		occupiedSquares[0] =  new int[] {startX, startY};
		occupiedSquares[1] =  new int[] {startX + 1, startY};
		occupiedSquares[2] =  new int[] {startX, startY + 1};
		occupiedSquares[3] =  new int[] {startX + 1, startY + 1};
		
		return occupiedSquares;
	}


}
