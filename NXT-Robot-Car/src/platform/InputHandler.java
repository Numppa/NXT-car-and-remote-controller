package platform;

import java.io.DataInputStream;
import java.io.IOException;

public class InputHandler implements Runnable{
	private DataInputStream dis;
	private CommandExecutor executor;
	
	
	
	public InputHandler(CommandExecutor executor) {
		this.dis = null;
		this.executor = executor;
	}
	
	public void setDataInputStream(DataInputStream dis) {
		this.dis = dis;
	}
	
	@Override
	public void run() {
		try {
			int i = dis.readInt();
		
			while(i != Commands.SHUTDOWN) {
				executor.addCommand(i);
				i = dis.readInt();
			}
			
		} catch (IOException e) {
			System.out.println("Problem with input stream. ");
		}
		System.out.println("Shutting down...");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		System.exit(0);
	}

}
