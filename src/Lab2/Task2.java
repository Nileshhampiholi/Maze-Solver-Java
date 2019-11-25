package Lab2;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;

public class Task2 {

	public static void main(String[] args) {
		
		sound();
		cursor();
		//display();

	}
		
		public static void sound()
		{
		int volume = 100;
		int freq =400;
		int duration= 500;
		
		Sound.setVolume(volume);
		Sound.playTone(freq, duration);
	}
	
	
	
public static void display()
{
	int volume=0;
	int frequency=0;
	int duration=0;
	
	String m = "Menu";
	LCD.drawString(m,5,0);
	String v = "Volume";
	LCD.drawString(v,2,1);
	LCD.drawInt(volume,15,1);
	String f = "Frequency" ;
	LCD.drawString(f,2,2);
	LCD.drawInt(frequency,15,2);
	String d = "Duration";
	LCD.drawString(d,2,3);
	LCD.drawInt(duration,15,3);
	String p = "play";
	LCD.drawString(p,2,4);
}


		public static void cursor() {
			int volume=10;
			int frequency=10;
			int duration=10;
	int cursor=0;
	while(true) {
		Button.waitForAnyPress();
		if(Button.DOWN.isDown()) {
			cursor++;
			LCD.clear();
			display();
			LCD.drawInt(volume,15,1);
			LCD.drawInt(frequency,15,2);
			LCD.drawInt(duration,15,3);
			LCD.drawString(">",1,cursor);
			if (cursor>4) {
				cursor=0;
			}
		}
		if(Button.UP.isDown()) {
			cursor--;
			LCD.clear();
			display();
			LCD.drawInt(volume,15,1);
			LCD.drawInt(frequency,15,2);
			LCD.drawInt(duration,15,3);
			LCD.drawString(">",1,cursor);
			if(cursor<0) {
				cursor =4;
			}
		}
		if(Button.RIGHT.isDown()) {
			if(cursor==1) {
				volume = volume + 10;
				LCD.drawInt(volume,15,1);
				}
			else if(cursor==2) {
				frequency += 10;
				LCD.drawInt(frequency,15,2);
				
			}
			else if (cursor==3){
				duration += 10;
				LCD.drawInt(duration,15,3);
				
			}
		}
		if(Button.LEFT.isDown()) {
			if(cursor==1 && volume >0) {
				volume = volume - 10;
				LCD.clear();
				display();
				LCD.drawInt(volume,15,1);
				}
			else if(cursor==2 && frequency >0) {
				frequency -= 10;
				LCD.clear();
				display();
				LCD.drawInt(frequency,15,2);
			}
			else if(cursor==3 && duration>0) {
				duration -= 10;
				LCD.clear();
				display();
				LCD.drawInt(duration,15,3);
				
			}
			}
		if(cursor==4) {
			if(Button.ENTER.isDown()) {
				Sound.setVolume(volume);
				Sound.playTone(frequency, duration);
			}
		}
		
		if(Button.ESCAPE.isDown()) {
			break;
		}
		}
		}	
	
	}
		
