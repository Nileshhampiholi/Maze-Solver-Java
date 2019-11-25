package Lab3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Task3_2 {
	public static EV3UltrasonicSensor distanceSensor = new EV3UltrasonicSensor(SensorPort.S4);
	public static void main(String[] args) {
	
		float distance= 0.0f;
		while(Button.ESCAPE.isUp())
		{
		distance = computeDistance(distanceSensor);
		LCD.drawString("Distance="+ distance, 1,1);
		Delay.msDelay(1000);
		
		if(Button.ESCAPE.isDown())
		{
			distanceSensor.close();
		}
		}
	}

	private static float computeDistance(EV3UltrasonicSensor distanceSensor) {
		
		float s[];
		SampleProvider distance = distanceSensor.getDistanceMode();
	    s = new float[distance.sampleSize()];
	    distance.fetchSample(s, 0);
	    return s[0];
	    
		
	}

}
