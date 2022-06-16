import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener{
	
	public final static int UP		= 0x001;
	public final static int DOWN	= 0x002;
	public final static int LEFT	= 0x004;
	public final static int RIGHT	= 0x008;
	public final static int SHOOT	= 0x010;
	
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
		if(panel.status == 1) {
			switch(keyCode) {
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
			case KeyEvent.VK_SPACE:
				keyBuff |= SHOOT;
			}
		}else keyBuff = keyCode;
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
		case KeyEvent.VK_SPACE:
			keyBuff &= ~SHOOT;
			panel.bShoot = true;
			break;
		}
	}
	
	public void keyHandle() {
		switch(panel.status) {
		case 0:
			if(keyBuff == KeyEvent.VK_ENTER) {
				panel.lblStart.lblThread.interrupt();
				panel.initGame();
				keyBuff = 0;
			}
			break;
		case 1:
			switch(keyBuff) {
			case LEFT:
			case LEFT | SHOOT:
				panel.degree = LEFTDEGREE;
				break;
			case RIGHT:
			case RIGHT | SHOOT:
				panel.degree = RIGHTDEGREE;
				break;
			case UP:
			case UP | SHOOT:
				panel.degree = UPDEGREE;
				break;
			case DOWN:
			case DOWN | SHOOT:
				panel.degree = DOWNDEGREE;
				break;
			case LEFT | UP:
			case LEFT | UP | SHOOT:
				panel.degree = LEFTUPDEGREE;
				break;
			case RIGHT | UP:
			case RIGHT | UP | SHOOT:
				panel.degree = RIGHTUPDEGREE;
				break;
			case LEFT | DOWN:
			case LEFT | DOWN | SHOOT:
				panel.degree = LEFTDOWNDEGREE;
				break;
			case RIGHT | DOWN:
			case RIGHT | DOWN | SHOOT:
				panel.degree = RIGHTDOWNDEGREE;
				break;
			case SHOOT:
				panel.degree = -1;
				break;
			default:
				panel.degree = -1;
				keyBuff = 0;
			}
			break;
		}
	}

}
