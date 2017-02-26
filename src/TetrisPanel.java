import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;
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
	public static final int BORDER_WIDTH = 0;
	public static final int SPEED_OF_SHAPE = 1 * Shape.SQUARE_DIMENSION;
	public static final int HEIGHT_SQUARES = 50;
	public static final int WIDTH_SQUARES = 20;
	
	// variables initialized in the top of the class should obtain a value later
	// in the code
	public Shape currentShape;
	boolean[][] stickBox; 
	Color[][] stickBoxColors;
	
	public TetrisPanel() {
		
		this.stickBox = new boolean[WIDTH_SQUARES][HEIGHT_SQUARES];
		this.stickBoxColors = new Color[WIDTH_SQUARES][HEIGHT_SQUARES];
		
		//What does the code below do?
		int panelWidth = (WIDTH_SQUARES * Shape.SQUARE_DIMENSION) + BORDER_WIDTH;
		int panelHeight = (HEIGHT_SQUARES * Shape.SQUARE_DIMENSION) + BORDER_WIDTH;
		Dimension dimensions = new Dimension(panelWidth, panelHeight);// (width, height)
		setPreferredSize(dimensions);
		setSize(dimensions);

		this.setBackground(Color.GRAY);
		this.setBorder(new LineBorder(Color.BLACK, BORDER_WIDTH));
		this.setFocusable(true); // have to be able to use keys in this panel
		this.addKeyListener(this);
		
		this.currentShape = getRandomShape();

		Timer timer = new Timer(100, this); // create a timer that goes off at
											// specific times and does something
											// at that time. That is why
											// ActionListener is implemented
		timer.start(); // starts the time

	}

	private Shape getRandomShape() {
		Shape newShape;
		int startX = this.getWidth() / 2;
		int startY = BORDER_WIDTH;
		int rightBounds = this.getWidth() - BORDER_WIDTH;
		int leftBounds = BORDER_WIDTH;
		int downBounds = this.getHeight() - BORDER_WIDTH;
		Orientation startOrient = Orientation.UP;
		
		System.out.println(getHeight());
		System.out.println(getWidth());

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
	public void paintComponent(Graphics g) { // Graphics g is like a canvas,
												// everthing will be written on
												// g
		super.paintComponent(g); // super gets everything inside JPanel's
									// paintComponent
		this.currentShape.drawSelf(g);
		
		for(int i = 0; i < WIDTH_SQUARES; i++){
			for(int j = 0; j < HEIGHT_SQUARES; j++){
				if(this.stickBox[i][j]){
					g.setColor(this.stickBoxColors[i][j]);
					g.fillRect(i * Shape.SQUARE_DIMENSION, j * Shape.SQUARE_DIMENSION, Shape.SQUARE_DIMENSION, Shape.SQUARE_DIMENSION);
				}
			}
		}
		

	}

	// below is a method from the ActionListener interface
	@Override
	public void actionPerformed(ActionEvent e) {
		moveShape(KeyEvent.VK_DOWN); // when the timer goes off, move the shape

	}

	public void moveShape(int keyCode) {
		int prevX = this.currentShape.x;
		int prevY = this.currentShape.y;

		if (keyCode == KeyEvent.VK_LEFT) {
			this.currentShape.x -= SPEED_OF_SHAPE;
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			this.currentShape.x += SPEED_OF_SHAPE;
			// Check boundaries and increase nextX by SPEED
		} else if (keyCode == KeyEvent.VK_DOWN) {
			this.currentShape.y += SPEED_OF_SHAPE;
		} else if (keyCode == KeyEvent.VK_SPACE) {
			this.currentShape.rotate();
		} else if (keyCode == KeyEvent.VK_UP) { // JUST FOR TESTING, REMOVE
			this.currentShape = getRandomShape();
		}
		// No else, just do nothing if another key was pressed
		// this.getHeight(); //this is the height the panel including the
		// border

		if (this.currentShape.isOutOfBounds()) {
			this.currentShape.x = prevX;
			this.currentShape.y = prevY;
		}

		this.repaint(); // repaint from paintComponent. Must be repainted LAST
		
		
		// Check whether to sticky the shape
		
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
