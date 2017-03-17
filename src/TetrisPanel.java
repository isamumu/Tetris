import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

//This class contains the panels, the contents of the frame

//things to note for the TetrisPanel
//1.background color
//2.borders
//3.make it ready for keypresses

//border should be a constant

//interfaces is a set of methods with no bodies, implementing promises that a class has those methods of the interface
public class TetrisPanel extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L; // Ignore this line

	// below are constants, variables that cannot be altered anywhere
	public static final int SPEED_OF_SHAPE = 1 * Shape.SQUARE_DIMENSION;
	public static final int HEIGHT_SQUARES = 30;
	public static final int WIDTH_SQUARES = 20;
	
	// Border variables
	public static final int BORDER_WIDTH = 2;
	public static final int BORDER_PADDING = 10;//Shape.SQUARE_DIMENSION;
	public static final int TOTAL_BORDER_WIDTH = BORDER_WIDTH + BORDER_PADDING;
	
	public static final Color BACKGROUND_COLOR = Color.GRAY;
	
	// variables initialized in the top of the class should obtain a value later
	// in the code
	public Shape currentShape;
	boolean[][] stickBox; 
	Color[][] stickBoxColors;
	int[] startSquareIndex;
	
	public TetrisPanel() {
		this.stickBox = new boolean[WIDTH_SQUARES][HEIGHT_SQUARES];
		this.stickBoxColors = new Color[WIDTH_SQUARES][HEIGHT_SQUARES];
		this.startSquareIndex = new int[2];
		
		setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING),
				new LineBorder(Color.WHITE, BORDER_WIDTH)));
		
		//What does the code below do?
		// This is the total width of the whole panel, INCLUDING all borders
		// Must take into account borders on top, bottom, left and right, hence the 2 * TOTAL_BORDER_WIDTH
		int panelWidth = (WIDTH_SQUARES * Shape.SQUARE_DIMENSION) + (2 * TOTAL_BORDER_WIDTH);
		int panelHeight = (HEIGHT_SQUARES * Shape.SQUARE_DIMENSION) + (2 * TOTAL_BORDER_WIDTH);
		Dimension dimensions = new Dimension(panelWidth, panelHeight);// (width, height)
		
		setPreferredSize(dimensions);
		setSize(dimensions);

		this.setBackground(BACKGROUND_COLOR);
		this.setFocusable(true); // have to be able to use keys in this panel
		this.addKeyListener(this);
		
		this.currentShape = getRandomShape();

		Timer timer = new Timer(100, this); // create a timer that goes off at
											// specific times and does something
											// at that time. That is why
											// ActionListener is implemented
