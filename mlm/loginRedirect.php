<?php
	include 'database.php';
	
	$username = strtoupper($_REQUEST['username']);
	$password = strtoupper($_REQUEST['password']);
	if ('ADMIN' == $username && 'ADMIN' == $password) {
  		gotoAdminView();
	}
	else {
		if ('' == $password) {
			header("Location: loginError.php");
		}
		else if ('' == $username) {
			header("Location: loginError.php");
		}
		else {
			gotoView($username, $password);
		}
	}
	
	function gotoAdminView() {
		header("Location: adminview.php");
	}
	
	function gotoView($username, $password) {
		$conn = conn();
		$result = qry("SELECT personid FROM Person WHERE username='".$username."' AND password='".$password."'", $conn);
//		var_dump($result);
		if (!$result) {
			die("No results found");
		}
		if ($row = mysql_fetch_array($result)) {
			$personid = $row['personid'];
//			var_dump($personid);
			mysql_free_result($result);
			mysql_close($conn);
			forward("userview.php?id=".$personid."&t=".time());
//			header("Location: view.php?id="+$personid+"&t="+time());
		}
		else {
			mysql_free_result($result);
			mysql_close($conn);
			header("Location: loginNotFound.php");
		}
	}

	function forward($location, $vars = array()) {
	    $file ='http://'.$_SERVER['HTTP_HOST']
	        .substr($_SERVER['REQUEST_URI'],0,strrpos($_SERVER['REQUEST_URI'], '/')+1)
	        .$location;
	
	    if(!empty($vars)) {
	         $file .="?".http_build_query($vars);
	    }
	
	    $response = file_get_contents($file);
	
	    echo $response;
	}
?>