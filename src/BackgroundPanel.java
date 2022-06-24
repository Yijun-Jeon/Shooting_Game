import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BackgroundPanel extends JPanel{
	
	Image backgroundImg,logoImg,heartImg,scoreImg,
		  gameboardImg,bulletImg,planeImg,bulletEImg,
		  effectImg,itemHeartImg,itemBulletImg,
		  gameoverImg,specialBulletImg,specialGaugeImg,
		  bossImg;
	Image[] enemyImgs;
	
	ImageIcon startImg;
	LabelThread lblStart;
	JLabel lblScore;
	
	Plane plane;
	
	int status;
	int score;
	int degree;
	int damagedCnt;
	int gauge;
	int gaugeCnt;
	int bckCnt;
	int bckIndex;
	
	Image dbImg;
	Graphics dbPage;
	
	boolean bShoot;
	boolean bDamaged;
	boolean bSpecial;
	
	Vector<Enemy> enemies;
	long enemyTime;
	int enemyNum;
	
	Vector<Effect> effects;
	int cnt;
	
	Vector<Item> items;
	
	
	public BackgroundPanel() {
		this.setPreferredSize(new Dimension(GameConstants.GAMEBOARDWIDTH,GameConstants.GAMEBOARDHEIGHT));
		setLayout(null);
		
		status = 0;
		
		backgroundImg = new ImageIcon("./img/background.png").getImage();
		gameboardImg = new ImageIcon("./img/gameboard.jpg").getImage();
		startImg = new ImageIcon("./img/start.png");
		bulletImg = new ImageIcon("./img/bullet.png").getImage();
		planeImg = new ImageIcon("./img/plane.png").getImage();
		logoImg = new ImageIcon("./img/logo.png").getImage();
		gameoverImg = new ImageIcon("./img/gameover.png").getImage();
		
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
		
		bulletEImg = new ImageIcon("./img/bulletE.png").getImage();
		
		enemies = new Vector<Enemy>();
		enemyTime = System.currentTimeMillis();
		enemyNum = 0;
		
		damagedCnt = 0;
		bDamaged = false;
		
		effects = new Vector<Effect>();
		effectImg = new ImageIcon("./img/effect.png").getImage();
		
		itemBulletImg = new ImageIcon("./img/itemBullet.png").getImage();
		itemHeartImg = new ImageIcon("./img/itemHeart.png").getImage();
		
		items = new Vector<Item>();
		
		enemyImgs = new Image[3];
		enemyImgs[0] = new ImageIcon("./img/enemy1.png").getImage();
		enemyImgs[1] = new ImageIcon("./img/enemy2.png").getImage();
		enemyImgs[2] = new ImageIcon("./img/enemy3.png").getImage();
		
		specialBulletImg = new ImageIcon("./img/specialbullet.png").getImage();
		bSpecial = false;
		
		specialGaugeImg = new ImageIcon("./img/specialgauge.png").getImage();
		gauge = 0;
		gaugeCnt = 0;
		
		bckCnt = 0;
		bckIndex = 0;
		
		bossImg = new ImageIcon("./img/boss.png").getImage();
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
		switch(status) {
		case 0:
			dbPage.drawImage(backgroundImg,0,0,null);
			dbPage.drawImage(logoImg,30,130,null);
			break;
		case 1:
			drawGameboard(dbPage);
			drawStage(dbPage);
			drawPlane(dbPage);
			makeEnemy();
			drawEnemy(dbPage);
			plane.shoot(bShoot);
			bShoot = false;
			drawBullet(dbPage);
			drawBulletE(dbPage);
			drawEffect(dbPage);
			drawItem(dbPage);
			checkGameOver();
			dbPage.drawImage(bossImg,0,-200,null);
			break;
		case 2:
			drawGameboard(dbPage);
			drawStage(dbPage);
			drawEnemy(dbPage);
			drawBulletE(dbPage);
			drawEffect(dbPage);
			drawItem(dbPage);
			dbPage.drawImage(gameoverImg, 150, 200, null);
		case 3:
			drawGameboard(dbPage);
			drawStage(dbPage);
			drawPlane(dbPage);
			drawEnemy(dbPage);
			plane.shoot(bShoot);
			bShoot = false;
			drawBullet(dbPage);
			drawBulletE(dbPage);
			drawEffect(dbPage);
			drawItem(dbPage);
			checkGameOver();
			break;
		}
	}
	
	public void addKeyController(KeyController key) {
		this.addKeyListener(key);
	}
	private void drawGameboard(Graphics page) {
		page.setClip(0,0,GameConstants.GAMEBOARDWIDTH,bckIndex);
		page.drawImage(gameboardImg,0,-(GameConstants.GAMEBOARDHEIGHT - bckIndex),null);
		page.setClip(0, bckIndex, GameConstants.GAMEBOARDWIDTH, GameConstants.GAMEBOARDHEIGHT - bckIndex);
		page.drawImage(gameboardImg,0,bckIndex,null);
		page.setClip(0,0,GameConstants.GAMEBOARDWIDTH,GameConstants.GAMEBOARDHEIGHT);
		if(bckCnt++ % 3 == 0)
			bckIndex++;
		if(bckIndex == GameConstants.GAMEBOARDHEIGHT)
			bckIndex = 0;
		
	}
	private void drawStage(Graphics page) {
		for(int i=0;i<plane.getLife();i++)
			page.drawImage(heartImg,10 + 70*i,10,null);
		page.drawImage(scoreImg,630,0,null);
		lblScore.setText(score+"");
		if(gauge == 100) {
			if(gaugeCnt++ % 200 < 100)
				page.drawImage(specialGaugeImg, 450, 5, null);
		}else page.drawImage(specialGaugeImg, 450, 5, null);
		page.setColor(Color.cyan);
		page.drawRect(510, 30, 100, 20);
		page.fillRect(510, 30, gauge, 20);
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
		if(bSpecial) {
			if(gauge == 100) {
				plane.specialShoot();
				gauge = 0;
				gaugeCnt = 0;
				planeInvicible();
			}
			bSpecial = false;
		}
		for(int i=0; i<plane.bullets.size();i++) {
			Bullet bullet = plane.bullets.elementAt(i);
			if(bullet.bSpecial)
				page.drawImage(specialBulletImg,bullet.getX()-GameConstants.SPECIALBULLETIMGWIDTH/2,bullet.getY()-GameConstants.SPECIALBULLETIMGHEIGHT/2,null);
			else
				page.drawImage(bulletImg,bullet.getX()-GameConstants.PLANEBULLETIMGWIDTH/2,bullet.getY()-GameConstants.PLANEBULLETIMGHEIGHT/2,null);
			if(bullet.moveAhead())
				plane.removeBullet(i);
		}
	}
	private void makeEnemy() {
		long time = System.currentTimeMillis();
		if((time - enemyTime)/1000 >= GameConstants.ENEMYMAKINGTERM) {
			if(enemyNum % 5 == 0)
				enemies.add(new Enemy(3));
			else if((enemyNum-3) % 5 == 0)
				enemies.add(new Enemy(2));
			else
				enemies.add(new Enemy(1));
			enemyTime = time;
			enemyNum++;
			checkProcess();
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
				page.drawImage(enemyImgs[enemy.type-1], enemy.getX() - enemy.imgWidth/2, enemy.getY() - enemy.imgHeight/2, null);
				for(int j=0;j<plane.bullets.size();j++) {
					Bullet bullet = plane.bullets.elementAt(j);
					if(bullet.bSpecial) {
						if(enemy.getSpecialDamaged(bullet)) {
							if(enemy.isEnemyDead()) {
								score += 100;
								effects.add(new Effect(enemy.getPt(),1));
								enemy.makeItem(items);
								break;
							}
						}
					}
					else {
						if(enemy.getDamaged(bullet)) {
							if(enemy.isEnemyDead()) {
								score += 100;
								effects.add(new Effect(enemy.getPt(),1));
								plane.removeBullet(j);
								enemy.makeItem(items);
								break;
							}
							effects.add(new Effect(bullet.getPt(),2));
							plane.removeBullet(j);
							gauge = gauge == 100 ? 100 : gauge + 5;
						}
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
				else if((bulletE.move()))
					enemy.removeBullet(i);
			}
		}
	}
	private void checkGameOver() {
		if(plane.getLife() < 0) {
			effects.add(new Effect(plane.getPt(),1));
			status = 2;
		}
	}
	public void moveToStart() {
		status = 0;
		lblScore.setVisible(false);
		lblStart = new LabelThread(startImg);
		lblStart.setBounds(120,500,500,200);
		add(lblStart);
		lblStart.start();
	}
	public void initGame() {
		score = 0;
		
		status = 1;
		damagedCnt = 0;
		plane.initPlane();
		
		enemies.clear();
		enemyTime = System.currentTimeMillis();
		enemyNum = 1;
		
		effects.clear();
		items.clear();
		
		bShoot = false;
		lblScore.setVisible(true);
		lblStart.setVisible(false);
	}
	public void planeDamaged() {
		planeInvicible();
		plane.decreLife();
		plane.increBulletNum(bDamaged);
	}
	public void planeInvicible() {
		bDamaged = true;
		damagedCnt = 120;
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
	public void drawItem(Graphics page) {
		for(int i=0;i<items.size();i++) {
			Item item = items.elementAt(i);
			page.setClip(item.getX(), item.getY(), GameConstants.ITEMIMGWIDTH, GameConstants.ITEMIMGHEIGHT);
			switch(item.getType()) {
			case 1:
				page.drawImage(itemBulletImg, item.getX() - item.getCnt()*GameConstants.ITEMIMGWIDTH, item.getY(), null);
				break;
			case 2:
				page.drawImage(itemHeartImg, item.getX() - item.getCnt()*GameConstants.ITEMIMGWIDTH, item.getY(), null);
				break;
			}
			if(item.moveDown() || plane.takeItem(item))
				items.remove(i);
		}
		page.setClip(0,0,GameConstants.GAMEBOARDWIDTH,GameConstants.GAMEBOARDHEIGHT);
	}
	public void checkProcess() {
		if(enemyNum % 10 == 0) 
			GameConstants.ENEMYSHOOTTERM = GameConstants.ENEMYSHOOTTERM == 1 ? 1 : GameConstants.ENEMYSHOOTTERM-1; 
		else if(enemyNum % 5 == 0) 
			GameConstants.ENEMYMAKINGTERM = GameConstants.ENEMYMAKINGTERM == 1 ? 1 : GameConstants.ENEMYMAKINGTERM-1;
	}
}
