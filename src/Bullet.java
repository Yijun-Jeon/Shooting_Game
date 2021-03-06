import java.awt.Point;

public class Bullet {
	
	private Point pt;
	private int degree;
	private int damage;
	public boolean bSpecial;
	
	public Bullet(int index) {
		pt = new Point(index*GameConstants.SPECIALBULLETIMGWIDTH + GameConstants.SPECIALBULLETIMGWIDTH/2 ,GameConstants.GAMEBOARDHEIGHT - GameConstants.SPECIALBULLETIMGHEIGHT/2);
		damage = 10;
		bSpecial = true;
	}
	public Bullet(Plane plane) {
		pt = new Point(plane.getX(), plane.getY()-GameConstants.PLANEIMGHEIGHT/2);
		damage = 1;
		bSpecial = false;
	}
	public Bullet(Enemy enemy) {
		pt = new Point(enemy.getX(), enemy.getY()+enemy.imgHeight/2);
		degree = (int)(Math.random()*140) + 110;
		damage = 1;
		bSpecial = false;
	}
	public Bullet(Enemy enemy,Point point) {
		pt = new Point(point.x, point.y+enemy.imgHeight/2);
		degree = 180;
		damage = 1;
		bSpecial = false;
	}
	public Bullet(Point point) {
		pt = new Point(point.x , point.y-GameConstants.PLANEIMGHEIGHT/2);
		damage = 1;
		bSpecial = false;
	}
	public Bullet(Point point, int degree) {
		pt = new Point(point.x,point.y - GameConstants.BOSSIMGHEIGHT/2);
		damage = 1;
		bSpecial = false;
		this.degree = degree; 
	}
	public Bullet(Boss boss) {
		pt = new Point(boss.getX(),boss.getY() + GameConstants.BOSSIMGHEIGHT/2);
		damage = 1;
		bSpecial = false;
		degree = (int)(Math.random()*140) + 110; 
	}
	
	public void setX(int x) {pt.x = x;}
	public void setY(int y) {pt.y = y;}
	public void setPt(int x,int y) {setX(x); setY(y);}
	public void setPt(Point pt) {this.pt = new Point(pt);}
	
	public int getX() {return pt.x;}
	public int getY() {return pt.y;}
	public Point getPt() {return pt;}
	public int getDamage() {return damage;}
	
	public boolean moveAhead() {
		setY(getY()-GameConstants.PLANEBULLETSPEED);
		return getY() < 0;
	}
	public boolean move() {
		if(degree != -1) {
			pt.x -= GameConstants.ENEMYBULLETSPEED*Math.sin(Math.toRadians(degree));
		 	pt.y -= GameConstants.ENEMYBULLETSPEED*Math.cos(Math.toRadians(degree));
		}
		checkPt();
		return getY() > GameConstants.GAMEBOARDHEIGHT;
	}
	public void checkPt() {
		if(getX() < 0) {
			setX(0);
			degree = 360 - degree;
		}
		else if(getX() > GameConstants.GAMEBOARDWIDTH) {
			setX(GameConstants.GAMEBOARDWIDTH);
			degree = 360 - degree; 
		}
	}
}
