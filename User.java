//Author: Margo Mac Kay
//November 2015
/*
 * PiTected Project
 * Intended to be implemented in other project classes to aid in OOP.
 */
public class User {

	//initialize variables
	int userID;
	String username;
	String code;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int getUserID() {//get user ID
		return userID;
	}

	public void setUserID(int userID) {//set user ID
		this.userID = userID;
	}

	public String getUsername() {//get username
		return username;
	}

	public void setUsername(String username) {//set username
		this.username = username;
	}

	public String getCode() {//get user code
		return code;
	}

	public void setCode(String code) {//set user code
		this.code = code;
	}

}
