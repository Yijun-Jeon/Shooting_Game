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
	
	Image backgroundImg,logoImg, startImg,heartImg,scoreImg;
	LabelThread lblStart;
	JLabel lblScore;
	Thread gameThread;
	
	Plane plane;
	
	int status;
	int score;
	int life;
	int degree; // 0, 45, 90, 135, 180, 225, 270, 315
	
	public BackgroundPanel() {
		this.setPreferredSize(new Dimension(740,830));
		setLayout(null);
		
		status = 0;
		life = 3;
		
		backgroundImg = new ImageIcon("./img/background.png").getImage();
	
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
		
		gameThread = new Thread();
		gameThread.start();
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		page.drawImage(backgroundImg,0,0,null);
		switch(status) {
		case 0:
			page.drawImage(logoImg,30,130,null);
			break;
		case 1:
			for(int i=0;i<life;i++)
				page.drawImage(heartImg,10 + 70*i,10,null);
			page.drawImage(scoreImg,630,0,null);
			plane.move(degree);
			page.drawImage(Plane.planeImg.getImage(),plane.getX(),plane.getY(),null);
			break;
		}
	}
	
	public void addKeyController(KeyController key) {
		this.addKeyListener(key);
	}

}
