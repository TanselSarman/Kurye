
<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){
	
		//operate the data further 

		$db = new DbOperations(); 

		$result = $db->kargoIstek( 	$_POST['kullanici'],
									$_POST['enlem'],
									$_POST['boylam'],
									$_POST['sehir']
								);
		if($result == 1){
			$response['error'] = false; 
			$response['message'] = "User registered successfully";
		}elseif($result == 2){
			$response['error'] = true; 
			$response['message'] = "Some error occurred please try again";			
		}elseif($result == 0){
			$response['error'] = true; 
			$response['message'] = "It seems you are already registered, please choose a different email and username";						
		}

	
}else{
	$response['error'] = true; 
	$response['message'] = "Invalid Request";
}

echo json_encode($response);
