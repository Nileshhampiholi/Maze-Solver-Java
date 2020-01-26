package Lab2;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;


public class Task2 
{
	public static void main( String[] args)
	{
		int parameters[]= { 0 , 0 , 0 , 0},i=0, id=0;
		String s[] = {"Volume",  "Frequency","Duration","Play", ">","<","^","v"};
		LCD.drawString("Menu",1,3);
		do
			{
			    display (s,parameters,i);
				
				Button.waitForAnyPress();
				
				if( Button.UP.isDown())
				{
					if (i==0)
					{i=3;}
					else {
					i=i-1;}
				}
				
				else if (Button.DOWN.isDown())
				{
					if(i==3)
					{i=0;}
					else {
					i= i+1;}
				}
				else if (Button.LEFT.isDown())
				{
					parameters[i]= decrease(parameters[i]);
					
				}
				else if (Button.RIGHT.isDown())
				{
					parameters[i]= increase (parameters[i]);
				}
				else if (Button.ENTER.isDown() && i == 3)
				{
					Sound.playTone(parameters[1], parameters[2], parameters[0]);
				}
				else if (id == Button.ID_ESCAPE) 
				{ 
					System.exit(0);
				}
				
				
			} while(true);
		
	
	}
		
	public static int increase(int x)
	{
		if (x==100) { 
			x= 100;
		}
		else {
		x = x+5;
		}
		return x;
	}
	
	public static int decrease(int x)
	{
		if (x==0)
		{ 
			x= 0;
		}
		else {
			x= x-5;
		}
		return x;
	}
	
	public static void display (String s[], int parameters[], int i)
	{
		LCD.clearDisplay();
		if(i==0)
		{
			LCD.drawString(s[3],2,2);
		}
		else 
		{
			LCD.drawString(s[i-1],2,2);
		}
	    
		LCD.drawString(s[4]+s[i]+"  " +  parameters[i], 3, 3);
		if(i==3)
		{
			LCD.drawString(s[0],2,4);
		}
		else {
		LCD.drawString(s[i+1], 2, 4);}
		  
	}	
	
}

