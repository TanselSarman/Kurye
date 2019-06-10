
<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){
	if(
		isset($_POST['mesaj']))
		{
		//operate the data further 

		$db = new DbOperations(); 

		$result = $db->createMesaj( 	$_POST['gonderen'],
									$_POST['alici'],
									$_POST['mesaj']
								);
		if($result == 1){
			$response['error'] = false; 
			$response['message'] = "Mesaj başarı ile gönderildi";
		}elseif($result == 2){
			$response['error'] = true; 
			$response['message'] = "Some error occurred please try again";			
		}elseif($result == 0){
			$response['error'] = true; 
			$response['message'] = "It seems you are already registered, please choose a different email and username";						
		}

	}else{
		$response['error'] = true; 
		$response['message'] = "Required fields are missing";
	}
}else{
	$response['error'] = true; 
	$response['message'] = "Invalid Request";
}

echo json_encode($response);
