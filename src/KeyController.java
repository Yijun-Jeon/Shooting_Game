import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener{
	
	private BackgroundPanel panel;
		
	public KeyController(BackgroundPanel p) {
		panel = p;
		panel.requestFocus(true);
		panel.setFocusable(true);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_ENTER) {
			panel.lblScore.setVisible(true);
			panel.lblStart.setRun(false);
			panel.status = 1;
		}
		panel.repaint();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}
