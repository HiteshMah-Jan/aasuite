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
	function getHierarchy($id) {
		global $tree_arr;
	    $sql = "SELECT personid, lastname, firstname, parentid FROM person WHERE parentid=".$id;
		$conn = conn();
		$result = mysql_query($sql, $conn) OR die(mysql_error());
		while($data = mysql_fetch_array($result)) {
		    $tree_arr[] = $data;
		    if($data['personid'] > 0) {
		    	getHierarchy($data['personid']);
		    }
		}
	}
	function getChildrenCount($id) {
		$GLOBALS['tree_arr'] = '';
		getHierarchy($id);
		return count($GLOBALS['tree_arr']);
	}
	function constructInStatement($arr) {
		$str = '(';
		foreach ($arr as $data) {
			$str .= $data['personid'].',';
		}
		return $str.'0)';
	}
	function getAllLevel1($id) {
		$retArr = array();
		$conn = conn();
		$result = qry("SELECT personid, firstname, lastname, planType, username, password FROM Person WHERE parentid=".$id, $conn);
		while($data = mysql_fetch_array($result)) {
		    $retArr[] = $data;
		}
		mysql_close($conn);
		return $retArr;
	}
	function getAllLevel2($id) {
		$retArr = array();
		$arr = getAllLevel1($id);
		$conn = conn();
		$result = qry("SELECT personid, firstname, lastname, planType, username, password FROM Person WHERE parentid in ".constructInStatement($arr), $conn);
		while($data = mysql_fetch_array($result)) {
		    $retArr[] = $data;
		}
		mysql_close($conn);
		return $retArr;
	}
	function getAllLevel3($id) {
		$retArr = array();
		$arr = getAllLevel2($id);
		$conn = conn();
		$result = qry("SELECT personid, firstname, lastname, planType, username, password FROM Person WHERE parentid in ".constructInStatement($arr), $conn);
		while($data = mysql_fetch_array($result)) {
		    $retArr[] = $data;
		}
		mysql_close($conn);
		return $retArr;
	}
	function getAllLevel4($id) {
		$retArr = array();
		$arr = getAllLevel3($id);
		$conn = conn();
		$result = qry("SELECT personid, firstname, lastname, planType, username, password FROM Person WHERE parentid in ".constructInStatement($arr), $conn);
		while($data = mysql_fetch_array($result)) {
		    $retArr[] = $data;
		}
		mysql_close($conn);
		return $retArr;
	}
	function getAllLevel5($id) {
		$retArr = array();
		$arr = getAllLevel4($id);
		$conn = conn();
		$result = qry("SELECT personid, firstname, lastname, planType, username, password FROM Person WHERE parentid in ".constructInStatement($arr), $conn);
		while($data = mysql_fetch_array($result)) {
		    $retArr[] = $data;
		}
		mysql_close($conn);
		return $retArr;
	}
?>