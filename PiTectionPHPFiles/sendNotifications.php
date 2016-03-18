<?php
require_once('header.php');
require_once('downstreamMessages.php');
// API access key from Google API's Console
define( 'API_ACCESS_KEY', 'AIzaSyDPp-tATRutZUI-eE3I4TYSrvkv3jDbcmE' );

$result = $connection->query("SELECT gcm_token FROM users");

$index = 0;
$user = array();
$result->data_seek(0);
while($row =mysqli_fetch_assoc($result))
    {
    	
        $user[] = $row;
        downstreamMessages($row['gcm_token']);
    } 
    
mysqli_close($connection);

?>