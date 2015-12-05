<?php






/* Attempt MySQL server connection.  */
require('header.php');
 
// Check connection
if($connection === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
 
// Escape user inputs for security
$username = mysqli_real_escape_string($connection, $_REQUEST['username']);
$password = mysqli_real_escape_string($connection, $_REQUEST['password']);
$keyCode = mysqli_real_escape_string($connection, $_REQUEST['keyCode']);
$rfid = mysqli_real_escape_string($connection, $_REQUEST['RFIDCode']);
 
// attempt insert query execution
$sql = "INSERT INTO users (username, password, keyCode, RFIDCode, system_id) VALUES
('$username', '$password','$keyCode', '$rfid', 1)";
if(mysqli_query($connection, $sql)){
    return true;
} else{
    return false;
}
 
// close connection
mysqli_close($connection);
?>
