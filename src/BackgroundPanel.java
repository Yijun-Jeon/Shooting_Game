import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BackgroundPanel extends JPanel{
	
	Image backgroundImg,logoImg, startImg,heartImg,scoreImg,bulletImg,planeImg;
	LabelThread lblStart;
	JLabel lblScore;
	
	Plane plane;
	
	int status;
	int score;
	int life;
	int degree;
	
	Image dbImg;
	Graphics dbPage;
	
	boolean bShoot;
	
	public BackgroundPanel() {
		this.setPreferredSize(new Dimension(740,830));
		setLayout(null);
		
		status = 0;
		life = 3;
		
		backgroundImg = new ImageIcon("./img/background.png").getImage();
		bulletImg = new ImageIcon("./img/bullet.png").getImage();
		planeImg = new ImageIcon("./img/plane.png").getImage();
	
		logoImg = new ImageIcon("./img/logo.png").getImage();
		
		lblStart = new LabelThread(new ImageIcon("./img/start.png"));
		lblStart.setBounds(120,500,500,200);
		add(lblStart);
		
		heartImg = new ImageIcon("./img/heart.png").getImage();
		
		score = 0;
		scoreImg = new ImageIcon("./img/score.png").getImage();
		lblScore = new JLabel("0");
		lblScore.setBounds(640,30,100,50);
		lblScore.setFont(new Font("Verdana",Font.BOLD+Font.ITALIC,20));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setForeground(Color.BLUE);
		lblScore.setVisible(false);
		add(lblScore);
		
		plane = new Plane();
		lblStart.start();
		
		bulletImg = new ImageIcon("./img/bullet.png").getImage();
		
		bShoot = false;
	}

	public void paint(Graphics page) {
		if(dbPage == null) {
			dbImg = createImage(this.getWidth(),this.getHeight());
			dbPage = dbImg.getGraphics();
		}
		update(page);
	}
	public void update(Graphics page) {
		if(dbPage == null)
			return;
		dbPaint();
		paintComponents(dbPage);
		page.drawImage(dbImg,0,0,null);
	}
	public void dbPaint() {
		dbPage.drawImage(backgroundImg,0,0,null);
		switch(status) {
		case 0:
			dbPage.drawImage(logoImg,30,130,null);
			break;
		case 1:
			drawStage(dbPage);
			drawPlane(dbPage);
			plane.shoot(bShoot);
			bShoot = false;
			drawBullet(dbPage);
			drawBullet(dbPage);
			break;
		}
	}
	
	public void addKeyController(KeyController key) {
		this.addKeyListener(key);
	}
	private void drawStage(Graphics page) {
		for(int i=0;i<life;i++)
			page.drawImage(heartImg,10 + 70*i,10,null);
		page.drawImage(scoreImg,630,0,null);
	}
	private void drawPlane(Graphics page) {
		plane.move(degree);
		page.drawImage(planeImg,plane.getX(),plane.getY(),null);	
	}
	private void drawBullet(Graphics page) {
		for(Bullet bullet: plane.bullets) {
			page.drawImage(bulletImg,bullet.getX(),bullet.getY(),null);
			bullet.moveAhead();
		}
		plane.dequeueBullet();
	}
}
