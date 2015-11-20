//Author: Margo Mac Kay
// 19 November 2015
/*
 * PiTected Project
 * This class is meant to be implemented in classes involving the system.
 * Addition methods/variables are to be added.
 */
import java.sql.ResultSet;
import java.sql.SQLException;


public class AlarmSystem {

	static DBConnection db = new DBConnection();//create a new object of database connection class
	private static User currUser = new User();//create a new object of the user class
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//check that the user is Authorized and call the armDisarm method
	public void getCheckCode(String attribute, String attributeValue, int status){
			try {
				db.dbConnection();
				//get all user IDs and codes
				ResultSet rset = db.getStatement().executeQuery("SELECT `id`, `" + attribute +"` FROM `users`;");
				rset.next();//set to first result
				//get the first code returned and check if it equals the entered code
				if(attributeValue.equals(rset.getString(attribute))){//If authorized user
					System.out.println("AUTHED");//debug
	//				System.out.println(rset.getInt("id"));//debugging: show user id
					currUser.setUserID(rset.getInt("id"));
					armDisarm(status);//Update the status of the system
				}
				//get the rest of the codes returned and chick if it equals the entered code
				while (rset.next()) {
//					System.out.println(rset.getString(attribute));//debugging: show each code in database after id 1
					if(attributeValue.equals(rset.getString(attribute))){//If authorized user
						System.out.println("Authorized User");//debug
						currUser.setUserID(rset.getInt("id"));//set the user id of the corresponding code
						armDisarm(status);//Update the status of the system
					}
					
					
				}
				db.conClose();//close connection to not effect future connection
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	

	//change the status of the system and log the change
	public void armDisarm(int status){
		try {
			db.dbConnection();
			//update system status. NOTE IT IS HARD CODED TO SYSTEM 1 BASED ON EXPECTED FUNCTIONALITY
			String update = "UPDATE `system` SET `status`=" + status + " WHERE `id`=1;";
			db.getStatement().executeUpdate(update);
			
			//log the system status change in table system_log
			String log = "INSERT INTO `system_log` (`system_id`, `user_id`, `status`) VALUES (1,"
					+ currUser.getUserID() + ", " + status +");";
			db.getStatement().executeUpdate(log);
			if (status == 1){
				System.out.println("System armed.");
			}else if (status == 0){
				System.out.println("System disarmed.");
			}
		
	//		System.out.println("INSERT INTO `system_log` (`system_id`, `user_id`, `status`) VALUES (1,"
	//				+ currUser.getUserID() + ", " + status +");");//debug: show system_log insert
			
			db.conClose();//close connection to not effect future connection
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
