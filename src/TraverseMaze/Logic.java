package TraverseMaze;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Logic {
	private String colorToBeDetected, colorDetected;
	private final int WALL_DISTANCE =12, SIDE_WALL_DISTAANCE =13, DELAY =500, LEFT_ANGLE = 90 , FRONT_ANGLE = 0, BACK_ANGLE = 180;
	private final int TILE_LENGHT = 35, SPEED= 200, COLORED_WALL_DISTANCE =3 , TILE_DISTANCE =20;
	private int distanceFront,distanceLeft, distanceRight, distanceBack,detect ;

	Buzzer buzz = new Buzzer();
	ColorSensor colorSensor = new ColorSensor();
	DistanceSensor distanceSensor = new DistanceSensor();
	Display display = new Display();
	MoveForward moveForward = new MoveForward();
	Turn turn = new Turn();

	public  void traverse() {                                   // traverses maze to find the selected color 
		int right=0,left=0,front=0, instance =0;
		colorToBeDetected = display.selectColorToDetect();      // opens a display to select color 
		LCD.clearDisplay();
		LCD.drawString(colorToBeDetected, 1, 1);
		buzz.playTone();     // plays a tone 
		align();        // aligns to center of a tile 
		
		while (true) {                             
			left = turnLeft();    // turns left if no obstacle goes forward or returns 1 
			if (left==1) {
				front= turnFront();   // turns front  if no obstacle goes forward or returns 1 
			}
			if (front ==1) {
				right = turnRight(); // turns right  if no obstacle goes forward or returns 1 
				front = 0;
			}
			if (right==1 ) {
				instance = detectColor();    // if there is obstace at right also its a 3 sided corner so detect color 
				if(instance == 1) {
					moveForward.stop();
					break;
				}
				else if (detect ==0) {   // if no color is detected turn back
					turnBack();	     // turns back and if no obstacle goes forward otherwise returns 1
					right= 0 ;
				}
			}
		}
	}

	public void align () {   // finds a wall behind it and aligns itself to center of the tile 
		turn.turnForGivenAngle(BACK_ANGLE);
		Delay.msDelay(DELAY);
		distanceBack = distanceSensor.computeDistance();
		Delay.msDelay(DELAY);
		if (distanceBack> WALL_DISTANCE) {
			moveToObstacle(WALL_DISTANCE);
		}
		else if (distanceBack< WALL_DISTANCE) {   // aligns to left wall if its not more than 35 cm 
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
		turn.turnForGivenAngle(-LEFT_ANGLE);   // aligns to right wall if its not more than 35 cm 
		Delay.msDelay(DELAY);
		distanceRight = distanceSensor.computeDistance();
		Delay.msDelay(DELAY);
		if (distanceRight> SIDE_WALL_DISTAANCE &&  distanceRight <TILE_LENGHT) {
			moveToObstacle(SIDE_WALL_DISTAANCE);
		}
		else if (distanceRight< SIDE_WALL_DISTAANCE){
			moveAway(SIDE_WALL_DISTAANCE);
		}
		if (distanceLeft <18 && distanceRight <18) {    // left and right there is obstacle then it calls method which detect color
			turn.turnForGivenAngle(BACK_ANGLE);
			detectColor();
			turn.turnForGivenAngle(FRONT_ANGLE);
		}
		turn.turnForGivenAngle(FRONT_ANGLE);
	}

	public int detectColor() {
		detect = detect(WALL_DISTANCE);     // method detect color in  a 3 sided corner  by calling method detect
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

	public int turnLeft() {   // turns left if no obstacle goes forward or returns 1 
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

	public int turnRight() {    // turns right  if no obstacle goes forward or returns 1 
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

	public  int turnFront() {   // turns front  if no obstacle goes forward or returns 1 
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

	public int turnBack() {   // turns back and if no obstacle goes forward otherwise returns 1
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

	public int detect (int distanceToStop) {   // moves forward until distance is 3 cm from obstacle detects color and moves back by given distance 
		moveToObstacle(COLORED_WALL_DISTANCE);
		Delay.msDelay(DELAY);
		//moveForward.forward(1);
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

	public void moveToObstacle(int distanceToStop) {   // move to the obstacle and stop at a threshould value 
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

	public  void moveAway(int distanceToStop) {  // move away from obstacle for a specified distance
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


