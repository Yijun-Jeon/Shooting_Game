import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameEx {

	public static void main(String[] args) {
		JFrame frame = new JFrame("YJ Shooting Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BackgroundPanel primary = new BackgroundPanel();
		frame.getContentPane().add(primary);
		frame.setLocation(400, 0);
		
		frame.setResizable(false);	
		frame.pack();
		frame.setVisible(true);
	}
}
