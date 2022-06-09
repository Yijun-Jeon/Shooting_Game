import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener{
	
	private BackgroundPanel panel;
	private boolean bStart;
		
	public KeyController(BackgroundPanel p) {
		panel = p;
		panel.requestFocus(true);
		panel.setFocusable(true);
		bStart = true;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_ENTER:
			if(bStart) {
				panel.lblStart.setRun(false);
				panel.lblLogo.setVisible(false);
				panel.lblHeart1.setVisible(true);
				panel.lblHeart2.setVisible(true);
				panel.lblHeart3.setVisible(true);
				panel.lblScore.setVisible(true);
				bStart = false;
			}
			break;
		case KeyEvent.VK_LEFT:
			panel.plane.setXLeft();
			break;
		case KeyEvent.VK_RIGHT:
			panel.plane.setXRight();
			break;
		case KeyEvent.VK_UP:
			panel.plane.setYUp();
			break;
		case KeyEvent.VK_DOWN:
			panel.plane.setYDown();
			break;
		}
		panel.plane.setBounds(panel.plane.getX(),panel.plane.getY(),panel.plane.planeImg.getIconWidth(),panel.plane.planeImg.getIconHeight());
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}
