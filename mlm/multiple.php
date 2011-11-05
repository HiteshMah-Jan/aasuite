<?php
	include 'database.php';
	
	$conn = conn();
	$result = qry("SELECT PersonId, FirstName, LastName FROM Person", $conn);
?>
<table border="1">
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
							<div></div>
						</div>
					</td>
				</tr>

<?php 
	}
	mysql_close($conn);
?>
</table>
