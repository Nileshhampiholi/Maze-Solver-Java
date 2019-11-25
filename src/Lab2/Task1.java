package Lab2;


import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class Task1 {

	public static void main(String[] args) {
		print();
	}



public static void  print()  {
	
	int x =1;
	int y =1;
	String s = "Hello World";
	int i = 0;
	for (i=0; i<100; i++)
	{
		LCD.drawString(s, x, y);
		Delay.msDelay(1000);
		LCD.clear();
		x= x+1;
		if (x > 5)
			{ 
			 for (int j = 0; j<6; j++)
				 {x= x-1;}
			}
	
	}
	}
}