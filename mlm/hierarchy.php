<?php
include 'database.php';
$id = 94;

getHierarchy($id);
 
$tree = '';
foreach($tree_arr as $data) {
    $tree .= $data['lastname'].', '.$data['firstname'].'<br/>';
}

echo $tree;
echo getChildrenCount($id);
?>