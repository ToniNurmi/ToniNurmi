package app;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class LineFollower extends Thread {

	private static EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S1);
	private static DataExchange de = new DataExchange();
	private static EV3LargeRegulatedMotor motor1 = new EV3LargeRegulatedMotor(MotorPort.A);
	private static EV3LargeRegulatedMotor motor2 = new EV3LargeRegulatedMotor(MotorPort.D);

	private int color = cs.getColorID();

//	public static void main(String[] args) {
//		while (true) {
//			int color = cs.getColorID();
//			while (color < 0) {
//				System.out.println(color);
//				if (color > 0) {
//					break;
//				}
//			}
//			
//			System.out.println("Pii");
//			if (Button.waitForAnyPress() != 0) {
//				System.exit(0);
//			}
//		}
//	}

	@Override
	public void run() {
		while (true) {
			if (de.getCMD() == 1) {
				if (color <= 1) {
					System.out.println(color);
					motor1.setSpeed(1000);
					motor1.forward();
					motor2.setSpeed(1000);
					motor2.forward();
				} else {
					System.out.println("COLOR");
					motor1.stop();
					motor2.stop();
				}
			} else {
				System.out.println(cs.getColorID());
				motor1.stop();
				motor2.stop();

			}
		}

	}
}
