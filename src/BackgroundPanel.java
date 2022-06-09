import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BackgroundPanel extends JPanel {
	
	ImageIcon backgroundImg, startImg,heartImg,scoreImg;
	JLabel lblLogo,lblHeart1,lblHeart2,lblHeart3,lblScore;
	LabelThread lblStart;
	
	Plane plane;
	
	int score;
	
	public BackgroundPanel() {
		this.setPreferredSize(new Dimension(740,830));
		setLayout(null);
		backgroundImg = new ImageIcon("./img/background.png");
	
		lblLogo = new JLabel(new ImageIcon("./img/logo.png"));
		lblLogo.setBounds(30, 130, 700, 100);
		add(lblLogo);
		
		lblStart = new LabelThread(new ImageIcon("./img/start.png"));
		lblStart.setBounds(120,500,500,200);
		add(lblStart);
		
		heartImg = new ImageIcon("./img/heart.png");
		lblHeart1 = new JLabel(heartImg);
		lblHeart2 = new JLabel(heartImg);
		lblHeart3 = new JLabel(heartImg);
		lblHeart1.setBounds(10,10,70,60);
		lblHeart2.setBounds(80,10,70,60);
		lblHeart3.setBounds(150,10,70,60);
		add(lblHeart1); add(lblHeart2); add(lblHeart3);
		lblHeart1.setVisible(false); lblHeart2.setVisible(false); lblHeart3.setVisible(false);
		
		score = 0;
		scoreImg = new ImageIcon("./img/score.png");
		lblScore = new JLabel("0",scoreImg,SwingConstants.CENTER);
		lblScore.setBounds(630,0,100,80);
		lblScore.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblScore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblScore.setFont(new Font("Verdana",Font.BOLD+Font.ITALIC,20));
		lblScore.setForeground(Color.BLUE);
		add(lblScore);
		lblScore.setVisible(false);
		
		plane = new Plane();
		plane.setBounds(plane.getX(),plane.getY(),plane.planeImg.getIconWidth(),plane.planeImg.getIconHeight());
		add(plane);
		
		lblStart.start();
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		page.drawImage(backgroundImg.getImage(),0,0,null);
	}
	
	public void addKeyController(KeyController key) {
		this.addKeyListener(key);
	}
}
