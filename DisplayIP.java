/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.system.NetworkInfo;
import com.pi4j.system.SystemInfo;
import java.util.Random;
import java.io.IOException;
import java.text.ParseException;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author bball_000
 */
public class DisplayIP 
{
    public static LCD lcd = new LCD();
    public static String ipAddress;
    public static String passPhrase;
    //create a new object of database connection class
    static DBConnection db = new DBConnection();
    
    
    public static void main(String[] args) throws InterruptedException, IOException, ParseException 
    {
      for (String ipAddress : NetworkInfo.getIPAddresses())
      {
        System.out.println("IP Addresses:  " + ipAddress);        
        lcd.writeln(0, ipAddress, LCDTextAlignment.ALIGN_CENTER);
        try
        {
            db.dbConnection();
            //update system staticIP. NOTE IT IS HARD CODED TO SYSTEM 1 BASED ON EXPECTED FUNCTIONALITY
            String update = "UPDATE `system` SET `staticIP`= '" + ipAddress  + "' WHERE `id`=1;";
            db.getStatement().executeUpdate(update);
            ipAddress = NetworkInfo.getIPAddress();
            Random randomGenerator = new Random();
            for (int idx = 1; idx <= 10; ++idx)
            {
                int randomInt = randomGenerator.nextInt(10);
                System.out.print(randomInt);
                passPhrase += randomInt;
            }
            passPhrase = passPhrase.substring(4,14);
            System.out.println();
            lcd.writeln(1, passPhrase, LCDTextAlignment.ALIGN_CENTER);
            lcd.backlight(1);
            System.out.println(ipAddress);
            update = "UPDATE `system` SET `passphrase`=" + passPhrase  + " WHERE `id`=1;";
            db.getStatement().executeUpdate(update);
        }
        catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e)
        {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
      }
    }
}
