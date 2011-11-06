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
	function add($first, $last, $user, $pass, $parentId) {
		$conn = conn();
		$sql = "INSERT INTO Person(FirstName, LastName, UserName, Password, ParentId) Values('".$first."','".$last."','".$user."','".$pass."',".$parentId.")";
		echo $sql;
		mysql_query($sql, $conn) OR die(mysql_error());
		mysql_close($conn);
	}
	function update($first, $last, $user, $pass, $personId, $plan) {
		$conn = conn();
		$sql = "UPDATE Person SET FirstName='".$first."', LastName='".$last."', UserName='".$user."', Password='".$pass."', PlanType='".$plan."' WHERE PersonId=".$personId;
		echo $sql;
		mysql_query($sql, $conn) OR die(mysql_error());
		mysql_close($conn);
	}
?>