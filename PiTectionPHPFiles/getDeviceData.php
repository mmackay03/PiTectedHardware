<?php
require('header.php');

  


$result = $connection->query("SELECT name, status FROM door UNION SELECT name, status FROM motion WHERE status=1");


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
