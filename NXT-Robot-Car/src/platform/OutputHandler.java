package platform;

import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;

public class OutputHandler implements Runnable{
	private DataOutputStream dos;
	private UltrasonicSensor us;
	
	public OutputHandler() {
		this.dos = null;
		this.us = new UltrasonicSensor(SensorPort.S1);
	}
	
	public void setDataOutputStream(DataOutputStream dos) {
		this.dos = dos;
	}

	@Override
	public void run() {
		int[] is = new int[8];
		us.setMode(UltrasonicSensor.MODE_PING);
		while (true) {
			int i = us.ping();
			if (i < 0) {
				continue;
			}
			i = us.getDistances(is);
			if (i < 0) {
				continue;
			}
			
			
			
		}
	}
}
