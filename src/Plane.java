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
	public void setPt(int x,int y) {setX(x); setY(y);}
	public void setPt(Point pt) {this.pt = new Point(pt);}
	
	public int getX() {return pt.x;}
	public int getY() {return pt.y;}
	public Point getPt() {return pt;}
	
	public void move(int degree) {
		 int x = getX() - 20*(int)Math.sin(Math.toRadians(degree));
		 int y = getY() - 20*(int)Math.cos(Math.toRadians(degree));
		 
		 setX(x);setY(y);
	}
}
