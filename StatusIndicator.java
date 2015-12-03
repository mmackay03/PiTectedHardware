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
            //get all user IDs and codes
            ResultSet rset = db.getStatement().executeQuery("SELECT `status` FROM `system` WHERE `id` = 1;");
            while (rset.next())
            {
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
        if (rsStatus == true)
        {
            status = 0;
            //DEBUG
            //System.out.println("CHANGE STATUS" + status); DEBUG
        }
        else if (rsStatus == false)
        {
            status = 1;
            //DEBUG
            //System.out.println("CHANGE STATUS" + status); DEBUG
        }
        return status;
    }
}
