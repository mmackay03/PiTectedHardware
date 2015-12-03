/**
 *
 * @author Bryce Renninger
 *         PiTechted
 */

import java.sql.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RFID 
{
   
    public static int status;
    //Creates a new object of the DBConnection class
    public static DBConnection db = new DBConnection();
    //Starts the gpio controller 
    final public static GpioController gpio = GpioFactory.getInstance();
    //Initializes the gpio 04 to start low that is running the RFID reader
    final public  static GpioPinDigitalOutput rfidReader = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "RFID", PinState.LOW);
    // initializes the serial port on the PI
    final public static Serial serialPort = SerialFactory.createInstance();
    //Creates a new object of the StatusIndicator class
    public static StatusIndicator si = new StatusIndicator();
    public static String card = "";
    public static String cutCard = ""; 
    //Creates a new object of the LCD class
    public static LCD lcd = new LCD();
    //Creates a new object of the AlarmSystem class
    public static AlarmSystem sys = new AlarmSystem();
    
    
    
    public static void main(String args[]) throws InterruptedException, UnsupportedEncodingException  
    {
        System.out.println("RFID CLass");
        while(true)
        {
            //Will continuosly have the RFID on and running as long as the file is being run. This is when the listener is active and waiting for input.
            serialPort.addListener(new SerialDataListener()
            {
                public void dataReceived(SerialDataEvent event)
                {
                    try
                    {
                        //Shuts down the RFID so it can't read anymore
                        rfidReader.high();
                   
                         // Grabs the data from the card and inputs it into a string.
                        card = event.getData();
                        // A while statement that makes sure the data grabbed is the full RFID number and not a partial due to interference
                        while(card.length() > 10)
                        {
                            // Gets rid of the new line character in the beginning of the card. And gets rid of all characters after the last digit of the RFID number.
                            cutCard = card.substring(1,10);
                            //debug
                            System.out.println("Data Recieved: " + cutCard);
                            //DEBUG
                            //System.out.println("RFID Status = " + status);
                            //status = si.changeStatus();
                            sys.getCheckCode("RFIDCode", cutCard);
                            //Resets both variables back to blank.
                            card = "";
                            cutCard = "";
                            
                        }
//                        Thread.sleep(2000);
//                        rfidReader.low();
                    }
                    catch(SerialPortException ex) 
                    {
                        System.out.println(ex.getMessage());
                        return;
                    }   
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(RFID.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                }
            });
            Thread.sleep(2000);
            //Activates the RFID so it can read again.
            rfidReader.low();
            try 
            {
                serialPort.open("/dev/ttyAMA0" , 2400);
                while(true)
                {
                    Thread.sleep(1000);
                }  
            }
            catch(SerialPortException ex)
            {
                System.out.println("SETUP FAILED: " + ex.getMessage());
                return;
            }
        }
        
    }

}