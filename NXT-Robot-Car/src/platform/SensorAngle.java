package platform;

public class SensorAngle {
	private int angle;
	private static SensorAngle sensorAngle;
	
	private SensorAngle() {
		this.angle = 0;
	}
	
	public int getAngle() {
		synchronized (sensorAngle) {
			return angle;
		}
	}
	
	public void addAngle(int i) {
		synchronized (sensorAngle) {
			angle += i;
			if (angle >= 360) {
				angle -= 360;
			} else if (angle < 0) {
				angle += 360;
			}
		}
	}
	
	
	public static SensorAngle getInstance() {
		if (sensorAngle == null) {
			sensorAngle = new SensorAngle();
		}
		return sensorAngle;
	}
}
