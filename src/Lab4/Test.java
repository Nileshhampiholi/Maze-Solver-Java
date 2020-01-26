package Lab4;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Test {
	static EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	static EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	static float WheelToWheelDist = 61.5f ;
	static double Diameter = 55;
	
	public static void main(String[] args) {
		//Turner obj = new Task_4_2();
		setSpeed(500);
		turn(90);
	}
	
	public static void setSpeed(int degreesPerSecond) {
		rightMotor.setSpeed(degreesPerSecond);
		leftMotor.setSpeed(degreesPerSecond);
	}
	
	
	public static int turn(float degrees) {
		
		LCD.drawString("AngleToRotate = " + degrees, 1,2);
		
		double s = WheelToWheelDist * degrees;
		
		int alpha = (int) (s/Diameter);
		rightMotor.rotateTo(alpha,true);
		leftMotor.rotateTo(-alpha);
		return alpha;
	}	

}
