import javax.swing.JFrame;

public class GameFrame extends JFrame implements Runnable{
	private BackgroundPanel panel;
	private KeyController key;
	private Thread GameThread;
	
	public GameFrame() {
		super("FLIGHT GROUND");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new BackgroundPanel();
		key = new KeyController(panel);
		panel.addKeyController(key);

		getContentPane().add(panel);
		setLocation(400, 0);

		setResizable(false);	
		pack();
	}
	
	public void start() {
		setVisible(true);
		if (GameThread == null)
			GameThread = new Thread(this);
		GameThread.start();
	}
	
	public void run() {
		while(true) {
			try {
				panel.repaint();
				key.keyHandle();
			}
			catch(Exception e) {e.printStackTrace();}
		}
	}
}