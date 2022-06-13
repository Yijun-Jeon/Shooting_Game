import java.awt.Point;

public class Bullet {
	
	private Point pt;
	
	public Bullet(Plane plane) {
		pt = new Point(plane.getX() + 49, plane.getY() - 50);
	}
	public Bullet(Enemy enemy) {
		pt = new Point(enemy.getX() + 37, enemy.getY() + 60);
	}
	
	public void setX(int x) {pt.x = x;}
	public void setY(int y) {pt.y = y;}
	public void setPt(int x,int y) {setX(x); setY(y);}
	public void setPt(Point pt) {this.pt = new Point(pt);}
	
	public int getX() {return pt.x;}
	public int getY() {return pt.y;}
	public Point getPt() {return pt;}
	
	public void moveAhead() {
		setY(getY()-5);
	}
	public void moveDown() {
		setY(getY()+2);
	}
}
