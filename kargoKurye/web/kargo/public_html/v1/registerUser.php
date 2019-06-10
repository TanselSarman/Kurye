<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){
	if(
		isset($_POST['username']) and 
			isset($_POST['email']) and 
		       isset($_POST['password']))
		{
		//operate the data further 

		$db = new DbOperations(); 

		$result = $db->createUser( 	$_POST['username'],
									$_POST['password'],
									$_POST['email'],
									$_POST['sehr'],
									$_POST['yetki']
									
									
									
									
									
								);
		if($result == 1){
			$response['error'] = false; 
			$response['message'] = "Başarıyla kayıt olundu.";
		}elseif($result == 2){
			$response['error'] = true; 
			$response['message'] = "Hata oluştu, tekrar deneyin.";			
		}elseif($result == 0){
			$response['error'] = true; 
			$response['message'] = "Kullanıcı zaten mevcut.";						
		}

	}else{
		$response['error'] = true; 
		$response['message'] = "Zorunlu alanları doldurun.";
	}
}else{
	$response['error'] = true; 
	$response['message'] = "Geçersiz istek";
}

echo json_encode($response);
