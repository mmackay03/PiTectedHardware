
public class Sensors {
//	private static Pin doorPin = RaspPin.GPIO_02;
	public static void main(String[] args) {

		//initiate door switch threads
		Thread door1 = new Thread(new DoorSwitch(0));
		Thread door2 = new Thread(new DoorSwitch(1));
		Thread door3 = new Thread(new DoorSwitch(2));
		Thread door4 = new Thread(new DoorSwitch(3));
		//initiate motion sensor threads
		Thread motion1 = new Thread(new MotionSensor(0));
		
		//start threads
		door1.start();
		door2.start();
		door3.start();
		door4.start();
		motion1.start();
	}
}
