<?php
include 'database.php';

$firstName = $_REQUEST["ufirstname"];
$lastName = $_REQUEST["ulastname"];
$userName = $_REQUEST["uusername"];
$password = $_REQUEST["upassword"];
$personId = $_REQUEST["upersonid"];
$plantype = $_REQUEST["uplantype"];
if ('' == $firstName && '' == $lastName) {
	echo "All fields are required!";
}
else {
	update($firstName, $lastName, $userName, $password, $personId, plantype);
}
?>
