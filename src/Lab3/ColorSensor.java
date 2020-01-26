package Lab3;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;
public class ColorSensor {
	 EV3ColorSensor colorSensor;	 
	 public ColorSensor() {
		 colorSensor = new EV3ColorSensor(SensorPort.S1);
	 }
		public static void main(String[] args)
		{	
	     senseColor();
		}
		  
	 public static  void senseColor() {
		 ColorSensor instance = new ColorSensor();
		 float colorID[];
		 String x[] = {"NONE", "BLACK","BLUE", "GREEN","YELLOW","RED", "WHITE","BROWN"};
		 SensorMode colorString = instance.colorSensor.getColorIDMode();		 	
		 while (Button.ESCAPE.isUp())
		 {		  
			colorID = new float[colorString.sampleSize()];
			colorString.fetchSample(colorID,0);
			for(int i=0; i<=7; i++)
			{	
				if (colorID[0]==i)
				{LCD.drawString(x[i], 1, 5);}
				Delay.msDelay(500);
				LCD.clearDisplay();
			}		 
			Delay.msDelay(1000);				  
			if(Button.ESCAPE.isDown())
			{	instance.colorSensor.close();}		  
		 }	  
	}	
}


