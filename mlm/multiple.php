<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<?php
	include 'database.php';
	
	$id = $_REQUEST["id"];
	$conn = conn();
	$str = "SELECT PersonId, FirstName, LastName FROM Person WHERE parentId=".$id;
	$result = qry($str, $conn);
?>
<table border="0">
<?php 
    while($row=mysql_fetch_array($result)) {
    	$id = $row["PersonId"];
    	$first = $row["FirstName"];
    	$last = $row["LastName"];
?>
				<tr>
					<td>&nbsp;&nbsp;</td>
					<td>
						<a onclick="getChildren(<?php echo $id?>)" href="#"><?php echo $last?>, <?php echo $first?></a>
						<div id="View<?php echo $id?>"></div>
					</td>
				</tr>

<?php 
	}
	mysql_close($conn);
?>
</table>