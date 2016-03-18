<?php
require('header.php');
if(isset($_REQUEST['log_type'])){
$log_type = $_REQUEST['log_type'];
}
if(isset($_REQUEST['limit'])){
$limit = '0,'. $_REQUEST['limit'];
}

if($log_type == "system"){
	$sql = "select username, system_log.status, system_log.timestamp FROM system_log, users, system where system_log.system_id = system.id 
 and system_log.user_id = users.id ORDER BY timestamp DESC";
	fetchData($sql, $connection);
}
else if($log_type == "devices"){
	$sql = "SELECT door.name, door_log.status, door_log.timestamp FROM door, door_log WHERE door.id = door_log.door_id UNION SELECT motion.name, motion_log.status, motion_log.timestamp FROM motion, motion_log WHERE motion.id = motion_log.motion_id DESC";
	fetchData($sql, $connection);
}
else if($log_type == "all"){
	$sql = "select username, system_log.status, system_log.timestamp FROM system_log, users, system where system_log.system_id = system.id 
 and system_log.user_id = users.id
UNION
SELECT door.name, door_log.status, door_log.timestamp FROM door, door_log WHERE door.id = door_log.door_id
 UNION 
 SELECT motion.name, motion_log.status, motion_log.timestamp FROM motion, motion_log WHERE motion.id = motion_log.motion_id";
	fetchData($sql, $connection);
}

function fetchData($sql, $connection){
$myArray = array();
	if ($result=mysqli_query($connection,$sql)){
 while($row = $result->fetch_array(MYSQL_ASSOC)) {
            $myArray[] = $row;
    }
    echo json_encode($myArray);
	}
}

mysqli_close($connection);

?>