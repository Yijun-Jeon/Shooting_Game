import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BackgroundPanel extends JPanel{
	
	Image backgroundImg,logoImg, startImg,heartImg,scoreImg,bulletImg,planeImg,enemyImg,bulletEImg;
	LabelThread lblStart;
	JLabel lblScore;
	
	Plane plane;
	
	int status;
	int score;
	int life;
	int degree;
	int cnt;
	
	Image dbImg;
	Graphics dbPage;
	
	boolean bShoot;
	
	Vector<Enemy> enemies;
	long enemyTime;
	int enemyNum;
	
	
	public BackgroundPanel() {
		this.setPreferredSize(new Dimension(GameConstants.GAMEBOARDWIDTH,GameConstants.GAMEBOARDHEIGHT));
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
		
		enemyImg = new ImageIcon("./img/enemy1.png").getImage();
		bulletEImg = new ImageIcon("./img/bulletE.png").getImage();
		
		enemies = new Vector<Enemy>();
		enemyTime = System.currentTimeMillis();
		enemyNum = 1;
		
		cnt = 0;
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
			makeEnemy();
			drawEnemy(dbPage);
			plane.shoot(bShoot);
			bShoot = false;
			drawBullet(dbPage);
			drawBulletE(dbPage);
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
		page.drawImage(planeImg,plane.getX()-GameConstants.PLANEIMGWIDTH/2, plane.getY()-GameConstants.PLANEIMGHEIGHT/2,null);
;	}
	private void drawBullet(Graphics page) {
		for(Bullet bullet: plane.bullets) {
			page.drawImage(bulletImg,bullet.getX()-GameConstants.PLANEBULLETIMGWIDTH/2,bullet.getY()-GameConstants.PLANEBULLETIMGHEIGHT/2,null);
			bullet.moveAhead();
		}
		plane.dequeueBullet();
	}
	private void makeEnemy() {
		long time = System.currentTimeMillis();
		if((time - enemyTime)/1000 >= GameConstants.ENEMYMAKINGTERM) {
			enemies.add(new Enemy(enemyNum));
			enemyTime = time;
		}
	}
	private void drawEnemy(Graphics page) {
		for(int i=0;i<enemies.size();i++) {
			page.drawImage(enemyImg, enemies.elementAt(i).getX() - GameConstants.ENEMYIMGWIDTH/2, enemies.elementAt(i).getY() - GameConstants.ENEMYIMGHEIGHT/2, null);
			if(enemies.elementAt(i).moveDown())
				enemies.remove(i);
		}
	}
	private void drawBulletE(Graphics page) {
		for(Enemy enemy: enemies) {
			enemy.shoot();
			for(Bullet bullet: enemy.bullets) {
				page.drawImage(bulletEImg,bullet.getX()-GameConstants.ENEMYBULLETIMGWIDTH/2,bullet.getY()-GameConstants.ENEMYBULLETIMGHIEHGT/2,null);
				bullet.moveDown();
			}
			enemy.dequeueBullet();
		}
	}
}
