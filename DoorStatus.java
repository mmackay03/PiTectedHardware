/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.pi4j.component.lcd.LCDTextAlignment;
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
/**
 *
 * @author Bryce Renninger
 * 12/6/2015
 * 
 * This class was created to check the values of the doors on the boot, and to update the database of with that value.
 * Just in case the value changed when the system was shut down.
 */
public class DoorStatus 
{
    static String currPin;//current pin
    static int doorID;//Door ID for the door table in the databse
    static int status;//variable to set status
    static DBConnection db = new DBConnection();//create a new object of database connection class
    
    //create gpio controller
    static GpioController gpio = GpioFactory.getInstance();
    //array for all possible GPIO pins to be used for door switches
    //provision gpio pin as an input pin with its internal pull up
    static GpioPinDigitalInput[] gpPins = {
             gpio.provisionDigitalInputPin(RaspiPin.GPIO_27, PinPullResistance.PULL_UP),
             gpio.provisionDigitalInputPin(RaspiPin.GPIO_28, PinPullResistance.PULL_UP),
             //gpio.provisionDigitalInputPin(RaspiPin.GPIO_28, PinPullResistance.PULL_UP),
     };
    
    public static void main(String args[]) throws SQLException, ClassNotFoundException,  InstantiationException, IllegalAccessException
    {
        
        for(int i = 0; i < gpPins.length; i++)
        {
            System.out.println("Pin " + i +   " State:     " + gpPins[i].getState());//debug
            currPin = gpPins[i].getState().toString();//Changes getState to a string
            System.out.println(currPin);//Debug
            doorID = i + 1;//Sets the door id for the database
            if(currPin.equals("HIGH"))//checks to see if the value is HIGH if so change status to 1
            {
                status = 1;
            }
            else if(currPin.equals("LOW"))//Checks to see if value is LOW if so change status to 0
            {
                status = 0;
            }
            db.dbConnection();//Open up database connection
            String update = "UPDATE `door` SET `status`=" + status + " WHERE `id`="+ doorID +";";//Create the update statement to update the door table
            db.getStatement().executeUpdate(update);//Execute the update statement.
            
        }
    }
    
}
