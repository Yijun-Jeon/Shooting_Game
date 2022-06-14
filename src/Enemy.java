import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class Enemy {
	
	Point pt;
	int enemyNum;
	Vector<Bullet> bullets;
	long shootTime;
	
	public Enemy(int num) {
		pt = new Point((int)(Math.random()*(GameConstants.GAMEBOARDWIDTH - GameConstants.ENEMYIMGWIDTH)) + GameConstants.ENEMYIMGWIDTH/2,GameConstants.ENEMYIMGHEIGHT/2);
		enemyNum = num;
		bullets = new Vector<Bullet>();
		bullets.add(new Bullet(this));
		shootTime = System.currentTimeMillis();
	}
	
	public void setX(int x) {pt.x = x;}
	public void setY(int y) {pt.y = y;}
	public void setPt(int x,int y) {setX(x); setY(y);}
	public void setPt(Point pt) {this.pt = new Point(pt);}
	
	public int getX() {return pt.x;}
	public int getY() {return pt.y;}
	public Point getPt() {return pt;}
	
	public void initEnemy() {
		setY(GameConstants.ENEMYIMGHEIGHT/2);
		bullets.clear();
	}
	
	public boolean moveDown() {
		setY(getY()+GameConstants.ENEMYDOWNSPEED);
		return getY() > GameConstants.GAMEBOARDHEIGHT;
	}
	
	public void shoot() {
		long time = System.currentTimeMillis();
		if((time-shootTime)/1000 >= GameConstants.ENEMYSHOOTTERM) {
			bullets.add(new Bullet(this));
			shootTime = time;
		}
	}
	public void removeBullet(int index) {
		try {
			bullets.remove(index);
		}catch(Exception e) {return;}
	}
}
