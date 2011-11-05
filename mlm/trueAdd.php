<?php
	include 'database.php';
	
	var_dump($_REQUEST);

		$firstName = $_REQUEST["firstName"];
		$lastName = $_REQUEST["lastName"];
		if ('' == $firstName && '' == $lastName) {
	  		echo "All fields are required!";
		}
		else {
			add($firstName, $lastName);
		}
?>

<html> 
<head> 
<title>Add</title>
</head> 
<body>
<form name="add" method="post" action="trueAdd.php">
<table>
	<tr>
		<td>Lastname: </td>
		<td><input type = "text" name="lastName"/></td>
	</tr>
	<tr>
		<td>Firstname: </td>
		<td><input type = "text" name="firstName"/></td>
	</tr>
	<tr>
		<td>Plan: </td>
		<td>
			<select>
				<option value="Plan A">Plan A</option>
				<option value="Plan B">Plan B</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>
		</td>
		<td>
			<input type="submit" value="Save"/>
		</td>
	</tr>
</table>
</form>
</body> 
</html>