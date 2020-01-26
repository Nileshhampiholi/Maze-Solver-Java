package TraverseMaze;

import lejos.hardware.Sound;

public class Buzzer {

	private int volume = 100;
	private int freq =400;
	private int duration= 500;

	public void playTone() {
		for(int i=0; i<3; i++) {
			Sound.setVolume(volume);
			Sound.playTone(freq, duration);

			freq = freq+ 100;
			duration = duration-100;
		}	
	}
}
