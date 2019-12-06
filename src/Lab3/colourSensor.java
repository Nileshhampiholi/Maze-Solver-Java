package Lab3;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class colourSensor {

	public static void main(String[] args) {
		
		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
		SensorMode color = colorSensor.getRGBMode();
	}
	public static void fetchSample(float [] sample, int offset) {
		
		
		
	}

	
}	

