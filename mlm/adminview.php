<?php
$link = mysql_connect('localhost:3306/mlm', 'root', '');
if (!$link) {
	die('Could not connect: ' . mysql_error());
}
echo 'Connected successfully';
mysql_close($link);

?>
<html>
<head>
<title>Home</title>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<link type="text/css" rel="stylesheet" href="mlm.css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
</head>
<body>

<table width="100%" border="1">
	<tr>
		<td colspan="2" style="background-color: rgb(24, 74, 134);">
		<center>
		<h1>Multi Level Marketing</h1>
		</center>
		</td>
	</tr>

	<tr valign="top">
		<td
			style="background-color: rgb(58, 120, 182); width: 30%; text-align: top;">
		<table>
			<tr>
				<td>Add/Search Panel</td>
			</tr>
			<tr>
				<td>First name:</td>
				<td><input type="text" name="firstname" /><br />
				</td>
			</tr>
			<tr>
				<td>Last name:</td>
				<td><input type="text" name="lastname" /><br />
				</td>
			</tr>
			<tr>
				<td><input type="submit" value="Search" /></td>
			</tr>
			<tr>
				<td><br />
				<br />
				</td>
			</tr>
			<tr>
				<td>Result</td>
			</tr>
			<tr>
				<td>
				<div id="SearchResult"></div>
				</td>
			</tr>
		</table>

		</td>
		<td
			style="background-color: rgb(97, 157, 207); height: 550px; width: 400px; text-align: top;">
		<table border="1">
			<tr>
				<td>
				<div><a onclick="return getChildren(1);" href="#">Name</a>
				<div id="View1"></div>
				</div>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="2"
			style="background-color: rgb(24, 74, 134); text-align: center;">
		Copyright © 2011 mysoftlabs.com</td>
	</tr>
</table>

<script>
	function getChildren(a) {
		$.get('multiple.php', function(data) {
			$('#View'+a).html(data);
		});
	}

</script>
</body>
</html>
