<?php
	function conn() {
		$db = mysql_connect('localhost:3306/mlm', 'root', '');
		if (!$db) {
	    	die('Could not connect: ' . mysql_error());
		}
		mysql_select_db("mlm", $db);
		return $db;
	}
	function qry($sql, $db) {
		$result = mysql_query($sql, $db) OR die(mysql_error());
	    return $result;
	}
	function add($first, $last) {
		$conn = conn();
		$sql = "INSERT INTO Person(FirstName, LastName) Values('".$first."','".$last."')";
		echo $sql;
		mysql_query($sql, $conn) OR die(mysql_error());
		mysql_close($conn);
	}
	function update($id, $first, $last, $plan) {
		
	}
?>