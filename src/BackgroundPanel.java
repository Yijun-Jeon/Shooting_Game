import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	
	ImageIcon backgroundImg, logoImg, startImg;
	JLabel lblLogo;
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
			}
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
}
