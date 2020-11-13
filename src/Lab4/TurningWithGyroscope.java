package Lab4;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class TurningWithGyroscope  {

	private static EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	private static EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	private static EV3GyroSensor gyroscopeSensor = new EV3GyroSensor(SensorPort.S3);
	private  static int  motorAngle, lenghtOfTurningPath,error, gyroscopeAngle;
	private static int angularSpeed=50;
	private static int inputAngle= 90;
	private static int radiusOfTurning=61;
static float sample [];
	

	public static void setSpeed(int angularSpeed){
		 
	leftMotor.setSpeed(angularSpeed);
	rightMotor.setSpeed(angularSpeed);

		
		
	}
	
	 public static void turn(int motorAngle) {
		 leftMotor.rotate(-(motorAngle),true);
		 rightMotor.rotate(motorAngle,true);
	 }
	
	public static void main(String[] args) {
		while(Button.ESCAPE.isUp()) {
			gyroscopeAngle = computeGyroscopeAngle( );
			error = computeError();
			lenghtOfTurningPath = computePathLength( );
			motorAngle= computeMotorAngle();
			setSpeed(angularSpeed);
			turn(motorAngle);	
			if(gyroscopeAngle== inputAngle)
			{    
				leftMotor.stop();
				rightMotor.stop();
			}
		}
	}

	private static  int computeMotorAngle() {
		
		return (int) ( lenghtOfTurningPath* 3 / 2.8);
	}

	private static int computePathLength() {
		
		return ( error* radiusOfTurning);

	}
	public static  int computeGyroscopeAngle (  )
	{
		SampleProvider gyro = gyroscopeSensor.getAngleMode();
		sample = new float[gyro.sampleSize()];
		gyro.fetchSample(sample,0);
		LCD.drawString("Angle="+ String.valueOf(sample[0]), 1, 1);
		Delay.msDelay(500);
		return (int) sample[0];	
	}
   public  static int computeError() {
	   return( inputAngle - gyroscopeAngle);
   }
   
}
