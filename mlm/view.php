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
<tr> <td>
<br>
<br>
<?php
    }
	mysql_close($conn);
    
    $count = getChildrenCount($id);
    $level1count = count(getAllLevel1($id));
    $level2count = count(getAllLevel2($id));
    $level3count = count(getAllLevel3($id));
    $level4count = count(getAllLevel4($id));
    $level5count = count(getAllLevel5($id));
    $amount = (20 * $count);
?>
<div id='totals'>
	<table border=1>
		<tr>
			<td>Promo</td>
			<td>Count</td>
			<td>Amount</td>
			<td>Needed Count</td>
		</tr>
		<tr>
			<td>20 Pesos</td>
			<td><?php echo $count;?></td>
			<td><?php echo $amount;?></td>
			<td>NA</td>
		</tr>
		<tr>
			<td>100K</td>
			<td><?php echo $count;?></td>
			<td>NA</td>
			<td>3125</td>
		</tr>
		<tr>
			<td>Level 1 Count</td>
			<td><?php echo $level1count;?></td>
			<td>NA</td>
			<td>NA</td>
		</tr>
		<tr>
			<td>Level 2 Count</td>
			<td><?php echo $level2count;?></td>
			<td>NA</td>
			<td>NA</td>
		</tr>
		<tr>
			<td>Level 3 Count</td>
			<td><?php echo $level3count;?></td>
			<td>NA</td>
			<td>NA</td>
		</tr>
		<tr>
			<td>Level 4 Count</td>
			<td><?php echo $level4count;?></td>
			<td>NA</td>
			<td>NA</td>
		</tr>
		<tr>
			<td>Level 5 Count</td>
			<td><?php echo $level5count;?></td>
			<td>NA</td>
			<td>NA</td>
		</tr>
	</table>
</div>
</td>
</tr>
</table>