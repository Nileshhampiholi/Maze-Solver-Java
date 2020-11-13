package Lab4;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

public  class SimpleTurning  {
	private static EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	private  static EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	private static int angularSpeed= 500, inputAngle=180, radiusOfTurning=61, motorAngle, lenghtOfTurningPath;
	//private final int TURN_DELAY = 200;
	
	//@Override
	public static void setSpeed(int angularSpeed){
		 
	leftMotor.setSpeed(angularSpeed);
	rightMotor.setSpeed(angularSpeed);

		
		
	}
	//@Override
	 public  static void turn(int motorAngle) {
		 leftMotor.rotate(-(motorAngle),true);
		 rightMotor.rotate(motorAngle,false);
	 }
	
	
	public static void main ( String [] args) {
		
		lenghtOfTurningPath = computePathLength( inputAngle, radiusOfTurning);
		motorAngle= computeMotorAngle( lenghtOfTurningPath);
		setSpeed(angularSpeed);
		turn(motorAngle);
	

}


		
		
		public static int computePathLength( int inputAngle, int radiusOfTurning ) {
			return( inputAngle* radiusOfTurning);	
		}
		
		public static  int computeMotorAngle( int lenghtOfTurningPath) {
			return (int) ( lenghtOfTurningPath* 3 / 2.8);
			}
		
		
}
