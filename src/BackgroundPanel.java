import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BackgroundPanel extends JPanel {
	
	ImageIcon backgroundImg, logoImg, startImg,heartImg;
	JLabel lblLogo,lblHeart1,lblHeart2,lblHeart3;
	LabelThread lblStart;
	
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
		
		this.addKeyListener(new KeyBoardListener());
		this.requestFocus(true);
		this.setFocusable(true);
		
		lblStart.start();
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		page.drawImage(backgroundImg.getImage(),0,0,null);
	}
	
	public class KeyBoardListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			if(keyCode == KeyEvent.VK_ENTER) {
				lblStart.setRun(false);
				lblLogo.setVisible(false);
				lblHeart1.setVisible(true);
				lblHeart2.setVisible(true);
				lblHeart3.setVisible(true);
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
}
