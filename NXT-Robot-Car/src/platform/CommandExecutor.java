package platform;

import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Motor;

public class CommandExecutor implements Runnable{
	private List<Integer> list;
	private boolean stop;
	
	public CommandExecutor() {
		this.list = new ArrayList<Integer>();
		this.stop = false;
	}

	@Override
	public void run() {
		while (true) {
			if (list.isEmpty()) {
				continue;
			}
			
			synchronized (list) {
				switch (list.get(0)) {
				case Commands.MOVE_FORWARD:
					Motor.A.rotate(45, true);
					Motor.C.rotate(45, true);
					break;
				case Commands.MOVE_BACKWARD: 
					Motor.A.rotate(-45, true);
					Motor.C.rotate(-45, true);
					break;
				case Commands.TURN_LEFT: 
					Motor.A.rotate(45, true);
					Motor.C.rotate(-45, true);
					break;
				case Commands.TURN_RIGHT: 
					Motor.A.rotate(-45, true);
					Motor.C.rotate(45, true);
					break;
				case Commands.PANIC_STOP:
					stop = true;
					Motor.A.stop();
					Motor.B.stop();
					Motor.C.stop();
					Motor.C.stop();
					list = new ArrayList<Integer>();
					list.add(-2);
					break;
				case Commands.SWEEP: 
					executeSweep();
					break;
				}
				list.remove(0);
			}
		}
	}

	
	private void executeSweep() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 360; i++) {
					if (stop) {
						return;
					}
					Motor.B.rotate(1);
					SensorAngle.getInstance().addAngle(1);
				}
				
				for (int i = 0; i < 360; i++) {
					if (stop) {
						return;
					}
					Motor.B.rotate(-1);
					SensorAngle.getInstance().addAngle(-1);
				}
			}
		}).start();
	}

	public void addCommand(int i) {
		synchronized (list) {
			list.add(i);
		}
	}

}
