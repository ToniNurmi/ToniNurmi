package app;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.utility.Delay;

public class Song extends Thread {
	
	@Override
	public void run() {
		while (true) {
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 494, 380);
			Button.LEDPattern(0);
			Delay.msDelay(140);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 494, 200);
			Button.LEDPattern(0);
			Delay.msDelay(600);
			
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 370, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 415, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 494, 320);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 415, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 370, 320);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 330, 380);
			Button.LEDPattern(0);
			Delay.msDelay(140);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 330, 200);
			Button.LEDPattern(0);
			Delay.msDelay(600);
			
			Delay.msDelay(660);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 370, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 415, 260);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 494, 380);
			Button.LEDPattern(0);
			Delay.msDelay(140);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 494, 200);
			Button.LEDPattern(0);
			Delay.msDelay(600);
			
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 370, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 415, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 494, 320);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 415, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 370, 220);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 330, 380);
			Button.LEDPattern(0);
			Delay.msDelay(140);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 330, 200);
			Button.LEDPattern(0);
			Delay.msDelay(320);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 415, 320);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 370, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 370, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 415, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 494, 320);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 415, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 370, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			Button.LEDPattern(1);
			Sound.playNote(Sound.PIANO, 330, 200);
			Button.LEDPattern(0);
			Delay.msDelay(40);
			
			System.exit(0);
		}
		
		
	}

}
