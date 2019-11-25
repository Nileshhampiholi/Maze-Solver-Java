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
	   int threshould1 = 10, threshould2 = 5, threshould3 = 2 , alpha, distance, radius =3;
	   while(Button.ESCAPE.isUp())
	   {
		 distance= (int)	computeDistance(distanceSensor);
		 LCD.drawString("Distance="+ distance+ "cm", 1,1);
		 Delay.msDelay(200);
		 alpha = computeAngle(distance,radius);
		 if (distance>threshould1)
		 {
			  forward(alpha,1000);
		 	  Delay.msDelay(500);
		      //break;
		 }
		 else if (distance<threshould2)
		 {
		      forward(alpha,100);
		      Delay.msDelay(500);
		      //break;
		 }
		 else if (distance<threshould3)
		 {
			  stop();
		      Delay.msDelay(500);
		      senseColor(colorSensor);
		      Delay.msDelay(2000);
		      Button.waitForAnyPress();
		      //break;
	     }     
		      
		 if(Button.ESCAPE.isDown())
		 {
			  distanceSensor.close();
			  colorSensor.close();
			  break;
		 }
		      
	     
	  }
	  
	 
    }

	private static void senseColor( EV3ColorSensor colorSensor) {
		
		 float colorID[];
		 String x[] = {"NONE", "BLACK","BLUE", "GREEN","YELLOW","RED", "WHITE","BROWN"};
		 SensorMode colorString = colorSensor.getColorIDMode(); 
		 colorID = new float[colorString.sampleSize()];
		 colorString.fetchSample(colorID,0);
		 LCD.drawString("color "+ String.valueOf(colorID[0]), 1, 4);
		 for(int i=0; i<=7; i++)
		  {
		  	if (colorID[0]==i)
		  	{
		  		LCD.drawString(x[i], 1, 5);
		  	}
		  }
		
	}

	private static void stop() {
		
		left.stop();
		right.stop();
	}

	private static void forward(int alpha, int x) {
		
		left.setSpeed(x);
		right.setSpeed(x);
		left.rotate(alpha, true);
		right.rotate(alpha,true);
		Delay.msDelay(200);
		//while ( left.isMoving() && right.isMoving())
		//{
		//	LCD.drawString("Is Moving", 3,4);
		//	Delay.msDelay(500);
		//}
	}
		


	private static float computeDistance(EV3UltrasonicSensor distanceSensor) {
		
		float s[];
		SampleProvider distance = distanceSensor.getDistanceMode();
	    s = new float[distance.sampleSize()];
	    distance.fetchSample(s, 0);
	    return (s[0]*100);   		
	}
	
	public static int computeAngle( int distance, double radius)
	{
		int alpha = (int) ((distance/radius)*360);
		alpha = 3*(alpha);
		LCD.drawString("alpha = "+ alpha, 3,3);
		return alpha;
		
	}

}
