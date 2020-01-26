package TraverseMaze;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class ColorSensor {
	
	private  EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
	private  int colorString;
	private  final int X=3 , Y= 3;
	
	public String senseColor() {
		
		while (Button.ESCAPE.isUp()){
			Delay.msDelay(1000);
			colorString = colorSensor.getColorID();
			switch(colorString){
			case Color.RED:
				LCD.drawString("RED", X, Y);
				return "RED";
			case Color.GREEN:
				LCD.drawString("GREEN", X, Y);
				return "GREEN";
			case Color.BLUE:
				LCD.drawString("BLUE", X, Y);
				return "BLUE";
			case Color.BLACK:
				LCD.drawString("BLACK", X, Y);
				return "BLACK";
			case Color.BROWN:
				LCD.drawString("BROWN", X, Y);
				return "BROWN";
			case Color.YELLOW:
				LCD.drawString("Yellow", X, Y);
				return "YELLOW";
			case Color.NONE:
				LCD.drawString("None", X, Y);
				return "NONE";
			}
		}
		return "NONE";  
	}	
}
