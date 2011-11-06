<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<?php
include 'database.php';

$first = $_REQUEST["firstname"];
$last = $_REQUEST["lastname"];

$conn = conn();
if ('' == $first && '' == $last) {
	$result = qry("SELECT personid, firstname, lastname, planType, username, password FROM Person", $conn);
}
else {
	$result = qry("SELECT personid, firstname, lastname, planType, username, password FROM Person WHERE firstname LIKE '".$first."%' OR lastname LIKE '".$last."%'", $conn);
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
		<th></th>
	</tr>
	<?php
	while($row = mysql_fetch_array($result)) {
		echo "<tr>";
		echo "<td><a onclick='return getSingle(".$row['personid'].");' href='#'>".$row['firstname']."</a></td>";
		echo "<td>".$row['lastname']."</td>";
		echo "<td>".$row['planType']."&nbsp;</td>";
		echo "<td><a href='#' onclick='addRec(".$row['personid'].")'>Add</a></td>";
		echo "<td><a href='#' onclick=\"updateRec(".$row['personid'].",'".$row['firstname']."','".$row['lastname']."','".$row['planType']."','".$row['username']."','".$row['password']."')\">Update</a></td>";
		echo "</tr>";
	}
	mysql_free_result($result);
	mysql_close($conn);
	?>
</table>
