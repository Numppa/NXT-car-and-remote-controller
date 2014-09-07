package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class CustomKeyListener implements KeyListener{
	private DataOutputStream dos;
	
	public CustomKeyListener(DataOutputStream dos) {
		this.dos = dos;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				dos.writeInt(Commands.TURN_LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				dos.writeInt(Commands.TURN_RIGHT);
				break;
			case KeyEvent.VK_UP:
				dos.writeInt(Commands.MOVE_FORWARD);
				break;
			case KeyEvent.VK_DOWN:
				dos.writeInt(Commands.MOVE_BACKWARD);
				break;
			case KeyEvent.VK_SPACE:
				dos.writeInt(Commands.PANIC_STOP);
				break;
			case KeyEvent.VK_S:
				dos.writeInt(Commands.SWEEP);
				break;
			}
			dos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
