<?php
require('header.php');

  


$result = $connection->query("SELECT keyCode FROM users");


$user = array();
$result->data_seek(0);
while($row = $result->fetch_assoc()){
	$user[] = $row;
}
      echo json_encode($user);
      
mysqli_stmt_close($query);

mysqli_close($connection);


?>
