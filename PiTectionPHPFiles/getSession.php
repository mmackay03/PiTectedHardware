<?php
require_once('header.php');
if(isset($_REQUEST['session'])){
$session = $_REQUEST['session'];

if(isset($_REQUEST['username'])){
$username = $_REQUEST['username'];
}
$result = $connection->query("SELECT session FROM users WHERE username='$username'");
$user = array();
$result->data_seek(0);
while($row =mysqli_fetch_assoc($result))
    {
        
        $user[] = $row;
      
    } 
      if($user[0]['session'] == $session){
         $user = array('status' => 'sessionOK');
        } 
    else {
        $user = array('status' => 'failed');
    }
echo json_encode($user);
      
mysqli_stmt_close($query);
mysqli_close($connection);
}
else{
echo json_encode(array('status' => 'failed'));
}
?>