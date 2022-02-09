package app;

import lejos.hardware.Button;

public class Move extends Thread {

	@Override
	public void run() {

		Button.LEDPattern(7);
		Button.waitForAnyPress();
		Button.LEDPattern(0);
		Song song = new Song();
		song.run();

	}

}
