<?php
require('header.php');

$systemID = $_REQUEST["system_id"];
$status = $_REQUEST["status"];
$user = $_REQUEST["user_id"];

$query = mysqli_prepare($connection, "INSERT INTO  system_log (status, system_id, user_id, timestamp)
VALUES (?,?,?,now())");
mysqli_stmt_bind_param($query, "sss",$status,$systemID,$user);
mysqli_stmt_execute($query);

mysqli_stmt_close($query);

$query = mysqli_prepare($connection, "UPDATE system set status = '$status' WHERE id = '$systemID'");
mysqli_stmt_execute($query);

mysqli_stmt_close($query);

mysqli_close($connection);

?>
