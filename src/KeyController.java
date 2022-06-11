import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener{
	
	public final static int UP		= 0x001;
	public final static int DOWN	= 0x002;
	public final static int LEFT	= 0x004;
	public final static int RIGHT	= 0x008;
	
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
			panel.degree = 90;
			break;
		case RIGHT:
			panel.degree = 270;
			break;
		case UP:
			panel.degree = 0;
			break;
		case DOWN:
			panel.degree = 180;
			break;
		case LEFT | UP:
			panel.degree = 45;
			break;
		case RIGHT | UP:
			panel.degree = 315;
			break;
		case LEFT | DOWN:
			panel.degree = 135;
			break;
		case RIGHT | DOWN:
			panel.degree = 225;
			break;
		}
	}

}
