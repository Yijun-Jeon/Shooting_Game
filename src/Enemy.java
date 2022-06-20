import java.awt.Point;
import java.util.Vector;

public class Enemy {
	
	int imgHeight;
	int imgWidth; 
	int damageDistance;
	Point pt;
	Vector<Bullet> bullets;
	long shootTime;
	int life;
	boolean isDead;
	int type;
	
	public Enemy(int num) {
		switch(num) {
		case 1:
			imgHeight = GameConstants.ENEMY1IMGHEIGHT;
			imgWidth = GameConstants.ENEMY1IMGWIDTH;
			break;
		case 2:
			imgHeight = GameConstants.ENEMY2IMGHEIGHT;
			imgWidth = GameConstants.ENEMY2IMGWIDTH;
			break;
		case 3:
			imgHeight = GameConstants.ENEMY3IMGHEIGHT;
			imgWidth = GameConstants.ENEMY3IMGWIDTH;
			break;
		}
		type = num;
		damageDistance = imgHeight/2;
		pt = new Point((int)(Math.random()*(GameConstants.GAMEBOARDWIDTH - imgWidth)) + imgWidth/2,imgHeight/2);
		bullets = new Vector<Bullet>();
		addBullet();
		shootTime = System.currentTimeMillis();
		life = 3 * num;
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
		setY(imgHeight/2);
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
			addBullet();
			shootTime = time;
		}
	}
	private void addBullet() {
		switch(type) {
		case 1:
			bullets.add(new Bullet(this));
			break;
		case 2:
			bullets.add(new Bullet(this));
			bullets.add(new Bullet(this));
			break;
		case 3:
			bullets.add(new Bullet(this,new Point(pt.x - GameConstants.ENEMYBULLETIMGWIDTH,pt.y)));
			bullets.add(new Bullet(this,pt));
			bullets.add(new Bullet(this,new Point(pt.x + GameConstants.ENEMYBULLETIMGWIDTH,pt.y)));
			break;
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