//		timer.start(); // starts the time

	}
	
	private int getLeftBounds() {
		return TOTAL_BORDER_WIDTH;
	}
	
	private int getRightBounds() {
		return this.getWidth() - TOTAL_BORDER_WIDTH;
	}
	
	private int getUpBounds() {
		return TOTAL_BORDER_WIDTH;
	}
	
	private int getDownBounds() {
		return this.getHeight() - TOTAL_BORDER_WIDTH;
	}

	private Shape getRandomShape() {
		Shape newShape;

		int rightBounds = this.getRightBounds();
		int leftBounds = this.getLeftBounds();
		int downBounds = this.getDownBounds();
		
		int startX = ((leftBounds + rightBounds) / 2) - Shape.SQUARE_DIMENSION;
		int startY = this.getUpBounds();
		startSquareIndex[0] = (startX - TOTAL_BORDER_WIDTH) / Shape.SQUARE_DIMENSION;
		startSquareIndex[1] = 0;
		
		Orientation startOrient = Orientation.UP;

		int randomNum = (int) ((Math.random() * 5) + 1);

		if (randomNum == 1) {
			newShape = new Square(startX, startY, rightBounds, leftBounds, downBounds, startOrient);
		} else if (randomNum == 2) {
			newShape = new LeftL(startX, startY, rightBounds, leftBounds, downBounds, startOrient);
		} else if (randomNum == 3) {
			newShape = new RightL(startX, startY, rightBounds, leftBounds, downBounds, startOrient);
		} else if (randomNum == 4) {
			newShape = new Line(startX, startY, rightBounds, leftBounds, downBounds, startOrient);
		} else {
			newShape = new T(startX, startY, rightBounds, leftBounds, downBounds, startOrient);
		}

		return newShape;
	}

	// paintcomponent is necessary for drawing
	// graphics must be drawn below
	@Override // purposely override because it already exists in painComponent
				// of JPanel
	public void paintComponent(Graphics g) { // Graphics g is like a canvas, everything will be written on g
		super.paintComponent(g);  // super gets everything inside JPanel's paintComponent
		
		for(int i = 0; i < WIDTH_SQUARES; i++){
			for(int j = 0; j < HEIGHT_SQUARES; j++){
				int x_coord = (i * Shape.SQUARE_DIMENSION) + TOTAL_BORDER_WIDTH;
				int y_coord = (j * Shape.SQUARE_DIMENSION) + TOTAL_BORDER_WIDTH;
								
				if(this.stickBox[i][j]){
					g.setColor(this.stickBoxColors[i][j]);
					g.fillRect(x_coord, y_coord, Shape.SQUARE_DIMENSION, Shape.SQUARE_DIMENSION);
				}
				
				// Draw a grid for debugging
				// Comment out for the real game, don't delete
				g.setColor(Color.RED);
				g.drawRect(x_coord, y_coord, Shape.SQUARE_DIMENSION, Shape.SQUARE_DIMENSION);
			}
		}
		
		this.currentShape.drawSelf(g);
	}

	// below is a method from the ActionListener interface
	@Override
	public void actionPerformed(ActionEvent e) {
		moveShape(KeyEvent.VK_DOWN); // when the timer goes off, move the shape

	}

	public void moveShape(int keyCode) {
		Orientation prevOrientation = this.currentShape.orientation;
		int prevX = this.currentShape.x;
		int prevY = this.currentShape.y;
		int [] prevStartSquareIndex = new int[2];
		prevStartSquareIndex[0] = this.startSquareIndex[0];
		prevStartSquareIndex[1] = this.startSquareIndex[1];
		
		if (keyCode == KeyEvent.VK_LEFT) {
			this.currentShape.x -= SPEED_OF_SHAPE;
			this.startSquareIndex[0] -= 1;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			this.currentShape.x += SPEED_OF_SHAPE;
			this.startSquareIndex[0] += 1;
		} else if (keyCode == KeyEvent.VK_DOWN) {
			this.currentShape.y += SPEED_OF_SHAPE;
			this.startSquareIndex[1] += 1;
		} else if (keyCode == KeyEvent.VK_SPACE) {
			this.currentShape.rotate();
		} else if (keyCode == KeyEvent.VK_UP) { // JUST FOR TESTING, REMOVE
			this.currentShape = getRandomShape();
		}
		// No else, just do nothing if another key was pressed
		// this.getHeight(); //this is the height the panel including the
		// border
		
		boolean resetShape = false;
	
		if(this.currentShape.isOutOfBounds()) {
			resetShape = true;
		} else {
			// If shape is not out of bounds, check for collisions with other shapes
			int [][] occupiedSquares = this.currentShape.getOccupiedSquares(this.startSquareIndex);
			
			for(int[] occupiedSquare : occupiedSquares){
				if(stickBox[occupiedSquare[0]][occupiedSquare[1]]){
					resetShape = true;
					break;
				}
			}
		}

		
		if (resetShape) {
			this.currentShape.setOrientation(prevOrientation);
			this.currentShape.x = prevX;
			this.currentShape.y = prevY;
			this.startSquareIndex[0] = prevStartSquareIndex[0];
			this.startSquareIndex[1] = prevStartSquareIndex[1];
			
			// Check whether to sticky the shape
			if(keyCode == KeyEvent.VK_DOWN){
				int [][] occupiedSquares = this.currentShape.getOccupiedSquares(this.startSquareIndex);
				for(int[] occupiedSquare : occupiedSquares){
					this.stickBox[occupiedSquare[0]][occupiedSquare[1]] = true;
					this.stickBoxColors[occupiedSquare[0]][occupiedSquare[1]] = this.currentShape.color;
				}
				
				// Clear all the lines
				for (int n = 0; n < HEIGHT_SQUARES; n++) {
					// Should we clear line n?
					boolean clearLine = true;
					for (int l = 0; l < WIDTH_SQUARES; l++) {
						if (!stickBox[l][n]) {
							clearLine = false;
						}
					}
					
					if(clearLine){
						// Clear line n
						for(int i = 0; i < WIDTH_SQUARES; i++) {
							this.stickBoxColors[i][n] = BACKGROUND_COLOR;
							this.stickBox[i][n] = false;
						}
						
						// Shift lines above line n down as a whole
						for(int lineToShift = n - 1; lineToShift >= 0; lineToShift--) {
							int currentLine = lineToShift;
							
							while(true) {
								// If currentLine is the bottom line, don't attempt to move it down
								if ((currentLine + 1) >= HEIGHT_SQUARES) {
									break;									
								}
								
								// Check if you can move down the line
								boolean canMoveLineDown = true;
								
								for(int i = 0; i < WIDTH_SQUARES; i++){
									if(stickBox[i][currentLine] && stickBox[i][currentLine + 1]){
										canMoveLineDown = false;
									}
								}
								
								if(canMoveLineDown) {
									// Move currentLine down by 1
									for(int i = 0; i < WIDTH_SQUARES; i++){
										if(stickBox[i][currentLine]){
											// Set the square below
											this.stickBox[i][currentLine + 1] = true;
											this.stickBoxColors[i][currentLine + 1] = this.stickBoxColors[i][currentLine];
											
											// Clear this square
											this.stickBox[i][currentLine] = false;
											this.stickBoxColors[i][currentLine] = BACKGROUND_COLOR;
										}
									}
									
									currentLine++;
								} else {
									break; // Can't move down anymore, stop moving line altogether
								}
							}
						}
					}

				}
				
				// Get the next shape
				this.currentShape = getRandomShape();
			}
		}

		this.repaint(); // repaint from paintComponent. Must be repainted LAST
		
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		moveShape(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
