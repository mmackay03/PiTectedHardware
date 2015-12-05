<?php
require('header.php');

$name = $_REQUEST["username"];
$password = $_REQUEST["password"];

$result = $connection->query("SELECT id, username, password FROM users WHERE username = '$name' AND password = '$password'");


$user = array();
$result->data_seek(0);

while($row =mysqli_fetch_assoc($result))
    {
    	
        $user[] = $row;
    }
    echo json_encode($user);
      
mysqli_stmt_close($query);

mysqli_close($connection);


?>
