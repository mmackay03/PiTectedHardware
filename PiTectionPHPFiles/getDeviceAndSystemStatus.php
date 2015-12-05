<?php
require('header.php');

  


$result = $connection->query("SELECT name, status FROM door UNION SELECT name, status FROM motion");


$user = array();
$result->data_seek(0);


while($row =mysqli_fetch_assoc($result))
    {	
        $user[] = $row;
       
    }
  
      
      
mysqli_stmt_close($query);

$result2 = $connection->query("SELECT name, status FROM system");


$result2->data_seek(0);


while($row2 =mysqli_fetch_assoc($result2))
    {	
        $user[] = $row2;
       
    }
    echo json_encode($user);
      
      
mysqli_stmt_close($query);

mysqli_close($connection);

?>
