import java.awt.Point;

public class Enemy {
	
	Point pt;
	int enemyNum;
	
	public Enemy(int num) {
		pt = new Point((int)(Math.random()*625),0);
		enemyNum = num;
	}
	
	public void setX(int x) {pt.x = x;}
	public void setY(int y) {pt.y = y;}
	public void setPt(int x,int y) {setX(x); setY(y);}
	public void setPt(Point pt) {this.pt = new Point(pt);}
	
	public int getX() {return pt.x;}
	public int getY() {return pt.y;}
	public Point getPt() {return pt;}
	
	public boolean moveDown() {
		setY(getY()+1);
		return getY() > 830;
	}
	
}
