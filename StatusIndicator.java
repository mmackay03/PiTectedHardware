/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bryce Renninger
 */

import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.SQLException;

public class StatusIndicator 
{
    static DBConnection db = new DBConnection();//create a new object of database connection class
    private static User currUser = new User();//create a new object of the user class
    public int status;
    public boolean rsStatus;
    public static void main(String[] args) {
    }
  
    public void checkStatus()
    {
        try 
        {
            //DEBUG
            //System.out.println("CHECK STATS" + rsStatus); DEBUG
            db.dbConnection();
            //Grabs the system info
            ResultSet rset = db.getStatement().executeQuery("SELECT `status` FROM `system` WHERE `id` = 1;");
            while (rset.next())
            {
                //Gets the TinyInt status value from the database and assigns it to the rsStatus variable
                rsStatus = rset.getBoolean(1);
                //DEBUG
                //System.out.println("WHILE " + rsStatus); DEBUG
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
