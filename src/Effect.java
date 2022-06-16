import java.awt.Point;

public class Effect {
	private Point pt;
	private int cnt;
	private int type; // Dead || Damaged
	private boolean bDecre;
	Effect(Point point,int type){
		pt=new Point(point);
		cnt = 31;
		this.type = type;
		bDecre = false;
	}
	
	public void setX(int x) {pt.x = x;}
	public void setY(int y) {pt.y = y;}
	public void setPt(int x,int y) {setX(x); setY(y);}
	public void setPt(Point pt) {this.pt = new Point(pt);}
	
	public int getX() {return pt.x;}
	public int getY() {return pt.y;}
	public Point getPt() {return pt;}
	public int getCnt() {return (cnt/8) % 4;}
	public int getType() {return type;}
	
	public boolean decreCnt() {
		if(bDecre) --cnt;
		else bDecre = true;
		return cnt == -1;
	}
}
