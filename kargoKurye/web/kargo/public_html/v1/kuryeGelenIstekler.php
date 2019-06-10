<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){
	
		$db = new DbOperations(); 

        $kargo=$db->kuryeGelenIstekler($_POST['sehir']);
        
        
        for($i=0;$i<count($kargo);$i++)
        {
            $response[$i]['Id']=$kargo[$i][0];
            $response[$i]['kullanici']=$kargo[$i][1];
            $response[$i]['enlem']=$kargo[$i][2];
            $response[$i]['boylam']=$kargo[$i][3];
            $response[$i]['sehir']=$kargo[$i][4];
            $response[$i]['durum']=$kargo[$i][5];
         
        }
		

	}else{
		$response['error'] = true; 
		$response['message'] = "Required fields are missing";
	
}

echo json_encode($response);