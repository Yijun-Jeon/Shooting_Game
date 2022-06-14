import java.awt.Point;

public class Bullet {
	
	private Point pt;
	
	public Bullet(Plane plane) {
		pt = new Point(plane.getX(), plane.getY()-GameConstants.PLANEIMGHEIGHT/2);
	}
	public Bullet(Enemy enemy) {
		pt = new Point(enemy.getX(), enemy.getY()+GameConstants.ENEMYIMGHEIGHT/2);
	}
	
	public void setX(int x) {pt.x = x;}
	public void setY(int y) {pt.y = y;}
	public void setPt(int x,int y) {setX(x); setY(y);}
	public void setPt(Point pt) {this.pt = new Point(pt);}
	
	public int getX() {return pt.x;}
	public int getY() {return pt.y;}
	public Point getPt() {return pt;}
	
	public boolean moveAhead() {
		setY(getY()-GameConstants.PLANEBULLETSPEED);
		return getY() < 0;
	}
	public boolean moveDown() {
		setY(getY()+GameConstants.ENEMYBULLETSPEED);
		return getY() > GameConstants.GAMEBOARDHEIGHT;
	}
}
