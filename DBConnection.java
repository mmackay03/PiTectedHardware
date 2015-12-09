//Author: Margo Mac Kay
//November 2015
/*
 * PiTected Project
 * This class is to be implemented in all other classes needing a database connection.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	Connection con = null;
	Statement statement;
	public DBConnection() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Statement getStatement(){
		return statement;
	}
	
	void dbConnection() throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException {
		//driver
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		//connection
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pitected",
				"root", "jeep");
		statement = con.createStatement();
	}
	void conClose() throws SQLException{
		con.close();
	}

}