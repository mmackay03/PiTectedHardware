<?php
require_once('header.php');
if(isset($_REQUEST['username'])){
$username = $_REQUEST['username'];
}
if(isset($_REQUEST['token'])){
$token = $_REQUEST['token'];
}

$sql = "UPDATE users SET gcm_token='$token' WHERE username='$username'";
if ($connection->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}
$connection->close();
?>