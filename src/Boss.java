import java.awt.Point;
import java.util.Vector;

public class Boss {
	Point pt;
	Vector<Bullet> bullets;
	int life;
	boolean isDead;
	boolean bInvicible;
	boolean first;
	boolean bShoot;
	boolean bAttack;
	
	public Boss() {
		pt = new Point(GameConstants.GAMEBOARDWIDTH/2, -GameConstants.BOSSIMGHEIGHT/2);
		bullets = new Vector<Bullet>();
		life = 100;
		isDead = false;
		bInvicible = true;
		first = true;
		bShoot = true;
		bAttack = false;
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
		setY(getY()-GameConstants.BOSSATTACKSPEED); 
	}
	public void attackDown() {
		setY(getY()+GameConstants.BOSSATTACKSPEED);
	}
	public boolean getDamaged(Bullet bullet) {
		life -= bullet.getDamage();
		int x = bullet.getX();
		int y = bullet.getY() - GameConstants.PLANEBULLETIMGHEIGHT/2;
		if(0 <= x && x < 140 ||
			180 < x && x < 210 ||
			242 < x && x < 275 ||
			308 < x && x < 340 ||
			396 < x && x < 427 ||
			462 < x && x < 494 ||
			527 < x && x < 560 ||
			593 < x && x <= 740) {
			if(y < 295 - GameConstants.BOSSIMGHEIGHT/2)
				return true;
		}
		else if(140 <= x && x <= 180 || 
				560 <= x && x <= 593) {
			if(y < 325 - GameConstants.BOSSIMGHEIGHT/2)
				return true;
		}
		else if(210 <= x && x <= 242 ||
				494 <= x && x <= 527 ) {
			if(y < 330 - GameConstants.BOSSIMGHEIGHT/2)
				return true;
		}
		else if(275 <= x && x <= 308 ||
				427 <= x && x <= 462 ) {
			if(y < 335 - GameConstants.BOSSIMGHEIGHT/2)
				return true;
		}
		else if(340 <= x && x <= 396) {
			if(y < 403 - GameConstants.BOSSIMGHEIGHT/2)
				return true;
		}
		life += bullet.getDamage();
		return false;
	}
	
	public boolean getAttacked(Plane plane) {
		int x = plane.getX();
		int y = plane.getY() - GameConstants.PLANEBULLETIMGHEIGHT/2;
		if(0 <= x && x < 140 ||
				180 < x && x < 210 ||
				242 < x && x < 275 ||
				308 < x && x < 340 ||
				396 < x && x < 427 ||
				462 < x && x < 494 ||
				527 < x && x < 560 ||
				593 < x && x <= 740) {
				if(y < 295 - GameConstants.BOSSIMGHEIGHT/2)
					return true;
			}
			else if(140 <= x && x <= 180 || 
					560 <= x && x <= 593) {
				if(y < 325 - GameConstants.BOSSIMGHEIGHT/2)
					return true;
			}
			else if(210 <= x && x <= 242 ||
					494 <= x && x <= 527 ) {
				if(y < 330 - GameConstants.BOSSIMGHEIGHT/2)
					return true;
			}
			else if(275 <= x && x <= 308 ||
					427 <= x && x <= 462 ) {
				if(y < 335 - GameConstants.BOSSIMGHEIGHT/2)
					return true;
			}
			else if(340 <= x && x <= 396) {
				if(y < 403 - GameConstants.BOSSIMGHEIGHT/2)
					return true;
			}
		return false;
	}
	public boolean isBossDead() {
		return life <= -6;
	}
	public void makeItem(Vector<Item> items,Bullet bullet) {
		int random = (int)(Math.random()*10000);
		if(random > 9900) {
			Item item = new Item(bullet.getPt(),2);
			items.add(item);
		}
		else if(random > 9700) {
			Item item = new Item(bullet.getPt(),1);
			items.add(item);
		}
	}
	public void removeBullet(int index) {
		try {
			bullets.remove(index);
		}catch(Exception e) {return;}
	}
	public void makeBullet() {
		bullets.add(new Bullet(new Point(160,325),210));
		bullets.add(new Bullet(new Point(228,330),250));
		bullets.add(new Bullet(new Point(293,335),225));
		bullets.add(new Bullet(new Point(447,335),150));
		bullets.add(new Bullet(new Point(511,330),130));
		bullets.add(new Bullet(new Point(577,325),170));
		bullets.add(new Bullet(new Point(368,403),180));
		bullets.add(new Bullet(this));
	}
}
