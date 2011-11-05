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
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
</head>
<body>
<table border=1>
	<tr>
		<td>
			<table>
				<tr>
					<td>Search</td>
				</tr>
				<tr>
					<td>First name:</td><td> <input type="text" name="firstname" /><br /></td>
				</tr>
				<tr>
					<td>Last name:</td><td> <input type="text" name="lastname" /><br /></td>
				</tr>
				<tr><td><input type="submit" value="Search"/></td></tr>
				<tr><td><br/><br/></td></tr>
				<tr><td>Result</td></tr>
				<tr>
					<td>
						<div id="SearchResult"></div>
					</td>
				</tr>
			</table>
		</td>
		<td>
			<table border="1">
				<tr>
					<td>
						<div>
							<a onclick="return getChildren(1);" href="#">Name</a>
							<div id="View1"></div>
						</div>
					</td>
				</tr>
			</table>
		</td>
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