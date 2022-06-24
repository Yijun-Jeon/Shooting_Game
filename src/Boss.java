import java.awt.Point;
import java.util.Vector;

public class Boss {
	Point pt;
	Vector<Bullet> bullets;
	int life;
	boolean isDead;
	boolean bInvicible;
	boolean first;
	
	public Boss() {
		pt = new Point(GameConstants.GAMEBOARDWIDTH/2, -GameConstants.BOSSIMGHEIGHT/2);
		bullets = new Vector<Bullet>();
		life = 100;
		isDead = false;
		bInvicible = true;
		first = true;
	}
	
	public void setX(int x) {pt.x = x;}
	public void setY(int y) {pt.y = y;}
	public void setPt(int x,int y) {setX(x); setY(y);}
	public void setPt(Point pt) {this.pt = new Point(pt);}
	
	public int getX() {return pt.x;}
	public int getY() {return pt.y;}
	public Point getPt() {return pt;}
	public int getBossLineY() {return GameConstants.BOSSIMGHEIGHT/2;}
	
	public void moveDown() {
		setY(getY()+GameConstants.BOSSDOWNSPEED); 
	}
	public void moveUp() {
		setY(getY()-GameConstants.BOSSDOWNSPEED); 
	}
	public void attackDown() {
		setY(getY()+GameConstants.BOSSATTACKSPEED);
	}
}
