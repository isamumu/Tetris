import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;

//this class contains the frame for graphics. Only does the running
public class Tetris {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Tetris");//the frame 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//from the JFrame class, operation specified by a constant
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));//layouts are classes
		frame.setSize(500, 500);
		TetrisPanel panel = new TetrisPanel();
		TetrisPanel tetrisPanel = new TetrisPanel();
		frame.add(tetrisPanel);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
	}
}
