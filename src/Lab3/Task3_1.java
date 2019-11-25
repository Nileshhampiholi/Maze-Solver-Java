package Lab3;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class Task3_1 {
	static float rgb [];

		public static void main(String[] args)
		{	
	
		  Port s1 = LocalEV3.get().getPort("S1");
		  EV3ColorSensor colorSensor= new EV3ColorSensor(s1);
		  SensorMode color = colorSensor.getRGBMode();
		  SensorMode colorString = colorSensor.getColorIDMode();
		  rgb = new float[color.sampleSize()];		
		 	
		  while (Button.ESCAPE.isUp())
		  {
			  color.fetchSample(rgb, 0);
			  LCD.drawString("R G B "+rgb[0], 1, 1);
			  LCD.drawString("color "+colorString, 1, 2);
			
			  Delay.msDelay(1000);
			  
			  if(Button.ESCAPE.isDown())
			  {
				 colorSensor.close();
			  }
		  }
				
								
		
	}

}
