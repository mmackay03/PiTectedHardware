//Author: Margo Mac Kay
//November 2015
/*
 * PiTected Project
 * Implements KeyboardController class
 * This class is meant to except user input on the keypad and compare the code entered with the user codes within the pitected
 * database. * will be used to disarm the system and # will be used to arm the system.
 */

public class Keypad {

	//initiate variables
	static String enteredCode = "";
	static char armDisarm = '#';
	public static StatusIndicator si = new StatusIndicator();
	public static int status;
	static AlarmSystem sys = new AlarmSystem();//create a new object of the AlarmSystem class
	
	//main
	public static void main(String[] args) {

		//create new keyboard object
		KeyboardController kbc = new KeyboardController();
		
		//continuous while loop. Ensures the keypad will continue to except characters being pressed even after code is entered
		//or an incorrect code is entered
		while(true){
			//variable to control the while loop
		    boolean go = true;
		    while (go){
		      char inputChar = kbc.getKey();
		      if (armDisarm == inputChar){//if # button is entered end the loop and arm the system is authorized user
		    	  go = false;//end loop
		    	  System.out.println("STATUS: " + status);
		      }else{//continuously add each character pressed (Except * and # characters)
		    	  enteredCode += inputChar;
		      }
		      // System.out.println("Char: " + inputChar);//debugging: show key pressed
		      
		      try { Thread.sleep(200L); } catch (Exception ex) {}
		    }
	//	    System.out.println("Entered: " + enteredCode);
		    status = si.changeStatus();
		    System.out.println("STATUS AFTER: " + status);
		    sys.getCheckCode("keyCode", enteredCode);
		    enteredCode ="";//reset the entered code for future entries
		    kbc.shutdown();//gpio shutdown
		}
	    
	}


}
