<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){
	
		$db = new DbOperations(); 

        $konum=$db->konumAl($_POST['kurye']
									);
        
        
       
            $response['kurye']=$konum['kurye'];
            $response['enlem']=$konum['enlem'];
            $response['boylam']=$konum['boylam'];
            
        
		

	}else{
		$response['error'] = true; 
		$response['message'] = "Required fields are missing";
	
}

echo json_encode($response);