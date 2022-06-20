import java.awt.Point;
import java.util.Vector;

public class Enemy {
	
	final int damageDistance = GameConstants.ENEMYIMGHEIGHT/2;
	Point pt;
	Vector<Bullet> bullets;
	long shootTime;
	int life;
	boolean isDead;
	
	public Enemy() {
		pt = new Point((int)(Math.random()*(GameConstants.GAMEBOARDWIDTH - GameConstants.ENEMYIMGWIDTH)) + GameConstants.ENEMYIMGWIDTH/2,GameConstants.ENEMYIMGHEIGHT/2);
		bullets = new Vector<Bullet>();
		bullets.add(new Bullet(this));
		shootTime = System.currentTimeMillis();
		life = 3;
		isDead = false;
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
	public static int getDistance(Point a, Point b) {
		return (int)Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
	}
	
	public void shoot() {
		long time = System.currentTimeMillis();
		if(!isDead && (time-shootTime)/1000 >= GameConstants.ENEMYSHOOTTERM) {
			bullets.add(new Bullet(this));
			shootTime = time;
		}
	}
	public boolean isEnemyDead() {
		if(life <= 0)
			isDead = true;
		return isDead;
	}
	public void removeBullet(int index) {
		try {
			bullets.remove(index);
		}catch(Exception e) {return;}
	}
	public boolean getDamaged(Bullet bullet) {
		if(getDistance(pt,bullet.getPt()) < damageDistance) {
			life--;
			return true;
		}
		return false;
	}
	public void makeItem(Vector<Item> items) {
		int random = (int)(Math.random()*100);
		if(random > 90) {
			Item item = new Item(this.getPt(),2);
			items.add(item);
		}
		else if(random > 70) {
			Item item = new Item(this.getPt(),1);
			items.add(item);
		}
	}
}
