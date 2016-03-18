<?php
require('header.php');
require('connection-functions.php');
require('session-functions.php');
$name = $_REQUEST["username"];
$password = $_REQUEST["password"];
/**Hash the password before matching to queried password*/
$hashword = hashString($password, $salt1, $salt2);
$result = $connection->query("SELECT id, username, password FROM users WHERE username='$username'");
$user = array();
$result->data_seek(0);
while($row =mysqli_fetch_assoc($result))
    {
    	
        $user[] = $row;
    }
    
    if($user[0]['password'] == $hashword){
    /**Create unique session ID*/
    $sessID = uniqid();
    //Insert/update sessionID in to the user's columm
    storeSession($connection, $name, $sessID);
		
    	 $return = array('status' => 'loggedIn', 'session' => $sessID, 'username' => $name, 'result'=>true);
		} 
    else {
    	$return = array('status' => 'failed', 'result' =>false);
	}
    
   	
echo json_encode($return);
      
mysqli_stmt_close($query);
mysqli_close($connection);
?>