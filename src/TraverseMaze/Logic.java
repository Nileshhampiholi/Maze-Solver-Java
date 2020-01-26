package TraverseMaze;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Logic {
	private String colorToBeDetected, colorDetected;
	private final int WALL_DISTANCE =12, SIDE_WALL_DISTAANCE =13 , TILE_DISTANCE =20, DELAY =500, LEFT_ANGLE = 90 , FRONT_ANGLE = 0, BACK_ANGLE = 180;
	private final int TILE_LENGHT = 35, SPEED= 200, COLORED_WALL_DISTANCE =3;
	private int distanceFront,distanceLeft, distanceRight, distanceBack,right,left,front, instance =0,detect ;

	Buzzer buzz = new Buzzer();
	ColorSensor colorSensor = new ColorSensor();
	DistanceSensor distanceSensor = new DistanceSensor();
	Display display = new Display();
	MoveForward moveForward = new MoveForward();
	Turn turn = new Turn();

	public  void traverse() {
		colorToBeDetected = display.selectColorToDetect();
		LCD.clearDisplay();
		LCD.drawString(colorToBeDetected, 1, 1);
		buzz.playTone();
		align();
		while (true) {
			left = turnLeft();
			if (left==1) {
				front= turnFront();
			}
			if (front ==1) {
				right = turnRight();
				front = 0;
			}
			if (right==1 ) {
				instance = detectColor();
				if(instance == 1) {
					moveForward.stop();
					break;
				}
				else if (detect ==0) {
					turnBack();	
					right= 0 ;
				}
			}
		}
	}

	public void align () {
		turn.turnForGivenAngle(BACK_ANGLE);
		Delay.msDelay(DELAY);
		distanceBack = distanceSensor.computeDistance();
		Delay.msDelay(DELAY);
		if (distanceBack> WALL_DISTANCE) {
			moveToObstacle(WALL_DISTANCE);
		}
		else if (distanceBack< WALL_DISTANCE) {
			moveAway(WALL_DISTANCE);
		}
		turn.turnForGivenAngle(LEFT_ANGLE);
		Delay.msDelay(DELAY);
		distanceLeft = distanceSensor.computeDistance();
		Delay.msDelay(DELAY);
		if (distanceLeft> SIDE_WALL_DISTAANCE &&  distanceLeft <TILE_LENGHT) {
			moveToObstacle(SIDE_WALL_DISTAANCE);  
		}
		else if(distanceLeft< SIDE_WALL_DISTAANCE) {
			moveAway(SIDE_WALL_DISTAANCE);
		}
		turn.turnForGivenAngle(-LEFT_ANGLE);
		Delay.msDelay(DELAY);
		distanceRight = distanceSensor.computeDistance();
		Delay.msDelay(DELAY);
		if (distanceRight> SIDE_WALL_DISTAANCE &&  distanceRight <TILE_LENGHT) {
			moveToObstacle(SIDE_WALL_DISTAANCE);
		}
		else if (distanceRight< SIDE_WALL_DISTAANCE){
			moveAway(SIDE_WALL_DISTAANCE);
		}
		if (distanceLeft <18 && distanceRight <18) {
			turn.turnForGivenAngle(BACK_ANGLE);
			detectColor();
			turn.turnForGivenAngle(FRONT_ANGLE);
		}
		turn.turnForGivenAngle(FRONT_ANGLE);
	}

	public int detectColor() {
		detect = detect(WALL_DISTANCE);
		if (detect ==1) {
			return 1;
		}
		turn.turnForGivenAngle(LEFT_ANGLE);
		detect = detect(SIDE_WALL_DISTAANCE);
		if (detect ==1) {
			return 1;
		}
		turn.turnForGivenAngle(-LEFT_ANGLE);
		detect= detect(SIDE_WALL_DISTAANCE);
		if (detect ==1) {
			return 1;
		}
		turn.turnForGivenAngle(FRONT_ANGLE);
		return 0;
	}

	public int turnLeft() {
		turn.turnForGivenAngle(LEFT_ANGLE);
		Delay.msDelay(DELAY);
		distanceLeft= distanceSensor.computeDistance();
		if(distanceLeft >TILE_LENGHT) {
			moveForward.forward(TILE_DISTANCE );
			Delay.msDelay(DELAY);
			GyroscopeSensor.gyroscopeSensor.reset();
			return 0;
		}
		else {
			if(distanceSensor.computeDistance()< SIDE_WALL_DISTAANCE) {
				moveAway(SIDE_WALL_DISTAANCE);
			}
			turn.turnForGivenAngle(FRONT_ANGLE);
			Delay.msDelay(DELAY);
			return 1;
		}
	}

	public int turnRight() {
		turn.turnForGivenAngle(-LEFT_ANGLE);
		Delay.msDelay(DELAY);
		distanceRight= distanceSensor.computeDistance();
		if(distanceRight >TILE_LENGHT) {
			moveForward.forward(TILE_DISTANCE );
			Delay.msDelay(DELAY);
			GyroscopeSensor.gyroscopeSensor.reset();
			return 0;
		}
		else {
			turn.turnForGivenAngle(FRONT_ANGLE);
			Delay.msDelay(DELAY);
			return 1;
		}
	}	 	

	public  int turnFront() {
		distanceFront= distanceSensor.computeDistance();
		Delay.msDelay(DELAY);
		if(distanceFront >TILE_LENGHT) {
			LCD.clearDisplay();
			LCD.drawString("d " + distanceFront, 1, 1);
			moveForward.forward(TILE_DISTANCE );
			Delay.msDelay(DELAY);
			distanceFront= distanceSensor.computeDistance();
			if (distanceFront<WALL_DISTANCE) {
				moveAway(WALL_DISTANCE);
			}
			return 0;
		}
		else {
			Delay.msDelay(DELAY);
			return 1;
		}
	}

	public int turnBack() {
		turn.turnForGivenAngle(BACK_ANGLE);
		Delay.msDelay(DELAY);
		distanceBack= distanceSensor.computeDistance();
		if(distanceBack >TILE_LENGHT) {
			moveForward.forward(TILE_DISTANCE );
			Delay.msDelay(DELAY);
			GyroscopeSensor.gyroscopeSensor.reset();
			return 0;
		}
		else {
			Delay.msDelay(DELAY);
			return 1 ;
		}
	}

	public int detect (int distanceToStop) {
		moveToObstacle(COLORED_WALL_DISTANCE);
		Delay.msDelay(DELAY);
		colorDetected = colorSensor.senseColor();
		if (colorToBeDetected == colorDetected ) {
			moveForward.stop();
			buzz.playTone();
			LCD.clearDisplay();
			LCD.drawString("E U R E K A", 3, 3);
			Delay.msDelay(DELAY*10);
			return(1);
		}
		moveAway(distanceToStop);
		Delay.msDelay(DELAY);
		return 0;
	}

	public void moveToObstacle(int distanceToStop) {
		int distance = distanceSensor.computeDistance();
		while (distance>distanceToStop) {
			moveForward.setSpeed(SPEED);
			moveForward.forward();
			distance = distanceSensor.computeDistance();
			if (distance<= distanceToStop) {
				moveForward.stop();
				Delay.msDelay(DELAY);
				break;
			}
		}
	}

	public  void moveAway(int distanceToStop) {
		while(distanceSensor.computeDistance()< distanceToStop) {
			moveForward.setSpeed(SPEED);
			moveForward.backward();
			if (distanceSensor.computeDistance()>= distanceToStop) {
				moveForward.stop();
				Delay.msDelay(DELAY);
				break;
			}
		}
	}
}
