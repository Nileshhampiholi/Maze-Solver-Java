package Lab2;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
//import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Task3 {
	static EV3LargeRegulatedMotor Left;
	static EV3LargeRegulatedMotor Right;
	public static void main(String [] args)
	{
		int alpha=0, distance= 100; 
	    double radius= 2.8;
		alpha = ComputeAngle(distance, radius);
		forward(alpha);	
			
		
	}
	
	
	public static int ComputeAngle( int distance, double radius)
	{
		int alpha = (int) ((distance/radius)*360);
		alpha = 3*(alpha);
		LCD.drawString("alpha = "+ alpha, 3,3);
		return alpha;
		
	}

	public static void forward(int alpha)
	{
		Left = new EV3LargeRegulatedMotor(MotorPort.B);

		Right = new EV3LargeRegulatedMotor(MotorPort.C);
		
		Left.setSpeed(1000);
		Right.setSpeed(1000);
		
		//Left.forward();
		//Right.forward();
		// Delay.msDelay(5000);
		
		Left.rotate(alpha, true);
		Right.rotate(alpha,true);
		Delay.msDelay(500);
		while ( Left.isMoving() && Right.isMoving())
		{
			LCD.drawString("Is Moving", 3,4);
			Delay.msDelay(500);
		}
		//Left.stop();
		//Right.stop();
	}
	
	
}