package Lab3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Task3_3 
{

	public static EV3UltrasonicSensor distanceSensor = new EV3UltrasonicSensor(SensorPort.S4);
	static EV3LargeRegulatedMotor left=new EV3LargeRegulatedMotor(MotorPort.B);
	static EV3LargeRegulatedMotor right=new EV3LargeRegulatedMotor(MotorPort.C);
	static  EV3ColorSensor colorSensor= new EV3ColorSensor(SensorPort.S1);
	
	public static void main(String[] args) 
	{
	   double threshould = 6.0,  distance=0.0; 
	   distance= computeDistance(distanceSensor);
	   if(distance> threshould)
	   {
		   forward(500);
	   }
	   while(Button.ESCAPE.isUp())
	   {
		 distance= computeDistance(distanceSensor);
		 LCD.drawString("Distance="+ (int)distance+ "cm", 1,1);
		
		 Delay.msDelay(200);
		 if (distance<threshould)
		 {
			  stop();
		      Delay.msDelay(1000);
		      senseColor(colorSensor);
		      Delay.msDelay(5000);
		      distance= computeDistance(distanceSensor);
		      if (distance>threshould)
		      {
		    	  forward(500);
		      }
	     }     
		      
		 if(Button.ESCAPE.isDown())
		 {
			  distanceSensor.close();
			  colorSensor.close();
			  break;
		 }
		  LCD.clear();    
	     
	  }
	  
	 
    }

	public static void senseColor( EV3ColorSensor colorSensor) {
		
		 float colorID[];
		 String x[] = {"NONE", "BLACK","BLUE", "GREEN","YELLOW","RED", "WHITE","BROWN"};
		 SensorMode colorString = colorSensor.getColorIDMode(); 
		 colorID = new float[colorString.sampleSize()];
		 colorString.fetchSample(colorID,0);
		 //LCD.drawString("color "+ String.valueOf(colorID[0]), 1, 4);
		 for(int i=0; i<=7; i++)
		  {
		  	if (colorID[0]==i)
		  	{
		  		LCD.drawString(x[i], 4, 4);
		  	}
		  }
		
	}

	public static void stop() {
		
		left.stop();
		right.stop();
	}

	public static void forward(int x) {
		
		left.setSpeed(x);
		right.setSpeed(x);
		left.backward();
		right.backward();
	}
		


	public static double computeDistance(EV3UltrasonicSensor distanceSensor) {
		
		float s[];
		SampleProvider distance = distanceSensor.getDistanceMode();
	    s = new float[distance.sampleSize()];
	    distance.fetchSample(s, 0);
	    return (s[0]*100);   		
	}
	
}
