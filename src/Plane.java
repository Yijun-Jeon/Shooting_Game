import java.awt.Point;
import java.util.Vector;

public class Plane{
	
	private Point pt;
	Vector<Bullet> bullets;
	private int damageDistance;
	
	public Plane() {
		pt = new Point(GameConstants.GAMEBOARDWIDTH/2,GameConstants.GAMEBOARDHEIGHT - GameConstants.PLANEIMGHEIGHT/2);
		bullets = new Vector<Bullet>();
		damageDistance = GameConstants.PLANEIMGHEIGHT/2;
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
			pt.x -= GameConstants.PLANESPEED*Math.sin(Math.toRadians(degree));
		 	pt.y -= GameConstants.PLANESPEED*Math.cos(Math.toRadians(degree));
		 	checkPt();
		}
	}
	private void checkPt() {
		if(pt.x < GameConstants.PLANEIMGWIDTH/2)
			pt.x = GameConstants.PLANEIMGWIDTH/2;
		else if(pt.x > GameConstants.GAMEBOARDWIDTH - GameConstants.PLANEIMGWIDTH/2)
			pt.x = GameConstants.GAMEBOARDWIDTH - GameConstants.PLANEIMGWIDTH/2;
		if(pt.y < GameConstants.PLANEIMGHEIGHT/2)
			pt.y = GameConstants.PLANEIMGHEIGHT/2;
		else if(pt.y > GameConstants.GAMEBOARDHEIGHT - GameConstants.PLANEIMGHEIGHT/2)
			pt.y = GameConstants.GAMEBOARDHEIGHT - GameConstants.PLANEIMGHEIGHT/2;
	}
	public static int getDistance(Point a, Point b) {
		return (int)Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}
	
	public void shoot(boolean bShoot) {
		if(bShoot)
			bullets.add(new Bullet(this));
	}
	public void removeBullet(int index) {
		try {
			bullets.remove(index);
		}catch(Exception e) {return;}
	}
	public boolean getDamaged(Bullet bullet) {
		if(getDistance(pt,bullet.getPt()) < damageDistance)
			return true;
		return false;
	}
}
