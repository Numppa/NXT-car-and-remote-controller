package platform;

import lejos.nxt.Button;
import lejos.nxt.Sound;

/**
 * Example leJOS Project with an ant build file
 *
 */
public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome! ");
		
		Connector conn = new Connector();
		
		new Thread(conn).start();
		
		CommandExecutor executor = new CommandExecutor();
		InputHandler ih = new InputHandler(executor);
		OutputHandler oh = new OutputHandler();
		
		
		while (!conn.connectionEstablished()){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
		
		ih.setDataInputStream(conn.getConnection().openDataInputStream());
		oh.setDataOutputStream(conn.getConnection().openDataOutputStream());
		
		
		new Thread(executor).start();
		new Thread(ih).start();
		new Thread(oh).start();
		
		System.out.println("All threads running");
		Sound.playTone(440, 100, 50);
		
		Button.waitForPress();
		System.exit(0);
	}
}
