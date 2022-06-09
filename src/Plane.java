import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Plane extends JLabel {
	
	static ImageIcon planeImg = new ImageIcon("./img/plane.png");
	Point pt;
	
	public Plane() {
		super(planeImg);
		pt = new Point(315,740);
	}
}
