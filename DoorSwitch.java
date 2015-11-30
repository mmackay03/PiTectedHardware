//Author: Margo Mac Kay
//November 23, 2015
/*
*This class is intedned to run threads for each door switch. The Doors class will initiate the threads for each door.
*/


//imports
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import java.sql.ResultSet;
import java.sql.SQLException;

//imiplement Runnable class for threading purposes
public class DoorSwitch implements Runnable{	
    
	int currPin;//current pin
    int doorID;//Door ID for the door table in the databse
	int tempPin;//variable to set up the pin
    static DBConnection db = new DBConnection();//create a new object of database connection class
    
    //create gpio controller
    static GpioController gpio = GpioFactory.getInstance();
    //array for all possible GPIO pins to be used for door switches
    //provision gpio pin as an input pin with its internal pull up
	static GpioPinDigitalInput[] gpPins = {
             gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_UP),
             gpio.provisionDigitalInputPin(RaspiPin.GPIO_27, PinPullResistance.PULL_UP),
             gpio.provisionDigitalInputPin(RaspiPin.GPIO_28, PinPullResistance.PULL_UP),
     };
	
	//get the array 
	public DoorSwitch(int arrayPin){
		//set the array item number as the current pin to be used
		currPin = arrayPin;
		//set the door ID for future use with the database
        doorID = (currPin + 1);
	}
	
	public void updateDB(int status, int door){
		try{
			db.dbConnection();//set up database connection
			//Update the door status in the database
	        String update = "UPDATE `door` SET `status`=" + status + " WHERE `id`="+ doorID +";";
	        db.getStatement().executeUpdate(update);
	        //Insert the door opened and time
		    String log = "INSERT INTO `door_log` (`door_id`, `status`) VALUES ("+ doorID + "," + status +");";
	        db.getStatement().executeUpdate(log);
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
	}
    
	//method to allow threading
    public void run(){
    	try{

//    		System.out.println("<--Pi4J--> GPIO Listen Example ... started. " + "passed value: " + p);//debug
           
            
    		//set the GPIO with the gpPins array
    		for (int i=1; i<gpPins.length; i++){
    			if(currPin == i){
//    				System.out.println(p);//debug
    				tempPin = currPin;
    			}
    		}

    		//Set the digital input for the door
    		final GpioPinDigitalInput door = gpPins[tempPin];
    		System.out.println(doorID);//debug

            
            // keep program running until user aborts (CTRL-C)
//    		System.out.println("READY: " + tempPin);//debug
    		
            door.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                	
                	
//                	try{
//                        db.dbConnection();//set up database connection
	                    if(event.getState() == PinState.HIGH){//If door is open
	                            System.out.println(doorID);//debug
	        					System.out.println("Door Open! " + currPin);//debug
	        					//update the door and door_log table in the database
	        					updateDB(1, doorID);
	                    }
	    				if(event.getState() == PinState.LOW){//If door is closed
	                            System.out.println(doorID);//debug
	        					System.out.println("				Door Closed! " + currPin);//debug
	        					//update the door and door_log table in the database
	        					updateDB(0, doorID);
	                        
	    				}
                    //System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());//debug
                }
                
            });
    	    while(true) {
    	    	Thread.sleep(500);
    	    }
            
            // stop all GPIO activity/threads by shutting down the GPIO controller
            // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
            // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
    	}catch(Exception e){
    		
    	}
    	
    }
    
}