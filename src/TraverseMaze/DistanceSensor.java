package TraverseMaze;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class DistanceSensor {

	private EV3UltrasonicSensor distanceSensor= new EV3UltrasonicSensor(SensorPort.S4);
	private float d[];

	public int computeDistance() {
		SampleProvider distance = distanceSensor.getDistanceMode();
		d = new float[distance.sampleSize()];
		distance.fetchSample(d, 0);
		d[0]= d[0]*100;
		return  (int) d[0];  
	}

}
