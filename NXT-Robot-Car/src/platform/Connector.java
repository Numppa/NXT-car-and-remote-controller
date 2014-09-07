package platform;

import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class Connector implements Runnable{
	private boolean connected;
	private BTConnection connection;
	
	public Connector() {
		this.connected = false;
		this.connection = null;
	}
	

	@Override
	public void run() {
		System.out.println("Waiting for BT connection... ");
		connection = Bluetooth.waitForConnection();
		connected = true;
		System.out.println("Connected");
	}
	
	public boolean connectionEstablished() {
		return connected;
	}
	
	public BTConnection getConnection() {
		return connection;
	}

}
