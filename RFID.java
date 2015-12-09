/**
 *
 * @author Bryce Renninger
 *         PiTechted
 */

import com.pi4j.component.lcd.LCDTextAlignment;
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
    final private static Serial serialPort = SerialFactory.createInstance();
    //Creates a new object of the StatusIndicator class
    public static StatusIndicator si = new StatusIndicator();
    public static String card = "";
    public static String cutCard = ""; 
    //Creates a new object of the LCD class
    public static LCD lcd = new LCD();
    //Creates a new object of the AlarmSystem class
    public static AlarmSystem sys = new AlarmSystem();
    private static int rfidStatus = 0;
    
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
                        rfidReader.high();
                        Thread.sleep(3000);
                        System.out.println("Before While");
                         // Grabs the data from the card and inputs it into a string.
                        card = event.getData();
                        // A while statement that makes sure the data grabbed is the full RFID number and not a partial due to interference
                        if(card.length() == 12)
                        {
                            System.out.println("After While, Before if");
                            if (rfidStatus == 0)
                            {
                                System.out.println("After if, Status = 1");
                                rfidStatus = 1;
                            // Gets rid of the new line character in the beginning of the card. And gets rid of all characters after the last digit of the RFID number.
                            cutCard = card.substring(1,10);
                            //debug
                            System.out.println("Data Recieved: " + cutCard);
                            //Changes the reader back to powerstate low and able to read info again
                            //rfidReader.low();
                            sys.getCheckCode("RFIDCode", cutCard);
                            //sys.getCheckCode("RFIDCode", cutCard, status);
                            card = "";
                            cutCard = "";
                            }
                        }
                        else
                        {
                            lcd.writeln(0, "Not Accepted", LCDTextAlignment.ALIGN_CENTER);
                            lcd.writeln(1, "Scan Card Again", LCDTextAlignment.ALIGN_CENTER);
                        }

                        
                    }
                    catch(SerialPortException ex) 
                    {
                        System.out.println(ex.getMessage());
                        return;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RFID.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    rfidStatus = 0;
                    System.out.println("                            SETTING RFID LOW");
                    try{
                        Thread.sleep(500);
                    }catch (InterruptedException ex){
                        
                    }
                    rfidReader.low();
                System.out.println("After catch");

                }
                
            });
            
            rfidStatus = 0;
            System.out.println("After Listener, status = 0");
            try 
            {
                serialPort.open("/dev/ttyAMA0" , 2400);
                // System.out.println("Port Open");
    //          serial1.open("/dev/ttyAMA0" , 2400);
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