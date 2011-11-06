<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<?php
	include 'database.php';
	
	$id = $_REQUEST["id"];
	$conn = conn();
	$str = "SELECT PersonId, FirstName, LastName FROM Person WHERE personId=".$id;
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
					<td>
						<div>
							<a onclick="getChildren(<?php echo $id?>)" href="#"><?php echo $last?>, <?php echo $first?></a>
							<div id="View<?php echo $id?>"></div>
						</div>
					</td>
				</tr>

<?php 
	}
	mysql_close($conn);
?>
</table>