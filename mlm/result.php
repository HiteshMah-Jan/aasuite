<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<?php
include 'database.php';

$first = $_REQUEST["firstname"];
$last = $_REQUEST["lastname"];

$conn = conn();
if ('' == $first && '' == $last) {
	$result = qry("SELECT personid, firstname, lastname, planType FROM Person", $conn);
}
else {
	$result = qry("SELECT personid, firstname, lastname, planType FROM Person WHERE firstname LIKE '".$first."%' OR lastname LIKE '".$last."%'", $conn);
}
if (!$result) {
	die("No results found");
}
?>
<table width="100%" border="1">
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Plan</th>
		<th></th>
	</tr>
	<?php
	while($row = mysql_fetch_array($result)) {
		echo "<tr><td><a onclick='return getSingle(".$row['personid'].");' href='#'>".$row['firstname']."</a></td><td>".$row['lastname']."</td><td>".$row['planType']."&nbsp;</td><td><a href='#' onclick='addRec(".$row['personid'].")'>Add</a></td></tr>";
	}
	mysql_free_result($result);
	mysql_close($conn);
	?>
</table>
