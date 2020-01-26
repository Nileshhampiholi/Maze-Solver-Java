package Lab3;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
public class Task3_4 {
	public static EV3GyroSensor angleSensor = new EV3GyroSensor(SensorPort.S3);
	public static float angle[];
	static EV3LargeRegulatedMotor left=new EV3LargeRegulatedMotor(MotorPort.B);
	static EV3LargeRegulatedMotor right=new EV3LargeRegulatedMotor(MotorPort.C);
	public static void main(String[] args) {
		double rotations,theta;
		int alpha = 720;
		while(Button.ESCAPE.isUp())
		{
			theta =  computeAngle(angleSensor);
			rotations = theta/360;
			LCD.drawString("Rotations="+ rotations, 2, 2);
			turnLeft(alpha,100);
		    Delay.msDelay(500);
			if(Button.ESCAPE.isDown())
			{
				angleSensor.close();
				break;
			}	
		}	
	}

		private static void turnLeft(int alpha, int x) {

			
			left.setSpeed(x);
			right.setSpeed(x);
			left.rotate(0, true);
			right.rotate(alpha,true);
			Delay.msDelay(200);	
	}
	public static double computeAngle ( EV3GyroSensor angleSensor )
	{
		SampleProvider gyro = angleSensor.getAngleMode();
		angle = new float[gyro.sampleSize()];
		gyro.fetchSample(angle,0);
		LCD.drawString("Angle="+ String.valueOf(angle[0]), 1, 1);
		Delay.msDelay(500);
		return angle[0];	
	}
	
}
