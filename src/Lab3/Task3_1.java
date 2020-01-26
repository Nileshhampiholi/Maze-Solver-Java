package Lab3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
//import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class Task3_1 {
	static float rgb [], c[];
	static String z[] = {"NONE", "BLACK","BLUE", "GREEN","YELLOW","RED", "WHITE","BROWN"};
	static int x =2 ,y =2;
	static  EV3ColorSensor colorSensor= new EV3ColorSensor(SensorPort.S1);
	public static void main(String[] args)
	{	
		while(true) {
			String s= detect();
			LCD.drawString(s, 1, 1);
			Delay.msDelay(500);
		}  

	}

	public static String detect() {
		
		while (Button.ESCAPE.isUp())
		{
			int colorString = colorSensor.getColorID();
			switch(colorString){
			case Color.RED:
				LCD.drawString("RED", x, y);
				return "RED";

			case Color.GREEN:
				LCD.drawString("Green", x, y);
				return "GREEN";

			case Color.BLUE:
				LCD.drawString("Blue", x, y);
				return "BLUE";
			case Color.BLACK:
				LCD.drawString("Black", x, y);
				return "BLACK";
			case Color.BROWN:
				LCD.drawString("Brown", x, y);
				return "BROWN";
			case Color.DARK_GRAY:
				LCD.drawString("Dark Gray", x, y);
				return "DARK_GRAY";
			case Color.LIGHT_GRAY:
				LCD.drawString("Light Gray", x, y);
				return "LIGHT_GRAY";
			case Color.ORANGE:
				LCD.drawString("Orange", x, y);
				return "ORANGE";
			case Color.NONE:
				LCD.drawString("None", x, y);
				return "NONE";
			case Color.YELLOW:
				LCD.drawString("Yellow", x, y);
				return "YELLOW";
			}
		}
		return "none";  
	}
}


