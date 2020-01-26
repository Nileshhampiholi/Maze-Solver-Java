package Lab2;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
public class task_3 {
	public static void main(String[] args) {
		alpha();
	}
	
	public static void simplerun() {
		Motor.B.forward();
		Motor.C.forward();
		Delay.msDelay(5000);
		Motor.B.backward();
		Motor.C.backward();
		Delay.msDelay(5000);
		
		Motor.B.stop();
		Motor.C.stop();
}
	
		public static void alpha() {
			int distance =0;
			int radius=3;
			int alpha;
			int x = 0,y=0;
	while(true) {
		LCD.drawString("Enter Distance",2,3);
		Button.waitForAnyPress();
			LCD.drawString("Enter Distance",2,3);
			LCD.drawInt(distance, 3, 4);
			if(Button.UP.isDown())
			{
				distance += 5;
				LCD.drawInt(distance, 3, 4);
				}
			
			else if(Button.DOWN.isDown())
			{
			distance -= 5;
			LCD.clear();
			LCD.drawInt(distance, 3, 4);
			}
			if(Button.ENTER.isDown())
			{
			alpha = (distance / radius )*360;
			LCD.drawInt(distance, 3, 4);
			EV3LargeRegulatedMotor Left	 = new EV3LargeRegulatedMotor(MotorPort.B);
			EV3LargeRegulatedMotor Right	 = new EV3LargeRegulatedMotor(MotorPort.C);
		
			Left.setSpeed(1000);
			Right.setSpeed(1000);
			Delay.msDelay(500);
			Left.rotate(alpha,true);
			Right.rotate(alpha,true);
			if(Left.isMoving() && Right.isMoving())
			{
			for(int i=0;i<5;i++)
			 {
				LCD.clear();
				LCD.drawString("Moving", x, y);
			 Delay.msDelay(2000);
			 x=x+1;
			 y=y+1;
			 LCD.clear();
			}
			
			if (Button.ESCAPE.isDown())
			{  
				Left.close();
			    Right.close();
				
			}
			
		}
		}
		}
}
}