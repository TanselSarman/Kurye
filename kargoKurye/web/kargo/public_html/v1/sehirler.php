<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='GET'){
	
		$db = new DbOperations(); 

        $sehir=$db->sehirAl();
        
        for($i=0;$i<count($sehir);$i++)
        {
            $response[$i]['sehir']=$sehir[$i][1];
           
        }
		
            
        
		

	}else{
		$response['error'] = true; 
		$response['message'] = "Required fields are missing";
	
}

echo json_encode($response);