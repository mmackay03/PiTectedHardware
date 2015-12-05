<?php
require('header.php');

  


$result = $connection->query("select username, system_log.status, system_log.timestamp FROM system_log, users, system where system_log.system_id = system.id 
 and system_log.user_id = users.id ORDER BY timestamp DESC");


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
