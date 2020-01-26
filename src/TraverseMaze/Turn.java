package TraverseMaze;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Turn implements Movement {

	public static EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	public static EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	private int  motorAngle, lenghtOfTurningPath,error, gyroscopeAngle, kp;
	private final int SPEED=50, RADIUS_OF_TURNING =61, GEAR_RATIO = 3, WHEEL_RADIUS= 28, MAX_ERROR =30 , MIN_ERROR = 10 ; 
    GyroscopeSensor gyroscopeSensor = new  GyroscopeSensor();
	
	public void turnForGivenAngle(int inputAngle) {
		while(Button.ESCAPE.isUp()) {
			gyroscopeAngle = gyroscopeSensor.computeGyroscopeAngle();
			error = computeError(inputAngle);
			LCD.drawString("x= " +error, 1, 4);
			lenghtOfTurningPath = computePathLength( );
			LCD.drawString("y= " +lenghtOfTurningPath, 1, 2);
			motorAngle= computeMotorAngle();
			LCD.drawString("z= " +motorAngle, 1, 3);
			kp= computeKp(error);
			LCD.drawString("kp= " +kp, 1, 5);
			setSpeed(SPEED*kp);
			motorRotate(motorAngle);
			Delay.msDelay(500);
			if(gyroscopeAngle== inputAngle)
			{   
				LCD.clearDisplay();
				LCD.drawString("stop", 5, 5);
				break;
			}
		}	
	}

	public void setSpeed(int speed){
		leftMotor.setSpeed(speed);
		rightMotor.setSpeed(speed);
	}
	
	public void motorRotate(int motorAngle) {
		leftMotor.rotate((motorAngle),true);
		rightMotor.rotate(-(motorAngle),true);
	}

	public int computeMotorAngle() {
		return (int) ( lenghtOfTurningPath* GEAR_RATIO / WHEEL_RADIUS);
	}

	public int computePathLength() {
		return ( error* RADIUS_OF_TURNING );
	}
	
	public int computeError(int inputAngle) {
		return( inputAngle - gyroscopeAngle);
	}

	public int computeKp(int error) {
     if (error>0) {
    	 if ( error >MAX_ERROR)
 		{
 			kp=10;
 		}
 		else if (error <MAX_ERROR && error >MIN_ERROR)
 		{ 
 			kp= 5;
 		}
 		else if (error < MIN_ERROR)
 		{
 			kp= 1;
 		}
     }
     else if (error<0) {
    	 if ( error <-MAX_ERROR)
 		{
 			kp=10;
 		}
 		else if (error >-MAX_ERROR && error <-MIN_ERROR)
 		{ 
 			kp= 5;
 		}
 		else if (error > -MIN_ERROR)
 		{
 			kp= 1;
 		}
     }
		return(kp);
	}
}
