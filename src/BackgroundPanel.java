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
	
	Image backgroundImg,logoImg,heartImg,scoreImg,
		  bulletImg,planeImg,enemyImg,bulletEImg,
		  effectImg;
	ImageIcon startImg;
	LabelThread lblStart;
	JLabel lblScore;
	
	Plane plane;
	
	int status;
	int score;
	int life;
	int degree;
	int damagedCnt;
	
	Image dbImg;
	Graphics dbPage;
	
	boolean bShoot;
	boolean bDamaged;
	
	Vector<Enemy> enemies;
	long enemyTime;
	int enemyNum;
	
	Vector<Effect> effects;
	int cnt;
	
	
	public BackgroundPanel() {
		this.setPreferredSize(new Dimension(GameConstants.GAMEBOARDWIDTH,GameConstants.GAMEBOARDHEIGHT));
		setLayout(null);
		
		status = 0;
		life = 3;
		
		backgroundImg = new ImageIcon("./img/background.png").getImage();
		startImg = new ImageIcon("./img/start.png");
		bulletImg = new ImageIcon("./img/bullet.png").getImage();
		planeImg = new ImageIcon("./img/plane.png").getImage();
	
		logoImg = new ImageIcon("./img/logo.png").getImage();
		
		lblStart = new LabelThread(startImg);
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
		
		damagedCnt = 0;
		bDamaged = false;
		
		effects = new Vector<Effect>();
		effectImg = new ImageIcon("./img/effect.png").getImage();
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
			drawEffect(dbPage);
			checkGameOver();
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
		lblScore.setText(score+"");
	}
	private void drawPlane(Graphics page) {
		plane.move(degree);
		if(bDamaged) {
			if(damagedCnt == 0)
				bDamaged = false;
			else if(damagedCnt-- % 3 == 0)
				page.drawImage(planeImg,plane.getX()-GameConstants.PLANEIMGWIDTH/2, plane.getY()-GameConstants.PLANEIMGHEIGHT/2,null);
		}
		else page.drawImage(planeImg,plane.getX()-GameConstants.PLANEIMGWIDTH/2, plane.getY()-GameConstants.PLANEIMGHEIGHT/2,null);
;	}
	private void drawBullet(Graphics page) {
		for(int i=0; i<plane.bullets.size();i++) {
			Bullet bullet = plane.bullets.elementAt(i);
			page.drawImage(bulletImg,bullet.getX()-GameConstants.PLANEBULLETIMGWIDTH/2,bullet.getY()-GameConstants.PLANEBULLETIMGHEIGHT/2,null);
			if(bullet.moveAhead())
				plane.removeBullet(i);
		}
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
			Enemy enemy = enemies.elementAt(i);
			if(enemy.isEnemyDead())
				continue;
			if(enemy.moveDown())
				enemies.remove(i);
			else {
				page.drawImage(enemyImg, enemy.getX() - GameConstants.ENEMYIMGWIDTH/2, enemy.getY() - GameConstants.ENEMYIMGHEIGHT/2, null);
				for(int j=0;j<plane.bullets.size();j++) {
					Bullet bullet = plane.bullets.elementAt(j);
					if(enemy.getDamaged(bullet)) {
						if(enemy.isEnemyDead()) {
							score += 100;
							effects.add(new Effect(enemy.getPt(),1));
							plane.removeBullet(j);
							break;
						}
						effects.add(new Effect(bullet.getPt(),2));
						plane.removeBullet(j);
					}
				}
				if(plane.getDamaged(enemy) && !bDamaged) {
					effects.add(new Effect(plane.getPt(),2));
					planeDamaged();
				}
			}
		}
	}
	private void drawBulletE(Graphics page) {
		for(Enemy enemy: enemies) {
			enemy.shoot();
			for(int i=0; i< enemy.bullets.size();i++) {
				Bullet bulletE = enemy.bullets.elementAt(i);
				page.drawImage(bulletEImg,bulletE.getX()-GameConstants.ENEMYBULLETIMGWIDTH/2,bulletE.getY()-GameConstants.ENEMYBULLETIMGHIEHGT/2,null);
				if(plane.getDamaged(bulletE) && !bDamaged) {
					effects.add(new Effect(bulletE.getPt(),2));
					enemy.removeBullet(i);
					planeDamaged();
				}
				else if((bulletE.moveDown()))
					enemy.removeBullet(i);
			}
		}
	}
	private void checkGameOver() {
		if(life < 0) {
			status = 0;
			lblScore.setVisible(false);
			lblStart = new LabelThread(startImg);
			lblStart.setBounds(120,500,500,200);
			add(lblStart);
			lblStart.start();
		}
	}
	public void initGame() {
		life = 3;
		score = 0;
		
		status = 1;
		damagedCnt = 0;
		plane.initPlane();
		
		enemies.clear();
		enemyTime = System.currentTimeMillis();
		enemyNum = 1;
		
		effects.clear();
		
		bShoot = false;
		lblScore.setVisible(true);
		lblStart.setVisible(false);
	}
	public void planeDamaged() {
		bDamaged = true;
		damagedCnt = 120;
		life--;
		plane.increBulletNum(bDamaged);
	}
	public void drawEffect(Graphics page) {
		for(int i=0;i<effects.size();i++) {
			Effect effect = effects.elementAt(i);
			page.setClip(effect.getX()-GameConstants.EFFECTIMGWIDTH/2,effect.getY()-GameConstants.EFFECTIMGHEIGHT,
					GameConstants.EFFECTIMGWIDTH,GameConstants.EFFECTIMGHEIGHT);
			page.drawImage(effectImg, effect.getX() - effect.getCnt()*GameConstants.EFFECTIMGWIDTH - GameConstants.EFFECTIMGWIDTH/2,
					effect.getY() - effect.getType()*GameConstants.EFFECTIMGHEIGHT,null);
			if(effect.decreCnt())
				effects.remove(i--);
		}
		page.setClip(0,0,GameConstants.GAMEBOARDWIDTH,GameConstants.GAMEBOARDHEIGHT);
	}
}
