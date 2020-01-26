package TraverseMaze;

import lejos.hardware.Sound;

public class Buzzer {
	
	public void playTone() {
		
		int volume = 100;
		int freq =400;
		int duration= 500;
		
		for(int i=0; i<3; i++) {
			Sound.setVolume(volume);
			Sound.playTone(freq, duration);
			freq = freq+ 100;
			duration = duration-100;
		}	
	}
}
