<?php
?>
<html> 
<head> 
<title>Edit</title>
</head> 
<body>
<form name="edit" action="#.htm" method="get">
<table>
	<tr>
		<td>Lastname: </td>
		<td><input type = "text" name ="lastname"/></td>
	</tr>
	<tr>
		<td>Firstname: </td>
		<td><input type = "text" name ="firstname"/></td>
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
		<input type = "submit" value = "Save"/>
		</td>
	</tr>
</table>
</form>
</body> 
</html>