import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener{
	
	public final static int UP			 = 0x001;
	public final static int DOWN		 = 0x002;
	public final static int LEFT		 = 0x004;
	public final static int RIGHT		 = 0x008;
	public final static int SHOOT		 = 0x010;
	public final static int SPECIALSHOOT = 0x020;
	
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
		if(panel.status == 1 || panel.status == 3) {
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
			case KeyEvent.VK_S:
				keyBuff |= SPECIALSHOOT;
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
		case KeyEvent.VK_S:
			keyBuff &= ~SPECIALSHOOT;
			panel.bSpecial = true;
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
		case 3:
			switch(keyBuff) {
			case LEFT:
			case LEFT | SHOOT:
			case LEFT | SPECIALSHOOT:
			case LEFT | SHOOT | SPECIALSHOOT:	
				panel.degree = LEFTDEGREE;
				break;
			case RIGHT:
			case RIGHT | SHOOT:
			case RIGHT | SPECIALSHOOT:
			case RIGHT | SHOOT | SPECIALSHOOT:
				panel.degree = RIGHTDEGREE;
				break;
			case UP:
			case UP | SHOOT:
			case UP | SPECIALSHOOT:
			case UP | SHOOT | SPECIALSHOOT:
				panel.degree = UPDEGREE;
				break;
			case DOWN:
			case DOWN | SHOOT:
			case DOWN | SPECIALSHOOT:
			case DOWN | SHOOT | SPECIALSHOOT:
				panel.degree = DOWNDEGREE;
				break;
			case LEFT | UP:
			case LEFT | UP | SHOOT:
			case LEFT | UP | SPECIALSHOOT:
			case LEFT | UP | SHOOT | SPECIALSHOOT:
				panel.degree = LEFTUPDEGREE;
				break;
			case RIGHT | UP:
			case RIGHT | UP | SHOOT:
			case RIGHT | UP | SPECIALSHOOT:
			case RIGHT | UP | SHOOT | SPECIALSHOOT:
				panel.degree = RIGHTUPDEGREE;
				break;
			case LEFT | DOWN:
			case LEFT | DOWN | SHOOT:
			case LEFT | DOWN | SPECIALSHOOT:
			case LEFT | DOWN | SHOOT | SPECIALSHOOT:
				panel.degree = LEFTDOWNDEGREE;
				break;
			case RIGHT | DOWN:
			case RIGHT | DOWN | SHOOT:
			case RIGHT | DOWN | SPECIALSHOOT:
			case RIGHT | DOWN | SHOOT | SPECIALSHOOT:
				panel.degree = RIGHTDOWNDEGREE;
				break;
			case SHOOT:
			case SPECIALSHOOT:
			case SHOOT | SPECIALSHOOT:
				panel.degree = -1;
				break;
			default:
				panel.degree = -1;
				keyBuff = 0;
			}
			break;
		case 2:
		case 4:
			if(keyBuff == KeyEvent.VK_ENTER)
				panel.moveToStart();
			keyBuff = 0;
			break;
		}
	}

}
