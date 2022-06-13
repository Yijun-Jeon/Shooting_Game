import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class Plane{
	
	private Point pt;
	Queue<Bullet> bullets;
	
	public Plane() {
		pt = new Point(315,740);
		bullets = new LinkedList<Bullet>();
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
		if(pt.x < 0)
			pt.x = 0;
		else if(pt.x > 625)
			pt.x = 625;
		if(pt.y < 0)
			pt.y = 0;
		else if(pt.y > 740)
			pt.y = 740;
	}
	
	public void shoot(boolean bShoot) {
		if(bShoot)
			bullets.offer(new Bullet(this));
	}
	public void dequeueBullet() {
		if(!(bullets.isEmpty()))
			if(bullets.peek().getY() < 0)
				bullets.poll();
	}
}
