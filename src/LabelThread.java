import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelThread extends JLabel implements Runnable {
	
	private Thread lblThread;
	private boolean bRun;
	
	public LabelThread(ImageIcon img) {
		super(img);
		bRun = true;
	}
	
	public void start() {
		if(lblThread == null)
			lblThread = new Thread(this);
		lblThread.start();
	}
	
	public void setRun(boolean b) {bRun = b;}
	
	@Override
	public void run() {
		while(bRun) {
			this.setVisible(false);
			try {
				lblThread.sleep(100);
			}catch(Exception e) {return;}
			this.setVisible(true);
			try {
				lblThread.sleep(100);
			}catch(Exception e) {return;}
		}
		this.setVisible(false);
	}
}
