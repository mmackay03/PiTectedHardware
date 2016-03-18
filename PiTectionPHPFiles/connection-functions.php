<?php
//used to sanitize any strings before inserting into database
function sanitizeString($string, $connection){
	$string = strip_tags($string);
	$string = htmlentities($string);
	$string = stripslashes($string);
	$string = $connection->real_escape_string($string);
	return $string;
}

//Strings to append and prepend strings before hashing
//and inserting into database
$salt1 = '^%$#@!#$%';
$salt2 = '%(*^^$#$';
//Used to hash strings, notably passwords, salt is added
//to the text input from users to decrease security loopholes
function hashString($string, $salt1, $salt2){
	$token = hash('SHA256', $salt1.$string.$salt2);
	return $token;
}
?>