<?php
require('header.php');

  


$result = $connection->query("select username, system_log.status, system_log.timestamp FROM system_log, users, system where system_log.system_id = system.id 
 and system_log.user_id = users.id
UNION
SELECT door.name, door_log.status, door_log.timestamp FROM door, door_log WHERE door.id = door_log.door_id
 UNION 
 SELECT motion.name, motion_log.status, motion_log.timestamp FROM motion, motion_log WHERE motion.id = motion_log.motion_id");


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
