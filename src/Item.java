import java.awt.Point;

public class Item {
	private Point pt;
	private int cnt;
	private int type;
	
	public Item(Point point,int type){
		pt=new Point(point);
		cnt = 0;
		this.type = type;
	}
	
	public void setX(int x) {pt.x = x;}
	public void setY(int y) {pt.y = y;}
	public void setPt(int x,int y) {setX(x); setY(y);}
	public void setPt(Point pt) {this.pt = new Point(pt);}
	
	public int getX() {return pt.x;}
	public int getY() {return pt.y;}
	public Point getPt() {return pt;}
	public int getType() {return type;}
	
	public int getCnt(){
		cnt = ++cnt%91;
		return cnt/13;
	}
	public boolean moveDown() {
		setY(getY()+GameConstants.ITEMDOWNSPEED);
		return getY() > GameConstants.GAMEBOARDHEIGHT;
	}
}
