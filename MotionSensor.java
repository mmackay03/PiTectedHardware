import java.sql.SQLException;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.Lcd;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.*;
public class MotionSensor implements Runnable{	
    
	int m;
    int motionID;
	int tempPin;
    static DBConnection db = new DBConnection();//create a new object of database connection class
    
    final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalInput[] motionPins = {gpio.provisionDigitalInputPin(RaspiPin.GPIO_25, PinPullResistance.PULL_DOWN)};
    
	public MotionSensor(int currPin){
		m = currPin;
        motionID = (m + 1);
	}
	
	public void updateDB(int status, int motion){
		try{
    		db.dbConnection();
    		String update = "UPDATE `motion` SET `status`=" + status + " WHERE `id`="+ motion +";";
            db.getStatement().executeUpdate(update);
		    String log = "INSERT INTO `motion_log` (`motion_id`, `status`) VALUES ("+ motion + "," + status + ");";
            db.getStatement().executeUpdate(log);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
	}
	public void run(){
		try{
        
        for (int i=1; i<motionPins.length; i++){
			if(m == i){
				System.out.println(m);
				tempPin = m;
			}
		}

		final GpioPinDigitalInput motion = motionPins[tempPin];
        
        motion.addListener(new GpioPinListenerDigital()
        {  
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
            {
//                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                if (event.getState() == PinState.HIGH)
                {
                	System.out.println("Motion has been detected!");
                	updateDB(1, motionID);
                }
                if(event.getState() == PinState.LOW){
                	
                    System.out.println("                   No motion");
                    updateDB(0, motionID);
                }
            }
        });
        while (true) {
            Thread.sleep(500);
        }
	}catch(Exception e){
		
	}
    }
}
