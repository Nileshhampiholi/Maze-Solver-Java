package Lab4;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class TurningWithGyroscope implements Turner {

	private EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	private EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	private EV3GyroSensor gyroscopeSensor = new EV3GyroSensor(SensorPort.S3);
	private int  motorAngle, lenghtOfTurningPath,error, gyroscopeAngle;
	private int angularSpeed=500;
	private int inputAngle=90;
	private int radiusOfTurning=61;
	float sample [];
	
	@Override
	public void setSpeed(int angularSpeed){
		 
	leftMotor.setSpeed(angularSpeed);
	rightMotor.setSpeed(angularSpeed);

		
		
	}
	@Override
	 public  void turn(int motorAngle) {
		 leftMotor.rotate(-(motorAngle),true);
		 rightMotor.rotate(motorAngle,true);
	 }
	
	public  void main(String[] args) {
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

	private  int computeMotorAngle() {
		
		return (int) ( lenghtOfTurningPath* 3 / 2.8);
	}

	private  int computePathLength() {
		
		return ( error* radiusOfTurning);

	}
	public  int computeGyroscopeAngle (  )
	{
		SampleProvider gyro = gyroscopeSensor.getAngleMode();
		sample = new float[gyro.sampleSize()];
		gyro.fetchSample(sample,0);
		LCD.drawString("Angle="+ String.valueOf(sample[0]), 1, 1);
		Delay.msDelay(500);
		return (int) sample[0];	
	}
   public int computeError() {
	   return( inputAngle - gyroscopeAngle);
   }
   
}
