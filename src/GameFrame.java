import javax.swing.JFrame;

public class GameFrame extends JFrame implements Runnable{
	BackgroundPanel primary;
	KeyController key;
	Thread GameThread;
	
	public GameFrame() {
		super("FLIGHT GROUND");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BackgroundPanel primary = new BackgroundPanel();
		KeyController key = new KeyController(primary);
		primary.addKeyController(key);

		getContentPane().add(primary);
		setLocation(400, 0);

		setResizable(false);	
		pack();
	}

	@Override
	public void run() {
		while(true) {
			primary.repaint();
			key.keyHandle();
		}
	}
}