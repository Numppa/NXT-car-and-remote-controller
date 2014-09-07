package main;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.pc.comm.NXTConnector;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		NXTConnector conn = new NXTConnector();
		
		if(!conn.connectTo("btspp://")) {
			System.exit(1);
		}
		
		
		
		final DataOutputStream dos = conn.getDataOut();
		final DataInputStream dis = conn.getDataIn();
		
		final Frame frame = new Frame("NXT Radar");
		frame.setBounds(200, 100, 1000, 700);
		frame.setVisible(true);
		frame.addKeyListener(new CustomKeyListener(dos));
		
		addWindowListener(dos, frame);
		
	}

	private static void addWindowListener(final DataOutputStream dos,
			final Frame frame) {
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {	
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					dos.writeInt(Commands.SHUTDOWN);
					dos.flush();
					System.exit(0);
				} catch (IOException e) {
					e.printStackTrace();
				}
				frame.dispose();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
			}
		});
	}

}
