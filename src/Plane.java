import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Plane extends JLabel {
	
	static ImageIcon planeImg = new ImageIcon("./img/plane.png");
	private Point pt;
	
	public Plane() {
		super(planeImg);
		pt = new Point(315,740);
	}
	
	public void setX(int x) {pt.x = x;}
	public void setY(int y) {pt.y = y;}
	public void setPt(Point pt) {this.pt = new Point(pt);}
	
	public void setXLeft() {pt.x -= 15;}
	public void setXRight() {pt.x += 15;}
	public void setYUp() {pt.y -= 15;}
	public void setYDown() {pt.y += 15;}
	
	public int getX() {return pt.x;}
	public int getY() {return pt.y;}
	public Point getPt() {return pt;}
}
