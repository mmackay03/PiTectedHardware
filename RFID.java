/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bball_000
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


public class RFID {
   
    static int status;
    static boolean stat;
    static DBConnection db = new DBConnection();
    static statusIndicator si = new statusIndicator();
    final static GpioController gpio = GpioFactory.getInstance();
    final static GpioPinDigitalOutput rfidReader = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "RFID", PinState.LOW);
    // initializes the serial port on the PI
    final static Serial serialPort = SerialFactory.createInstance();
    static String card = "";
    static String cutCard = "";  
    static AlarmSystem sys = new AlarmSystem();
    
    
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
                        Thread.sleep(10000);
                         // Grabs the data from the card and inputs it into a string.
                        card = event.getData();
                        // A while statement that makes sure the data grabbed is the full RFID number and not a partial due to interference
                        while(card.length() > 10)
                        {
                            // Gets rid of the new line character in the beginning of the card. And gets rid of all characters after the last digit of the RFID number.
                            cutCard = card.substring(1,10);
                            //debug
                            System.out.println("Data Recieved: " + cutCard);
                            //Changes the reader back to powerstate low and able to read info again
                            rfidReader.low();
                            status = si.changeStatus();
                            sys.getCheckCode("RFIDCode", cutCard, status);
                            card = "";
                            cutCard = "";
                        }
                        rfidReader.low();
                    }
                    catch(SerialPortException ex) 
                    {
                        System.out.println(ex.getMessage());
                        return;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RFID.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            try 
        {
           serialPort.open("/dev/ttyAMA0" , 2400);
//         serial1.open("/dev/ttyAMA0" , 2400);
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