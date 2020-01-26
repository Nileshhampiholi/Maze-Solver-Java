package TraverseMaze;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class Display {
	
	private final String COLOR_ARRAY[] = {"NONE", "BLACK","BLUE", "GREEN","YELLOW","RED", "WHITE","BROWN"};
	private int cursorPosition=1;
	
	public String selectColorToDetect() {

		LCD.drawString("Menu",1,3);
		while(true) {
			display(cursorPosition);
			Button.waitForAnyPress();
			if( Button.UP.isDown()){
				if (cursorPosition==1) {
					cursorPosition=7;
				}
				else {
					cursorPosition=cursorPosition-1;}
			}

			else if (Button.DOWN.isDown()){
				if(cursorPosition==7){
					cursorPosition=1;
				}
				else {
					cursorPosition= cursorPosition+1;}
			}
			else if (Button.LEFT.isDown())
			{
				if (cursorPosition==1) {
					cursorPosition=7;
				}
				else {
					cursorPosition=cursorPosition-1;}
			}

			else if (Button.RIGHT.isDown())	{
				if(cursorPosition==7){
					cursorPosition=1;
				}
				else {
					cursorPosition= cursorPosition+1;
				}
			}
			else if (Button.ENTER.isDown())
			{
				return(COLOR_ARRAY[cursorPosition]);
			}
			else if (Button.ESCAPE.isDown()) 
			{ 
				System.exit(0);
			}
		}
	}

	public void display ( int i)
	{
		LCD.clearDisplay();
		LCD.drawString(" Menu (Select  color to be Detected) ",1,1);
		if(i==1)
		{
			LCD.drawString(COLOR_ARRAY[7],2,2);
		}
		else 
		{
			LCD.drawString(COLOR_ARRAY[i-1],2,2);
		}

		LCD.drawString(">" +COLOR_ARRAY[i], 3, 3);


		if(i==7)
		{
			LCD.drawString(COLOR_ARRAY[1],2,4);
		}
		else {
			LCD.drawString(COLOR_ARRAY[i+1], 2, 4);}

	}	
}

