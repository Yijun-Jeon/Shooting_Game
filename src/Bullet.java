import java.awt.Point;

public class Bullet {
	
	private Point pt;
	private int degree;
	
	public Bullet(Plane plane) {
		pt = new Point(plane.getX(), plane.getY()-GameConstants.PLANEIMGHEIGHT/2);
	}
	public Bullet(Enemy enemy) {
		pt = new Point(enemy.getX(), enemy.getY()+enemy.imgHeight/2);
		degree = (int)(Math.random()*140) + 110;
	}
	public Bullet(Enemy enemy,Point point) {
		pt = new Point(point.x, point.y+enemy.imgHeight/2);
		degree = 180;
	}
	public Bullet(Point point) {
		pt = new Point(point.x , point.y-GameConstants.PLANEIMGHEIGHT/2);
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
	public boolean move() {
		checkPt();
		if(degree != -1) {
			pt.x -= GameConstants.ENEMYBULLETSPEED*Math.sin(Math.toRadians(degree));
		 	pt.y -= GameConstants.ENEMYBULLETSPEED*Math.cos(Math.toRadians(degree));
		}
		return getY() > GameConstants.GAMEBOARDHEIGHT;
	}
	public void checkPt() {
		if(getX() <= 0) {
			setX(0);
			degree = 360 - degree;
		}
		else if(getX() >= GameConstants.GAMEBOARDWIDTH) {
			setX(GameConstants.GAMEBOARDWIDTH);
			degree = 360 - degree; 
		}
	}
}
