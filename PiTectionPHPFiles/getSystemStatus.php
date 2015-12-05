<?php

require('header.php');

  
//Return the most recent system log entry

$result = $connection->query("SELECT status, passphrase FROM system");


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
