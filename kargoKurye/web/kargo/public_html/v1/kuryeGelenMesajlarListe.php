<?php 

require_once '../includes/DbOperations.php';

$response = array(); 

if($_SERVER['REQUEST_METHOD']=='POST'){
	
		$db = new DbOperations(); 

        $kargo=$db->kuryeGelenMesajListe($_POST['alici']);
        
        
        for($i=0;$i<count($kargo);$i++)
        {
            $response[$i]['Id']=$kargo[$i][0];
            $response[$i]['gonderen']=$kargo[$i][1];           
            $response[$i]['alici']=$kargo[$i][2];           
            $response[$i]['mesaj']=$kargo[$i][3];
            $response[$i]['deger']=$kargo[$i][4];
           
        }
		

	}else{
		$response['error'] = true; 
		$response['message'] = "Required fields are missing";
	
}

echo json_encode($response);