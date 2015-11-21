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
    public static void main(String[] args) {
    }
  
    public void checkStatus()
    {
        try 
        {
            db.dbConnection();
            //get all user IDs and codes
            ResultSet rset = db.getStatement().executeQuery("SELECT `status` FROM `system` WHERE `id` = 1;");
            //System.out.println(rset.next());
            status = (rset.next()) ? 1 : 0;           
            
        }
        catch (ClassNotFoundException | InstantiationException| IllegalAccessException | SQLException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
    }
    public int changeStatus()
    {
        if (status == 1)
        {
            status = 0;
            return status;
        }
        else if (status == 0)
        {
            status = 1;
            return status;
        }
        return status;
    }
}
