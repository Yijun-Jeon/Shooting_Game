import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener{
	
	public final static int UP		= 0x001;
	public final static int DOWN	= 0x002;
	public final static int LEFT	= 0x004;
	public final static int RIGHT	= 0x008;
	
	public final static int LEFTDEGREE 		= 90;
	public final static int RIGHTDEGREE 	= 270;
	public final static int	UPDEGREE 		= 0;
	public final static int DOWNDEGREE	 	= 180;
	public final static int LEFTUPDEGREE	= 45;
	public final static int LEFTDOWNDEGREE 	= 135;
	public final static int RIGHTUPDEGREE 	= 315;
	public final static int RIGHTDOWNDEGREE = 225;
	
	private BackgroundPanel panel;
	int keyBuff;
	
	public KeyController(BackgroundPanel p) {
		panel = p;
		panel.requestFocus(true);
		panel.setFocusable(true);
		keyBuff = 0;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch(keyCode) {
		case KeyEvent.VK_ENTER:
			if(panel.status == 0) {
				panel.lblScore.setVisible(true);
				panel.lblStart.setRun(false);
				panel.status = 1;
			}
			break;
		case KeyEvent.VK_LEFT:
			keyBuff |= LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			keyBuff |= RIGHT;
			break;
		case KeyEvent.VK_UP:
			keyBuff |= UP;
			break;
		case KeyEvent.VK_DOWN:
			keyBuff |= DOWN;
			break;
		}
		keyHandle();
		keyBuff = 0;
		panel.repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			keyBuff &= ~LEFT;
			break;
		case KeyEvent.VK_UP:
			keyBuff &= ~UP;
			break;
		case KeyEvent.VK_RIGHT:
			keyBuff &= ~RIGHT;
			break;
		case KeyEvent.VK_DOWN:
			keyBuff &= ~DOWN;
			break;
		}
	}
	
	public void keyHandle() {
		switch(keyBuff) {
		case LEFT:
			panel.degree = LEFTDEGREE;
			break;
		case RIGHT:
			panel.degree = RIGHTDEGREE;
			break;
		case UP:
			panel.degree = UPDEGREE;
			break;
		case DOWN:
			panel.degree = DOWNDEGREE;
			break;
		case LEFT | UP:
			panel.degree = LEFTUPDEGREE;
			break;
		case RIGHT | UP:
			panel.degree = RIGHTUPDEGREE;
			break;
		case LEFT | DOWN:
			panel.degree = LEFTDOWNDEGREE;
			break;
		case RIGHT | DOWN:
			panel.degree = RIGHTDOWNDEGREE;
			break;
		}
	}

}
