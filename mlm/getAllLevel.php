<?php
include 'database.php';
$id = 94;

$arrLevel1 = getAllLevel1($id);
$arrLevel2 = getAllLevel2($id);
$arrLevel3 = getAllLevel3($id);
$arrLevel4 = getAllLevel4($id);
$arrLevel5 = getAllLevel5($id);

$tree = 'Level 1<br/>';
foreach($arrLevel1 as $data) {
    $tree .= $data['lastname'].', '.$data['firstname'].'<br/>';
}

$tree .= '<br><br>Level 2<br/>';
foreach($arrLevel2 as $data) {
    $tree .= $data['lastname'].', '.$data['firstname'].'<br/>';
}

$tree .= '<br><br>Level 3<br/>';
foreach($arrLevel3 as $data) {
    $tree .= $data['lastname'].', '.$data['firstname'].'<br/>';
}

$tree .= '<br><br>Level 4<br/>';
foreach($arrLevel4 as $data) {
    $tree .= $data['lastname'].', '.$data['firstname'].'<br/>';
}

$tree .= '<br><br>Level 5<br/>';
foreach($arrLevel5 as $data) {
    $tree .= $data['lastname'].', '.$data['firstname'].'<br/>';
}
echo $tree;
?>