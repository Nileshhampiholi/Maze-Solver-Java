package TraverseMaze;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class MoveForward implements Movement {
	
	private int motorAngle;
	private final int SPEED =500;
	private final double WHEEL_RADIUS = 2.8;

	public void forward(double distanceToCover) {   // moves forward for specified distance in centimeters
		motorAngle =((int) ( distanceToCover / WHEEL_RADIUS)*360);
		LCD.drawString("angle= "+motorAngle, 3, 1);
		setSpeed(SPEED);
		motorRotate(motorAngle);
		while(Turn.leftMotor.isMoving() &&  Turn.rightMotor.isMoving()) {
			LCD.drawString(" is moving", 3, 4);
			break;
		}
	}
	
	public void forward() { // just moves forward 
		Turn.leftMotor.synchronizeWith( new EV3LargeRegulatedMotor[] {Turn.rightMotor});
		Turn.leftMotor.startSynchronization();
		Turn.leftMotor.backward();
		Turn.rightMotor.backward();
		Turn.leftMotor.endSynchronization();
	}
	
	public void backward() {  // moves backward until asked otherwise
		Turn.leftMotor.synchronizeWith( new EV3LargeRegulatedMotor[] {Turn.rightMotor});
		Turn.leftMotor.startSynchronization();
		Turn.leftMotor.forward();
		Turn.rightMotor.forward();	
		Turn.leftMotor.endSynchronization();
	}

	public void setSpeed( int speed) {
		Turn.leftMotor.setSpeed(speed);
		Turn.rightMotor.setSpeed(speed);
	}
	
	public void motorRotate(int motorAngle) {
		Turn.leftMotor.rotate(-(motorAngle),true);
		Turn.rightMotor.rotate(-(motorAngle),false);
	}
	
	public void stop() {
		Turn.leftMotor.synchronizeWith( new EV3LargeRegulatedMotor[] {Turn.rightMotor});
		Turn.leftMotor.startSynchronization();
		Turn.leftMotor.stop();
		Turn.rightMotor.stop();
		Turn.leftMotor.endSynchronization();
	}
}

