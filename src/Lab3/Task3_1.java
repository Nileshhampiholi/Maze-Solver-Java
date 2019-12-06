package Lab3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

public class Task3_1 {
	static float rgb [], c[];
    static String x[] = {"NONE", "BLACK","BLUE", "GREEN","YELLOW","RED", "WHITE","BROWN"};
		public static void main(String[] args)
		{	
	
		  EV3ColorSensor colorSensor= new EV3ColorSensor(SensorPort.S1);
		  SensorMode color = colorSensor.getRGBMode();
		  SensorMode colorString = colorSensor.getColorIDMode();
		  rgb = new float[color.sampleSize()];		
		 	
		  while (Button.ESCAPE.isUp())
		  {
			  color.fetchSample(rgb, 0);
			  LCD.drawString("R= "+ rgb[0], 1, 1);
			  LCD.drawString("G= "+ rgb[1], 1, 2);
			  LCD.drawString("B=" + rgb[2], 1, 3);
			  c = new float[colorString.sampleSize()];
			  colorString.fetchSample(c,0);
			  LCD.drawString("color "+ String.valueOf(c[0]), 1, 4);
			  for(int i=0; i<=7; i++)
			  {
			  	if (c[0]==i)
			  	{
			  		LCD.drawString(x[i], 1, 5);
			  	}
			  }
			 
			  Delay.msDelay(1000);
			  
			  if(Button.ESCAPE.isDown())
			  {
				 colorSensor.close();
			  }
		  }
		  
		}
}


