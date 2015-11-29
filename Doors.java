
public class Doors {

	
//	private static Pin doorPin = RaspPin.GPIO_02;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		
		Thread door1 = new Thread(new DoorSwitch(0));
		door1.start();
		Thread door2 = new Thread(new DoorSwitch(1));
		door2.start();
		Thread door3 = new Thread(new DoorSwitch(2));
		door3.start();
	
	}

}
