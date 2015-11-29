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
    
	int p;
    int doorID;
	int tempPin;
    static DBConnection db = new DBConnection();//create a new object of database connection class
	public DoorSwitch(int currPin){
		p = currPin;
        doorID = (p + 1);
	}
    
    public void run(){
    	try{

//    		System.out.println("<--Pi4J--> GPIO Listen Example ... started. " + "passed value: " + p);
            
    		// create gpio controller
    		final GpioController gpio = GpioFactory.getInstance();
    		final GpioPinDigitalInput[] gpPins = {
    	             gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_UP),
    	             gpio.provisionDigitalInputPin(RaspiPin.GPIO_27, PinPullResistance.PULL_UP),
    	             gpio.provisionDigitalInputPin(RaspiPin.GPIO_28, PinPullResistance.PULL_UP),
    	     };
            
    		
    		for (int i=1; i<gpPins.length; i++){
    			if(p == i){
//    				System.out.println(p);
    				tempPin = p;
    			}
    		}

    		final GpioPinDigitalInput door = gpPins[tempPin];
            

    	    // provision gpio pin as an input pin with its internal pull down resistor enabled
//            System.out.println(" ... complete the GPIO #02 circuit and see the listener feedback here in the console.");
            
            // keep program running until user aborts (CTRL-C)
//    		System.out.println("READY: " + tempPin);
    		
            door.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                    // display pin state on console
                	

                    if(event.getState() == PinState.HIGH){
                        try{
                            db.dbConnection();
                            System.out.println(doorID);
        					System.out.println("Door Open! " + p);
                            String update = "UPDATE `door` SET `status`=" + 1 + " WHERE `id`="+ doorID +";";
                            db.getStatement().executeUpdate(update);
        				    String log = "INSERT INTO `door_log` (`door_id`, `status`) VALUES ("+ doorID + ",1);";
                            db.getStatement().executeUpdate(log);
                        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        }
                    }
    				if(event.getState() == PinState.LOW){
                        try{
                            db.dbConnection();
                            System.out.println(doorID);
        					System.out.println("				Door Closed! " + p);
                            String update = "UPDATE `door` SET `status`=" + 0 + " WHERE `id`="+ doorID +";";
                            db.getStatement().executeUpdate(update);
                            String log = "INSERT INTO `door_log` (`door_id`, `status`) VALUES ("+ doorID + ",0);";
                            db.getStatement().executeUpdate(log);
                        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        }
    				}
                    //System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
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