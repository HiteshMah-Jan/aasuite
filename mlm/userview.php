<?php
	include 'database.php';
	
	$id = $_REQUEST["id"];
	$conn = conn();
	$str = "SELECT PersonId, FirstName, LastName FROM Person WHERE personId=".$id;
	$result = qry($str, $conn);
?>
<html>
<head>
<title>Home</title>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
			<link type="text/css" rel="stylesheet" href="mlm.css">
			<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/themes/base/jquery-ui.css" type="text/css" media="all" />
			<link rel="stylesheet" href="http://static.jquery.com/ui/css/demo-docs-theme/ui.theme.css" type="text/css" media="all" />
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js" type="text/javascript"></script>
			<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js" type="text/javascript"></script>
			<script src="http://jquery-ui.googlecode.com/svn/tags/latest/external/jquery.bgiframe-2.1.2.js" type="text/javascript"></script>
			<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/i18n/jquery-ui-i18n.min.js" type="text/javascript"></script>
</head>
<script>
		function getChildren(a) {
			url = 'multiple.php?id='+a+"&t="+new Date();
//			alert(url);
			$.get(url, function(data) {
//				alert(data);
				$('#View'+a).html(data);
			});
		}
		function getSingle(a) {
			url = 'view.php?id='+a+"&t="+new Date();
//			alert(url);
			$.get(url, function(data) {
//				alert(data);
				$('#View').html(data);
			});
		}
</script>
<body>
<table width="100%" border="1">
	<tr>
		<td colspan="2">
		<center>
		<h1>Multi Level Marketing</h1>
		</center>
		</td>
	</tr>
	<tr valign="top">
		<td class="right">
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
		</td>
		<td>
			<?php
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
	<tr>
		<td class="bottom" colspan="2">Copyright© 2011 mysoftlabs.com</td>
	</tr>
</table>
</body>
</html>
