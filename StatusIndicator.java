
/**
 *
 * @author Bryce Renninger
 *         PiTechted
 */

import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.SQLException;

public class StatusIndicator 
{
    //create a new object of database connection class
    static DBConnection db = new DBConnection();
    //create a new object of the user class
    private static User currUser = new User();
    //Creates an int called status
    public int status;
    //Created a boolean called rsStatus
    public boolean rsStatus;
    public static void main(String[] args) {
    }
  
    public void checkStatus()
    {
        try 
        {
            //DEBUG
            //System.out.println("CHECK STATS" + rsStatus); DEBUG
            //Starts the connection to the database
            db.dbConnection();
            //Grabs the system info from the database
            ResultSet rset = db.getStatement().executeQuery("SELECT `status` FROM `system` WHERE `id` = 1;");
            while (rset.next())
            {
                //Gets the TinyInt status value from the database and assigns it to the rsStatus variable
                rsStatus = rset.getBoolean(1);
                //DEBUG
                System.out.println("WHILE " + rsStatus); 
            }
            
        }
        catch (ClassNotFoundException | InstantiationException| IllegalAccessException | SQLException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
    }
    public int changeStatus()
    {
        //DEBUG
        //System.out.println("TEST !"); DEBUG
        checkStatus();
        //Checks if the System is armed or disarmed
        if (rsStatus == true)
        {
            //Changes the status to 0
            status = 0;
            //DEBUG
            //System.out.println("CHANGE STATUS" + status); 
        }
        else if (rsStatus == false)
        {
            //Changes the status to 1
            status = 1;
            //DEBUG
            //System.out.println("CHANGE STATUS" + status); 
        }
        return status;
    }
}
