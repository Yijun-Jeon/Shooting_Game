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
		if(degree != -1) {
			pt.x -= 5*Math.sin(Math.toRadians(degree));
		 	pt.y -= 5*Math.cos(Math.toRadians(degree));
		 	checkPt();
		}
	}
	private void checkPt() {
		if(pt.x < 0)
			pt.x = 0;
		else if(pt.x > 625)
			pt.x = 625;
		if(pt.y < 0)
			pt.y = 0;
		else if(pt.y > 740)
			pt.y = 740;
	}
}
