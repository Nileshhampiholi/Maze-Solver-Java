package TraverseMaze;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class GyroscopeSensor {
	
	public static EV3GyroSensor gyroscopeSensor = new EV3GyroSensor(SensorPort.S3);
	public static SampleProvider gyro = gyroscopeSensor.getAngleMode();
    private float sample [];
    
	public int computeGyroscopeAngle () {
		sample = new float[gyro.sampleSize()];
		gyro.fetchSample(sample,0);
		LCD.drawString("Angle="+ String.valueOf(sample[0]), 1, 1);
		return (int) sample[0];	
	}
}
