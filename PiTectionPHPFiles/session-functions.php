<?php
require_once('header.php');
function storeSession($connection, $username, $sessionID){
$sql = "UPDATE users SET session ='$sessionID' WHERE username = '$username'";
	if (mysqli_query($connection, $sql)) {
    	return true;
	} else {
    	return false;
	}
    $connection->close();
}

if(isset($_REQUEST['username'])){
	$username = $_REQUEST['username'];
    }
if(isset($_REQUEST['session'])){
	$session = $_REQUEST['session'];
}



?>