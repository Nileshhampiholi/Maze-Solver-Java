package Lab3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

import lejos.utility.Delay;

public class Task3_3
{

	static EV3LargeRegulatedMotor left=new EV3LargeRegulatedMotor(MotorPort.B);  //starting sensors and motors so that they remain switched On for entire Execution
	static EV3LargeRegulatedMotor right=new EV3LargeRegulatedMotor(MotorPort.C);
	static  EV3ColorSensor colorSensor= new EV3ColorSensor(SensorPort.S1);

	public static void main(String[] args)
	{
		obstacleAndColorDetection();
    }

	public static void senseColor( EV3ColorSensor colorSensor) {

		 float colorID[];
		 String X[] = {"NONE", "BLACK","BLUE", "GREEN","YELLOW","RED", "WHITE","BROWN"};// Storing all the colors in an array according to sample provider such that they can be accessed using loops
		 SensorMode colorString = colorSensor.getColorIDMode();
		 colorID = new float[colorString.sampleSize()];     //  defining size of colorID array
		 colorString.fetchSample(colorID,0);
		 // get rid of the loops and just use LCD.drawString(X[ (int) colorID[0] ], 4, 4);
		 for(int i=0; i<=7; i++)
		  {
		  	if (colorID[0]==i)      //  using the colorID value to compare with and print colors
		  	{
		  		LCD.drawString(X[i], 4, 4);
		  	}
		  }

	}

	public static void stop() {           // Function to stop the motor

		left.stop(true);
		right.stop(true);
	}

	public static void forward(int x) {                                     // function to move forward using motors and set their speed

		left.setSpeed(x);
		right.setSpeed(x);
		left.backward();
		right.backward();
	}

	public static void obstacleAndColorDetection()        //  function for detecting obstacle stopping and sensing colour
	{
			// uppercase only for static final
			// plus: final variables make no sense in scope of methods
	    final double THRESHOLD = 6.0;  // set max distance of the obstacle to 6cm
		double distance=0.0;
		DistanceSensor instance = new DistanceSensor();//                 calling instance of distance sensor

		   distance=instance.computeDistance();//                      computing distance using computeDistance function in class dDistance Sensor
		   if(distance> THRESHOLD)// check if distance is greater than max value move forward
		   {
			   forward(500);
		   }
		   while(Button.ESCAPE.isUp())
		   {
			   distance=instance.computeDistance();// recompute the distance
			 LCD.drawString("Distance="+ (int)distance + "cm", 1,1);// print distance to LCD

			 Delay.msDelay(200);
			 if (distance<THRESHOLD)// if the obstacle is at a distance less than  max value then stop and sense color and displace color
			 {
				  stop();
			      Delay.msDelay(1000);// time gap for the robot to stop and sensor to get ready
			      senseColor(colorSensor);//   sense color  using the function and display color
			      Delay.msDelay(2000);// time to settle down
			      distance=instance.computeDistance();//   recompute distance
			      if (distance>THRESHOLD)//  if there is no obstacle ahead or in range of max distance then move forward
			      {
			    	  forward(500);
			      }
		     }

			 if(Button.ESCAPE.isDown())// if the escape button is pressed exit the loop and stop excution
			 {
				  instance.close();
				  colorSensor.close();
				  break;
			 }
			  LCD.clear();    // clearing the screen to print new distance and color
	}

	}
}
