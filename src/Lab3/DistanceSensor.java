package Lab3;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
public class DistanceSensor {
	public EV3UltrasonicSensor distanceSensor;
	public DistanceSensor() {
		distanceSensor = new EV3UltrasonicSensor(SensorPort.S4);
	}
	public static void main(String[] args) {
		DistanceSensor instance = new DistanceSensor();
		float distance= 0.0f;
		while(Button.ESCAPE.isUp())
		{
		distance = instance.computeDistance();
		LCD.drawString("Distance="+ distance+ "cm", 1,1);
		Delay.msDelay(1000);
		
		if(Button.ESCAPE.isDown())
		{
			instance.close();
		}
		}
	}
	public float computeDistance() {
		float s[];
		SampleProvider distance = distanceSensor.getDistanceMode();
	    s = new float[distance.sampleSize()];
	    distance.fetchSample(s, 0);
	    s[0]= s[0]*100;
	    return s[0];
	}
	public void close() {
		distanceSensor.close();
	}

}
