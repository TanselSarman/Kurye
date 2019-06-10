<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){
	if(isset($_POST['username']) and isset($_POST['password'])){
		$db = new DbOperations(); 

		if($db->userLogin($_POST['username'], $_POST['password'])){
			$user = $db->getUserByUsername($_POST['username']);
			$response['error'] = false; 
			$response['id'] = $user['id'];
		    $response['email'] = $user['email'];
		    $response['username'] = $user['username'];
		    $response['yetki'] = $user['yetki'];
		    $response['country'] = $user['country'];

			
			
		}else{
			$response['error'] = true; 
			$response['message'] = "Geçersiz kullanıcı adı veya şifre.";			
		}

	}else{
		$response['error'] = true; 
		$response['message'] = "Zorunlu alanları girmediniz.";
	}
}

echo json_encode($response);