<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){
	
		$db = new DbOperations(); 
		



		$mesaj = $db->musteriMusteriMesajGetir($_POST['gonderen'],$_POST['alici']);
		
			
			
			$response['Id'] = $mesaj['Id'];
			$response['gonderen'] = $mesaj['gonderen'];
			$response['alici'] = $mesaj['alici'];
			$response['mesaj'] = $mesaj['mesaj'];
			$response['deger'] = $mesaj['deger'];
			
		
	

	
}

echo json_encode($response);