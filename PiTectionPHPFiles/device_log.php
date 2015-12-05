<?php
require('header.php');

  


$result = $connection->query("SELECT door.name, door_log.status, door_log.timestamp FROM door, door_log WHERE door.id = door_log.door_id
 UNION 
 SELECT motion.name, motion_log.status, motion_log.timestamp FROM motion, motion_log WHERE motion.id = motion_log.motion_id
 ORDER BY timestamp DESC");


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
