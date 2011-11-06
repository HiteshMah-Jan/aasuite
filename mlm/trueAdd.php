<?php
include 'database.php';

$firstName = $_REQUEST["afirstname"];
$lastName = $_REQUEST["alastname"];
$userName = $_REQUEST["ausername"];
$password = $_REQUEST["apassword"];
$parentId = $_REQUEST["aparentid"];
if ('' == $firstName && '' == $lastName) {
	echo "All fields are required!";
}
else {
	add($firstName, $lastName, $userName, $password, $parentId);
}
?>
