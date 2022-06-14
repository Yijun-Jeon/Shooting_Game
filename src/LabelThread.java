import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelThread extends JLabel implements Runnable {
	
	Thread lblThread;
	
	public LabelThread(ImageIcon img) {
		super(img);
	}
	
	public void start() {
		if(lblThread == null)
			lblThread = new Thread(this);
		lblThread.start();
	}
	
	@Override
	public void run() {
		while(!lblThread.isInterrupted()) {
			this.setVisible(false);
			try {
				lblThread.sleep(100);
			}catch(Exception e) {return;}
			this.setVisible(true);
			try {
				lblThread.sleep(100);
			}catch(Exception e) {return;}
		}
	}
}
