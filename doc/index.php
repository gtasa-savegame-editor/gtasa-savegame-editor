<?php

// Pages
$pages[] = array('overview', 'overview', 'Overview');
$pages[] = array('block00', 'block 00', 'Block 00');
$pages[] = array('block01', 'block 01', 'Block 01 - Script');
$pages[] = array('block02', 'block 02', 'Block 02 - Objects');
$pages[] = array('block03', 'block 03', 'Block 03 - Garages');
$pages[] = array('block04', 'block 04', 'Block 04');
$pages[] = array('block05', 'block 05', 'Block 05');
$pages[] = array('block06', 'block 06', 'Block 06 - Pickups');
$pages[] = array('block08', 'block 08', 'Block 08');
$pages[] = array('block09', 'block 09', 'Block 09 - Radar Blips');
$pages[] = array('block10', 'block 10', 'Block 10 - Zones');
$pages[] = array('block11', 'block 11', 'Block 11 - Gang Weapons');
$pages[] = array('block12', 'block 12', 'Block 12 - Car Generator');
$pages[] = array('block15', 'block 15', 'Block 15');
$pages[] = array('block16', 'block 16', 'Block 16 - Stats');
$pages[] = array('block17', 'block 17', 'Block 17');
$pages[] = array('block18', 'block 18', 'Block 18');
$pages[] = array('block19', 'block 19', 'Block 19');
$pages[] = array('block20', 'block 20', 'Block 20 - Tags');
$pages[] = array('block21', 'block 21', 'Block 21');
$pages[] = array('block22', 'block 22', 'Block 22 - Clothes');
$pages[] = array('block23', 'block 23', 'Block 23');
$pages[] = array('block24', 'block 24', 'Block 24 - Unique Jumps');
$pages[] = array('block25', 'block 25', 'Block 25 - Teleport');
$pages[] = array('block26', 'block 26', 'Block 26');
$pages[] = array('block27', 'block 27', 'Block 27');
$pages[] = array('block30', 'block 30', 'Block 30');
$pages[] = array();
$pages[] = array('threads', 'threads', 'Threads');
$pages[] = array('weapons', 'weapons', 'Weapons');
$pages[] = array('pickups', 'pickups', 'Pickups');
$pages[] = array('sprites', 'sprites', 'Sprites');
$pages[] = array('vehicle_colors', 'vehicle colors', 'Vehicle colors');
$pages[] = array('vehicle_types', 'vehicle types', 'Vehicle types');
$pages[] = array('vehicle_mods', 'vehicle mods', 'Vehicle mods');
$pages[] = array('locations', 'locations', 'Locations');
$pages[] = array('savehouses', 'savehouses', 'Savehouses');
$pages[] = array('map', 'map', 'Map');
$pages[] = array('legend', 'legend', 'Legend');

// Find the page
$page_id = -1;
if (isset($_GET['page'])) {
  foreach ($pages as $id => $page) {
    if (count($page) == 3 && $page[0] == $_GET['page']) {
      $page_id = $id;
      break;
    }
  }
}

// Output or redirect
if ($page_id != -1)
{
  // Create Toc
  $toc = '';
  foreach ($pages as $id => $page) {
    if (count($page) == 0) {
      $toc .= "      <br />\n";
    }
    elseif ($id == $page_id) {
      $toc .= "      <span>".$page[1]."</span><br />\n";
    }
    else {
      $toc .= "      <a href=\"?page=".$page[0]."\">".$page[1]."</a><br />\n";
    }
  }
  $toc = substr($toc, 0, -7);

  // Create html
  $document = file_get_contents('document.htm');
  $content = str_replace("\n", "\n      ", file_get_contents($_GET['page'].'.htm'));
  $html = str_replace(array('$toc', '$title', '$content'), array($toc, $pages[$page_id][2], $content), $document);
  
  // Output
  header("Content-Type: text/html; charset=utf-8");
  echo $html;
}
else
{
  // Redirect
  header("HTTP/1.1 307 Temporary Redirect");
  header("Location: index.php?page=overview");
}

?>