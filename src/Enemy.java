import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class Enemy {
	
	Point pt;
	int enemyNum;
	Queue<Bullet> bullets;
	long shootTime;
	
	public Enemy(int num) {
		pt = new Point((int)(Math.random()*625),0);
		enemyNum = num;
		bullets = new LinkedList<Bullet>();
		shootTime = System.currentTimeMillis();
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
	
	public void shoot() {
		long time = System.currentTimeMillis();
		if((time-shootTime)/1000 >= 1) {
			bullets.offer(new Bullet(this));
			shootTime = time;
		}
	}
	public void dequeueBullet() {
		if(!(bullets.isEmpty()))
			if(bullets.peek().getY() < 0)
				bullets.poll();
	}
	
}
