import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	
	ImageIcon backgroundImg;
	
	public BackgroundPanel() {
		this.setPreferredSize(new Dimension(740,830));
		backgroundImg = new ImageIcon("./img/background.png");
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		page.drawImage(backgroundImg.getImage(),0,0,null);
	}
}
